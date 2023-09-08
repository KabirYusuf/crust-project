package org.crustproject.taskmanagement.service;

import org.crustproject.taskmanagement.data.dto.request.CreateTaskRequest;
import org.crustproject.taskmanagement.data.model.Task;
import org.crustproject.usermanagement.data.dto.response.ApiResponse;

import java.util.List;

public interface TaskService {
    ApiResponse createTask(String jwt, CreateTaskRequest createTaskRequest);
    List<Task> getAllTasks();
    ApiResponse deleteTaskById(Long taskId);
    ApiResponse updateTask(Long taskId);
}
