package com.techreturners.todolistapi.controller;

import com.techreturners.todolistapi.exception.TaskNotFoundException;
import com.techreturners.todolistapi.model.Task;
import com.techreturners.todolistapi.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskDetailsById(@Valid @PathVariable Long id) throws TaskNotFoundException {
        Task task = taskService.getTaskById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);

    }
    @GetMapping("/title/{title}")
    public ResponseEntity<List<Task>> getAllTasksByTitle(@Valid @PathVariable String title) throws TaskNotFoundException {
        List<Task> tasksByTitle = taskService.getAllTasksByTitle(title);
        return new ResponseEntity<>(tasksByTitle, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Task> replaceExistingTaskDetails(@Valid @PathVariable Long id, @RequestBody Task task) throws TaskNotFoundException {
        Task updatedTask = taskService.replaceExistingTask(id, task);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteATask(@Valid @PathVariable Long id) throws TaskNotFoundException {
        taskService.deleteATask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
