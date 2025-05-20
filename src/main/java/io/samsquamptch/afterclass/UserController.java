package io.samsquamptch.afterclass;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @PostMapping
    public String createUser() {
        return null;
    }

    @PutMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {}

    @DeleteMapping("/{id}")
    public void updateUser(@PathVariable Long id) {}

    @GetMapping("/{id}")
    public void getUser(@PathVariable Long id) {}
}
