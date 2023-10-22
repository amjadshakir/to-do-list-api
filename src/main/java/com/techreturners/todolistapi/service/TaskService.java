package com.techreturners.todolistapi.service;

import com.techreturners.todolistapi.exception.TaskNotFoundException;
import com.techreturners.todolistapi.model.Task;

import java.util.List;

public interface TaskService {

    Task insertTask(Task task);
    List<Task> getAllTasks();
    Task getTaskById(Long id) throws TaskNotFoundException;
}
