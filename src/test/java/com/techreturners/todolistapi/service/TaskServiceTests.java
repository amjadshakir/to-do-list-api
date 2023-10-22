package com.techreturners.todolistapi.service;

import com.techreturners.todolistapi.model.Task;
import com.techreturners.todolistapi.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
}
