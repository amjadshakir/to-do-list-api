package com.techreturners.todolistapi.service;

import com.techreturners.todolistapi.model.Task;

import java.util.List;

public interface TaskService {

    Task insertTask(Task task);
    public List<Task> getAllTasks();
}
