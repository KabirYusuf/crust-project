package org.crustproject.taskmanagement.service;

import org.crustproject.auth.JwtService;
import org.crustproject.taskmanagement.data.dto.request.CreateTaskRequest;
import org.crustproject.taskmanagement.data.model.Task;
import org.crustproject.taskmanagement.data.repository.TaskRepository;
import org.crustproject.taskmanagement.exception.TaskException;
import org.crustproject.usermanagement.data.dto.response.ApiResponse;
import org.crustproject.usermanagement.data.model.User;
import org.crustproject.usermanagement.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.crustproject.taskmanagement.util.TaskConstants.*;

@Service
public class CrustTaskService implements TaskService{
    private final TaskRepository taskRepository;
    private final JwtService jwtService;
    private final UserService userService;
    public CrustTaskService(
            TaskRepository taskRepository,
            JwtService jwtService,
            UserService userService
    ){
        this.taskRepository = taskRepository;
        this.jwtService = jwtService;
        this.userService = userService;
    }
    @Override

    public ApiResponse createTask(String jwt, CreateTaskRequest createTaskRequest) {
        String email = jwtService.extractUsername(jwt.substring(7));
        User taskCreator = userService.findUserByEmail(email);
        Task task = new Task();
        task.setTitle(createTaskRequest.getTitle());
        task.setDescription(createTaskRequest.getDescription());
        task.setUser(taskCreator);
        taskRepository.save(task);
        ApiResponse apiResponse = getApiResponse(TASK_CREATED_SUCCESSFULLY);
        return apiResponse;
    }

    private ApiResponse getApiResponse(Object data) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccessful(true);
        apiResponse.setData(data);
        return apiResponse;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository
                .findAll();
    }

    @Override
    public ApiResponse deleteTaskById(Long taskId) {
        taskRepository.deleteById(taskId);
        return getApiResponse(TASK_DELETED_SUCCESFULLY);
    }

    @Override
    public ApiResponse updateTask(Long taskId) {
        Task task = taskRepository
                .findById(taskId)
                .orElseThrow(()-> new TaskException(TASK_DOESNT_EXIST));
        task.setCompleted(!task.isCompleted());
        taskRepository.save(task);
        return getApiResponse(TASK_UPDATED_SUCCESSFULLY);
    }
}
