package org.crustproject.entrypoint.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.crustproject.taskmanagement.controller.TaskControl;
import org.crustproject.taskmanagement.data.dto.request.CreateTaskRequest;
import org.crustproject.taskmanagement.data.model.Task;
import org.crustproject.usermanagement.data.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private final TaskControl taskControl;
    public TaskController(TaskControl taskControl){
        this.taskControl = taskControl;
    }
    @PostMapping
    public ResponseEntity<ApiResponse> createTask(HttpServletRequest httpServletRequest, @RequestBody CreateTaskRequest createTaskRequest){
        String jwt = httpServletRequest.getHeader("Authorization");
        return new ResponseEntity<>(taskControl.createTask(jwt, createTaskRequest), HttpStatus.CREATED);
    }
    @GetMapping
    public List<Task> getAllTasks(){
        return taskControl.getAllTasks();
    }
    @PatchMapping("/{taskId}")
    public ResponseEntity<ApiResponse> updateTask(@PathVariable("taskId") Long taskId){
        return ResponseEntity.ok(taskControl.updateTask(taskId));
    }
    @DeleteMapping("/{taskId}")
    public ResponseEntity<ApiResponse> deleteTask(@PathVariable("taskId") Long taskId){
        return ResponseEntity.ok(taskControl.deleteTask(taskId));
    }

}
