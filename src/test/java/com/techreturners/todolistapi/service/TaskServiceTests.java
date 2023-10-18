package com.techreturners.todolistapi.service;

import com.techreturners.todolistapi.model.Task;
import com.techreturners.todolistapi.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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
}
