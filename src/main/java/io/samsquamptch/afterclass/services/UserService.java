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

    public UserDTO createUser() {
        return null;
    }

    public UserDTO getUser(long id) {
        return null;
    }

    public List<UserDTO> getAllUsers() {
        return null;
    }

    public UserDTO updateUser(long id, String name) {
        return null;
    }

    public ResponseEntity<Void> deleteUser(long id) {
        return null;
    }
}
