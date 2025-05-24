package io.samsquamptch.afterclass.services;

import io.samsquamptch.afterclass.dto.UserDTO;
import io.samsquamptch.afterclass.repositories.UserRepository;

import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO createUser(Long groupId, String name) {
        return null;
    }

    public List<UserDTO> getAllUsers(Long groupId) {
        return null;
    }

    public UserDTO getUser(Long groupId, Long id) {
        return null;
    }

    public void updateUser(Long groupId, Long id, String name) {

    }

    public void deleteUser(Long groupId, Long id) {

    }
}
