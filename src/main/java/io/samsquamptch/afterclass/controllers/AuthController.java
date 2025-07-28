package io.samsquamptch.afterclass.controllers;

import io.samsquamptch.afterclass.components.SessionValidator;
import io.samsquamptch.afterclass.dto.AuthDTO;
import io.samsquamptch.afterclass.services.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final SessionValidator sessionValidator;

    public AuthController(final AuthService authService, SessionValidator sessionValidator) {
        this.authService = authService;
        this.sessionValidator = sessionValidator;
    }

    @GetMapping("/session")
    public ResponseEntity<Map<String, Boolean>> getSession(HttpSession session) {
        boolean hasGroup = session.getAttribute("groupId") != null;
        boolean hasUser = session.getAttribute("userId") != null;

        Map<String, Boolean> result = new HashMap<>();
        result.put("hasGroup", hasGroup);
        result.put("hasUser", hasUser);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/group")
    public ResponseEntity<Void> setGroup(@RequestBody AuthDTO request, HttpSession session) {
        Long groupId = authService.authenticateGroup(request.getPassCode());
        session.setAttribute("groupId", groupId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/user")
    public ResponseEntity<Void> setUser(@RequestBody AuthDTO request, HttpSession session) {
        Long groupId = sessionValidator.validateSessionAttribute("groupId", session);
        Long userId = authService.authenticateUser(request.getPassCode(), groupId);
        session.setAttribute("userId", userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok().build();
    }
}
