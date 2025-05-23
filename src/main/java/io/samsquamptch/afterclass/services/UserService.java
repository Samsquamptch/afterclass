package io.samsquamptch.afterclass.services;

import io.samsquamptch.afterclass.repositories.UserRepository;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
