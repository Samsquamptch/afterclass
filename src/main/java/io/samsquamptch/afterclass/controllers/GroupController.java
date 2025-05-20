package io.samsquamptch.afterclass.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/groups")
public class GroupController {
    @PostMapping()
    public String createGroup() {
        return null;
    }

    @PutMapping("/{id}")
    public void updateGroup(@PathVariable Integer id) {}

    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable Integer id) {}

    @GetMapping("/{id}")
    public void getGroup(@PathVariable Integer id) {}

}