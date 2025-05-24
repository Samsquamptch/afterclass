package io.samsquamptch.afterclass.controllers;

import io.samsquamptch.afterclass.dto.UserDTO;
import io.samsquamptch.afterclass.dto.UserRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups/{passCode}/users")
public class UserController {

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@PathVariable String passCode, @RequestBody UserRequestDTO request) {
        return null;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers(@PathVariable String passCode) {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String passCode, @PathVariable Long id) {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String passCode,
                                              @PathVariable Long id,
                                              @RequestBody UserRequestDTO request) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable String passCode,
                                           @PathVariable Long id,
                                           @RequestBody UserRequestDTO request) {
        return null;
    }


}
