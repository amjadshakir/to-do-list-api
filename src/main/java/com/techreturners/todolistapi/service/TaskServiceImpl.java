package com.techreturners.todolistapi.service;

import com.techreturners.todolistapi.model.Task;
import com.techreturners.todolistapi.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    TaskRepository taskRepository;
    public Task insertTask(Task task){
        return taskRepository.save(task);
    }
}
