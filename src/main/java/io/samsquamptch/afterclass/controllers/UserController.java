package io.samsquamptch.afterclass.controllers;

import io.samsquamptch.afterclass.dto.UserDTO;
import io.samsquamptch.afterclass.dto.UserRequestDTO;
import io.samsquamptch.afterclass.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups/{groupId}/users")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {this.userService = userService;}

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@PathVariable Long groupId, @RequestBody UserRequestDTO request) {
        UserDTO createdUser = userService.createUser(groupId, request.getName());
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers(@PathVariable Long groupId) {
        List<UserDTO> userDTOs = userService.getAllUsers(groupId);
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long groupId,
                                           @PathVariable Long id,
                                           @RequestBody UserRequestDTO request) {
        userService.updateUser(groupId, id, request.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long groupId, @PathVariable Long id) {
        userService.deleteUser(groupId, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
