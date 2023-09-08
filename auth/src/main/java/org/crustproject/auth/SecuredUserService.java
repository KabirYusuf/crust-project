package org.crustproject.auth;

import lombok.AllArgsConstructor;
import org.crustproject.usermanagement.data.model.User;
import org.crustproject.usermanagement.exception.UserManagementException;
import org.crustproject.usermanagement.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.crustproject.auth.AuthConstants.*;

@Service
@AllArgsConstructor
public class SecuredUserService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User foundUser = userService.findUserByEmail(username);
        if (foundUser == null)throw new AuthException(USER_NOT_FOUND.replace("{email}", username));
        return new SecuredUser(foundUser);
    }
}
