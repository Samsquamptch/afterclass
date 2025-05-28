package io.samsquamptch.afterclass.controllers;

import io.samsquamptch.afterclass.dto.AuthDTO;
import io.samsquamptch.afterclass.services.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(final AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/group")
    public ResponseEntity<Void> getGroup(@RequestBody AuthDTO request, HttpSession session) {
        Long groupId = authService.authenticateGroup(request.getPassCode());
        session.setAttribute("groupId", groupId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user")
    public ResponseEntity<Void> getUser(@RequestBody AuthDTO request, HttpSession session) {

        Long userId = authService.authenticateUser(request.getPassCode());
        session.setAttribute("userId", userId);
        return ResponseEntity.ok().build();
    }
}
