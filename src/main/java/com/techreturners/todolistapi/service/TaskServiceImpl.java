package com.techreturners.todolistapi.service;

import com.techreturners.todolistapi.exception.TaskNotFoundException;
import com.techreturners.todolistapi.model.Task;
import com.techreturners.todolistapi.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    TaskRepository taskRepository;
    public Task insertTask(Task task){
        return taskRepository.save(task);
    }
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        taskRepository.findAll().forEach(tasks::add);
        return tasks;
    }
    public Task getTaskById(Long id) throws TaskNotFoundException {
        Task task;
            if (taskRepository.findById(id).isPresent()){
                task = taskRepository.findById(id).get();
            } else {
                throw new TaskNotFoundException("Task not found for this id, " + id);
            }
        return task;
    }
    public List<Task> getAllTasksByTitle(String title){
        return new ArrayList<>(taskRepository.findByTitle(title));
    }
    public Task replaceExistingTask(Long id, Task task) throws TaskNotFoundException{
        Task existingTask;
            if (taskRepository.findById(id).isPresent()){
                existingTask = taskRepository.findById(id).get();
                existingTask.setTitle(task.getTitle());
                existingTask.setDescription(task.getDescription());
                existingTask.setCompleted(task.isCompleted());
                taskRepository.save(existingTask);

            } else {
                throw new TaskNotFoundException("Task not found for this id, " +id);
            }
        return existingTask;
    }

}
