package org.crustproject.entrypoint.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.crustproject.auth.AuthController;
import org.crustproject.usermanagement.data.dto.ApiResponse;
import org.crustproject.usermanagement.data.dto.LoginRequest;
import org.crustproject.usermanagement.data.dto.RegistrationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
@AllArgsConstructor
public class AuthenticationController {
    private final AuthController authController;

    @PostMapping("register")
    public ResponseEntity<ApiResponse> registerCommuter(
//            @Valid
            @RequestBody
            RegistrationRequest registrationRequest
    ){
        return new ResponseEntity<>(authController.register(registrationRequest), HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<ApiResponse> login(
//            @Valid
            @RequestBody
            LoginRequest loginRequest
    ){
        return ResponseEntity.ok(authController.loginUser(loginRequest));
    }
}
