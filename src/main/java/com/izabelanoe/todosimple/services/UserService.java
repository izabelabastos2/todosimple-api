package com.izabelanoe.todosimple.services;

import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izabelanoe.todosimple.models.User;
import com.izabelanoe.todosimple.repositories.TaskRepository;
import com.izabelanoe.todosimple.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired // Construtor de interface rs
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    /**
     * @param id
     * @return
     */
    public User findById(Long id) {
        // corrigir erro de null point Exception
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(
                () -> new RuntimeException("Usuário não encontrado! Id:" + id + "Tipos:" + User.class.getName()));
    }

    @Transactional // Usado para td que for persistir
    public User create(User obj) {
        obj.setId(null);
        obj = this.userRepository.save(obj);
        this.taskRepository.saveAll(obj.getTask());
        return obj;

    }

    @Transactional
    public User update(User obj) {
        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);

    }

    /**
     * @param id
     */
    @Transactional
    public void delete(Long id) {
        findById(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir! Existem tasks para esse usuário");
        }

    }

}
