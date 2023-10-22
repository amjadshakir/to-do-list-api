package com.techreturners.todolistapi.controller;

import com.techreturners.todolistapi.model.Task;
import com.techreturners.todolistapi.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    @Autowired
    TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody @Valid Task task){
        Task newTask = taskService.insertTask(task);
        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }
}
