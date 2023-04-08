package com.izabelanoe.todosimple.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.izabelanoe.todosimple.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findfindByUser_Id(Long id);

    // @Query(value = "SELECT t FROM Task t WHERE t.user.id = :id")
    // List<Task> findfindByUser_Id(@Param ("id") Long id);

    // @Query(value = "SELECT * FROM task t WHERE t = :id", nativeQuery = true)
    // List<Task> findfindByUser_Id(@Param ("id") Long id);

}
