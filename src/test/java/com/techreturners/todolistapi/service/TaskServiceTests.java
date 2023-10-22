package com.techreturners.todolistapi.service;

import com.techreturners.todolistapi.exception.TaskNotFoundException;
import com.techreturners.todolistapi.model.Task;
import com.techreturners.todolistapi.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@DataJpaTest
class TaskServiceTests {
    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskServiceImpl;

    @Test
    void testInsertTask(){
        Task expectedTask = new Task(1L, "Study", "To practice Java", false);
        when(taskRepository.save(expectedTask)).thenReturn(expectedTask);
        Task actualTask =  taskServiceImpl.insertTask(expectedTask);
        assertThat(actualTask).isEqualTo(expectedTask);
    }
    @Test
    void testGetAllTasksReturnsListOfTasks(){
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "study", "practice java", false));
        tasks.add(new Task(2L, "cook", "dinner", false));
        tasks.add(new Task(3L, "dance", "tango", false));

        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> actualResult = taskServiceImpl.getAllTasks();

        assertThat(actualResult).hasSize(3);
        assertThat(actualResult).isEqualTo(tasks);
    }

    @Test
     void testGetAllTasksReturnsEmptyTasks() {
        List<Task> tasks = new ArrayList<>();

        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> actualResult = taskServiceImpl.getAllTasks();

        assertThat(actualResult).hasSize(0);
        assertThat(actualResult).isEqualTo(tasks);
    }
    @Test
    void testGetTaskByIdReturnsATask() throws TaskNotFoundException {
        Task task = new Task(1L, "study", "practice java", false);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Task actualResult = taskServiceImpl.getTaskById(1L);

        assertThat(actualResult).isEqualTo(task);
    }

    @Test
    void testGetTaskByIdReturnsTaskNotFoundException()  {
        Long id = 1L;
        when(taskRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(TaskNotFoundException.class, () -> taskServiceImpl.getTaskById(id));
    }
    @Test
    void testGetTasksByTitleReturnsListOfTasks() {
        List<Task> tasks = new ArrayList<>();
        Task task1 = new Task(1L, "study", "practice java", false);
        Task task2 = new Task(2L, "study", "practice java", false);
        Task task3 = new Task(3L, "study", "practice java", false);
        tasks.add(task1);

        when(taskRepository.findByTitle("study")).thenReturn(tasks);

        List<Task> actualResult = taskServiceImpl.getAllTasksByTitle("study");

        assertThat(actualResult).hasSize(1);
        assertThat(actualResult).isEqualTo(tasks);
    }

    @Test
    void testGetTasksByTitleReturnsEmptyTasks() {
        List<Task> tasks = new ArrayList<>();

        when(taskRepository.findByTitle("study")).thenReturn(tasks);

        List<Task> actualResult = taskServiceImpl.getAllTasksByTitle("study");

        assertThat(actualResult).isEmpty();
        assertThat(actualResult).isEqualTo(tasks);
    }
    @Test
    void testReplaceExistingTaskByIdReturnsUpdatedTask() throws TaskNotFoundException {
        Task task = new Task(1L, "study", "practice java", false);
        Task updatedTask = new Task(1L, "cook", "dinner", false);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        taskServiceImpl.replaceExistingTask(1L, updatedTask);
        taskServiceImpl.getTaskById(1L);
        assertThat(taskServiceImpl.getTaskById(1L)).isEqualTo(updatedTask);
    }

    @Test
    void testReplaceExistingTaskByIdReturnsTaskNotFoundException() throws TaskNotFoundException {
        Task task = new Task(1L, "study", "practice java", false);
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(TaskNotFoundException.class, () -> taskServiceImpl.replaceExistingTask(1L, task));
    }
    @Test
    void testDeleteTaskByIdReturnsUpdatedTask() throws TaskNotFoundException {
        Task task = new Task(1L, "study", "practice java", false);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        taskServiceImpl.replaceExistingTask(1L, task);
        assertThat(taskServiceImpl.getTaskById(1L)).isEqualTo(task);
    }

    @Test
    void testDeleteTaskByIdReturnsTaskNotFoundException() throws TaskNotFoundException {
        Task task = new Task(1L, "study", "practice java", false);
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(TaskNotFoundException.class, () -> taskServiceImpl.deleteATask(1L));
    }
}
