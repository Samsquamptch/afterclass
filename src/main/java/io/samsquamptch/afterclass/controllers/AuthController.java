package io.samsquamptch.afterclass.controllers;

import io.samsquamptch.afterclass.components.SessionValidator;
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
    private final SessionValidator sessionValidator;

    public AuthController(final AuthService authService, SessionValidator sessionValidator) {
        this.authService = authService;
        this.sessionValidator = sessionValidator;
    }

    @GetMapping("/session/group")
    public ResponseEntity<Void> getSession(HttpSession session) {
        Long groupdId = (Long) session.getAttribute("groupdId");
        if (groupdId == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/session/user")
    public ResponseEntity<Void> getUser(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/group")
    public ResponseEntity<Void> getGroup(@RequestBody AuthDTO request, HttpSession session) {
        Long groupId = authService.authenticateGroup(request.getPassCode());
        session.setAttribute("groupId", groupId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user")
    public ResponseEntity<Void> getUser(@RequestBody AuthDTO request, HttpSession session) {
        Long groupId = sessionValidator.validateSessionAttribute("groupId", session);
        Long userId = authService.authenticateUser(request.getPassCode(), groupId);
        session.setAttribute("userId", userId);
        return ResponseEntity.ok().build();
    }
}
