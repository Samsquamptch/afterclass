package io.samsquamptch.afterclass.controllers;

import io.samsquamptch.afterclass.components.SessionValidator;
import io.samsquamptch.afterclass.dto.CreatedUserDTO;
import io.samsquamptch.afterclass.dto.UserDTO;
import io.samsquamptch.afterclass.dto.UserRequestDTO;
import io.samsquamptch.afterclass.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final SessionValidator sessionValidator;

    public UserController(UserService userService, SessionValidator sessionValidator) {
        this.userService = userService;
        this.sessionValidator = sessionValidator;
    }

    @PostMapping()
    public ResponseEntity<CreatedUserDTO> createUser(@RequestBody UserRequestDTO request, HttpSession session) {
        Long groupId = sessionValidator.validateSessionAttribute("groupId", session);
        CreatedUserDTO createdUser = userService.createUser(groupId, request.getName());
        session.setAttribute("userId", createdUser.getId());
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getUsers(HttpSession session) {
        Long groupId = sessionValidator.validateSessionAttribute("groupId", session);
        List<UserDTO> userDTOs = userService.getAllUsers(groupId);
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUser(HttpSession session) {
        Long groupId = (Long) session.getAttribute("groupId");
        Long userId = (Long) session.getAttribute("userId");
        UserDTO userDTO = userService.getUser(groupId, userId);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Void> updateUser(@RequestBody UserRequestDTO request, HttpSession session) {
        Long groupId = sessionValidator.validateSessionAttribute("groupId", session);
        Long userId = sessionValidator.validateSessionAttribute("userId", session);
        userService.updateUser(groupId, userId, request.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteUser(HttpSession session) {

        Long groupId = sessionValidator.validateSessionAttribute("groupId", session);
        Long userId = sessionValidator.validateSessionAttribute("userId", session);
        userService.deleteUser(groupId, userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
