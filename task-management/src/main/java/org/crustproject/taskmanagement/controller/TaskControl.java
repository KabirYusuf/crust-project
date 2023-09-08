package org.crustproject.taskmanagement.controller;

import org.crustproject.taskmanagement.data.dto.request.CreateTaskRequest;
import org.crustproject.taskmanagement.data.model.Task;
import org.crustproject.taskmanagement.service.TaskService;
import org.crustproject.usermanagement.data.dto.response.ApiResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskControl {
    private final TaskService taskService;
    public TaskControl(TaskService taskService){
        this.taskService = taskService;
    }
    public ApiResponse createTask(String jwt, CreateTaskRequest createTaskRequest){
        return taskService.createTask(jwt, createTaskRequest);
    }
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }
    public ApiResponse updateTask(Long taskId){
        return taskService.updateTask(taskId);
    }
    public ApiResponse deleteTask(Long taskId){
        return taskService.deleteTaskById(taskId);
    }
}
