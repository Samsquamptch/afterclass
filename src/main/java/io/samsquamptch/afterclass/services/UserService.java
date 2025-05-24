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

    public UserDTO createUser(String name) {
        return null;
    }

    public List<UserDTO> getAllUsers(String passCode) {
        return null;
    }

    public UserDTO getUser(String passCode, long id) {
        return null;
    }

    public UserDTO updateUser(String passCode, long id, String name) {
        return null;
    }

    public ResponseEntity<Void> deleteUser(String passCode, long id) {
        return null;
    }
}
