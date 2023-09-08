package org.crustproject.usermanagement.service;

import org.crustproject.usermanagement.data.model.User;
import org.crustproject.usermanagement.data.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CrustUserService implements UserService{
    private final UserRepository userRepository;
    public CrustUserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public User findUserByEmail(String email) {
        return userRepository
                .findUserByEmailIgnoreCase(email)
                .orElse(null);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
