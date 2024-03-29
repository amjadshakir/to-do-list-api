package com.techreturners.todolistapi.repository;

import com.techreturners.todolistapi.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findByTitle(String title);
}
