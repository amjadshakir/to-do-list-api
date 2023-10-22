package com.techreturners.todolistapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techreturners.todolistapi.model.Task;
import com.techreturners.todolistapi.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@SpringBootTest
class TaskControllerTests {
    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;
    private MockMvc mockMvc;
    private ObjectMapper mapper;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
        mapper = new ObjectMapper();
    }

    @Test
    void testCreateTaskReturns201() throws Exception {
    Task task = new Task(1L, "Study", "To practice Java", false);
    when(taskService.insertTask(task)).thenReturn(task);
    this.mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v1/tasks")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(task)))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Study"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("To practice Java"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.completed").value(false));

    verify(taskService, times(1)).insertTask(task);
    }

    @Test
    void testCreateTaskReturns400ForMethodArgumentNotValidException() throws Exception {
        Task task = new Task(1L, "", "", false);
        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(task)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(taskService, times(0)).insertTask(task);
    }
}
