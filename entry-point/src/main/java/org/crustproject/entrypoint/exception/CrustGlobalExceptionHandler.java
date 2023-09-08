package org.crustproject.entrypoint.exception;

import org.crustproject.auth.AuthException;
import org.crustproject.taskmanagement.exception.TaskException;
import org.crustproject.usermanagement.data.dto.response.ApiResponse;
import org.crustproject.usermanagement.exception.UserManagementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CrustGlobalExceptionHandler {
    @ExceptionHandler(UserManagementException.class)
    public ResponseEntity<ApiResponse> handleUserManagementException(UserManagementException userManagementException){
        ApiResponse apiResponse =  new ApiResponse();
        apiResponse.setData(userManagementException.getMessage());
        apiResponse.setSuccessful(false);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ApiResponse> handleAuthException(AuthException authException){
        ApiResponse apiResponse =  new ApiResponse();
        apiResponse.setData(authException.getMessage());
        apiResponse.setSuccessful(false);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(TaskException.class)
    public ResponseEntity<ApiResponse> handleTaskException(TaskException taskException){
        ApiResponse apiResponse =  new ApiResponse();
        apiResponse.setData(taskException.getMessage());
        apiResponse.setSuccessful(false);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
