package org.crustproject.auth;

import org.crustproject.usermanagement.data.dto.ApiResponse;
import org.crustproject.usermanagement.data.dto.LoginRequest;
import org.crustproject.usermanagement.data.dto.RegistrationRequest;
import org.crustproject.usermanagement.data.enums.Role;
import org.crustproject.usermanagement.data.model.User;
import org.crustproject.usermanagement.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

import static org.crustproject.auth.AuthConstants.*;
import static org.crustproject.usermanagement.data.enums.Role.*;

@Component
public class AuthController {

        private final UserService userService;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;

        public AuthController(
                UserService userService,
                PasswordEncoder passwordEncoder,
                JwtService jwtService,
                AuthenticationManager authenticationManager){
            this.userService = userService;
            this.passwordEncoder = passwordEncoder;
            this.jwtService = jwtService;
            this.authenticationManager = authenticationManager;
        }


        public ApiResponse register(RegistrationRequest registrationRequest) {
            if (userService.findUserByEmail(registrationRequest.getEmail()) != null) throw new AuthException(USER_ALREADY_EXIST);
            User user = new User();
            user.setEmail(registrationRequest.getEmail());
            user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(USER);
            user.setRoles(userRoles);
            userService.saveUser(user);
            SecuredUser securedUser = new SecuredUser(user);
            String token = jwtService.generateToken(securedUser);
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setData(token);
            apiResponse.setSuccessful(true);
            return apiResponse;
        }

    public ApiResponse loginUser(LoginRequest loginRequest) {
        authenticateUser(loginRequest);
        User foundUser = userService.findUserByEmail(loginRequest.getEmail());
        SecuredUser securedUser = new SecuredUser(foundUser);
        String token = jwtService.generateToken(securedUser);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(token);
        apiResponse.setSuccessful(true);
        return apiResponse;
    }
    private void authenticateUser(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
            ));
        }catch (AuthenticationException authenticationException){
            throw new AuthException(INVALID_CREDENTIALS);
        }
    }



}
