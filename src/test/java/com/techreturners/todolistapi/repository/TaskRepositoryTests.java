package com.techreturners.todolistapi.repository;

import com.techreturners.todolistapi.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
class TaskRepositoryTests {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void testCreatesAndFindTaskByIdReturnsTask(){
        Task expectedTask = new Task(1L, "Study", "To practice Java", false);
        taskRepository.save(expectedTask);
        Optional<Task> retrievedTask = taskRepository.findById(expectedTask.getId());
        assertThat(retrievedTask).isNotNull();
        assertEquals("Study", retrievedTask.get().getTitle());
        assertEquals("To practice Java", retrievedTask.get().getDescription());
        assertFalse(retrievedTask.get().isCompleted());
    }
}
