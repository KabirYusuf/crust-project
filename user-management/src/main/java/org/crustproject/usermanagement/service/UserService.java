package org.crustproject.usermanagement.service;

import org.crustproject.usermanagement.data.model.User;

public interface UserService {
    User findUserByEmail(String email);
    User saveUser(User user);
}
