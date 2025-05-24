package io.samsquamptch.afterclass.controllers;

import io.samsquamptch.afterclass.dto.UserDTO;
import io.samsquamptch.afterclass.dto.UserRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups/{groupId}/users")
public class UserController {

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@PathVariable Long groupId, @RequestBody UserRequestDTO request) {
        return null;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers(@PathVariable Long groupId) {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long groupId, @PathVariable Long id) {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long groupId,
                                              @PathVariable Long id,
                                              @RequestBody UserRequestDTO request) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long groupId,
                                           @PathVariable Long id,
                                           @RequestBody UserRequestDTO request) {
        return null;
    }


}
