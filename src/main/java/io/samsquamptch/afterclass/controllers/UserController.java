package io.samsquamptch.afterclass.controllers;

import io.samsquamptch.afterclass.dto.UserDTO;
import io.samsquamptch.afterclass.dto.UserRequestDTO;
import io.samsquamptch.afterclass.interfaces.GroupValidator;
import io.samsquamptch.afterclass.interfaces.SessionValidator;
import io.samsquamptch.afterclass.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController implements SessionValidator, GroupValidator {

    UserService userService;

    public UserController(UserService userService) {this.userService = userService;}

    @PostMapping()
    public ResponseEntity<UserDTO> createUser(@RequestBody UserRequestDTO request, HttpSession session) {
        Long groupId = (Long) session.getAttribute("groupId");
        validateGroup(groupId);
        UserDTO createdUser = userService.createUser(groupId, request.getName());
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getUsers(HttpSession session) {
        Long groupId = (Long) session.getAttribute("groupId");
        validateGroup(groupId);
        List<UserDTO> userDTOs = userService.getAllUsers(groupId);
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Void> updateUser(@RequestBody UserRequestDTO request, HttpSession session) {
        Long groupId = (Long) session.getAttribute("groupId");
        Long userId = (Long) session.getAttribute("userId");
        validateSession(groupId, userId);
        userService.updateUser(groupId, userId, request.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteUser(HttpSession session) {
        Long groupId = (Long) session.getAttribute("groupId");
        Long userId = (Long) session.getAttribute("userId");
        validateSession(groupId, userId);
        userService.deleteUser(groupId, userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
