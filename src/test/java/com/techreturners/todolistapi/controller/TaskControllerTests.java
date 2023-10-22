package com.techreturners.todolistapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techreturners.todolistapi.exception.TaskNotFoundException;
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

import java.util.ArrayList;
import java.util.List;

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
    @Test
    void testCreateTaskReturns400ForHttpMessageNotReadableException() throws Exception {
        String taskAsString = "{\"title\":\"study\",\"description\":\"practice java\",\"completed\":\"falsy\"}";

        this.mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/tasks")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(taskAsString))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());


    }
    @Test
    void testGetAllTasksReturnsTasks() throws Exception {

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "study", "practice java", false));
        tasks.add(new Task(2L, "cook", "dinner", false));
        tasks.add(new Task(3L, "dance", "tango", false));
        when(taskService.getAllTasks()).thenReturn(tasks);

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/tasks/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("study"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value("practice java"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].completed").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("cook"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].description").value("dinner"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].completed").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].title").value("dance"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].description").value("tango"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].completed").value(false));

        verify(taskService, times(1)).getAllTasks();
    }

    @Test
    void testGetAllTasksReturnsEmptyTasks() throws Exception {

        List<Task> tasks = new ArrayList<>();
        when(taskService.getAllTasks()).thenReturn(tasks);

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/tasks/"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(taskService, times(1)).getAllTasks();
    }
    @Test
    void testGetTaskByIdReturnsATask() throws Exception, TaskNotFoundException {

        Task task = new Task(1L, "study", "practice java", false);

        when(taskService.getTaskById(1L)).thenReturn(task);

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/tasks/" + task.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("study"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("practice java"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.completed").value(false));

        verify(taskService, times(1)).getTaskById(1L);
    }

    @Test
    void testGetTaskByIdReturnsTaskNotFoundException() throws TaskNotFoundException, Exception {

        Task task = new Task(1L, "study", "practice java", false);

        when(taskService.getTaskById(1L)).thenThrow(TaskNotFoundException.class);


    }
}
