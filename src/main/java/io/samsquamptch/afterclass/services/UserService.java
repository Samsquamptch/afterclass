package io.samsquamptch.afterclass.services;

import io.samsquamptch.afterclass.dto.UserDTO;
import io.samsquamptch.afterclass.repositories.UserRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO createUser(String passCode, String name) {
        return null;
    }

    public List<UserDTO> getAllUsers(String passCode) {
        return null;
    }

    public UserDTO getUser(String passCode, long id) {
        return null;
    }

    public void updateUser(String passCode, long id, String name) {

    }

    public void deleteUser(String passCode, long id) {

    }
}
