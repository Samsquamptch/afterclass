package io.samsquamptch.afterclass.controllers;

import io.samsquamptch.afterclass.dto.GroupRequestDTO;
import io.samsquamptch.afterclass.dto.GroupDTO;
import io.samsquamptch.afterclass.dto.PassCodeDTO;
import io.samsquamptch.afterclass.services.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }
    
    @PostMapping()
    public ResponseEntity<GroupDTO> createGroup(@RequestBody GroupRequestDTO request) {
        GroupDTO createdGroup = groupService.createGroup(request.getName());
        return new ResponseEntity<>(createdGroup, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO> getGroup(@PathVariable Long id) {
        GroupDTO group = groupService.getGroup(id);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGroup(@PathVariable Long id, @RequestBody GroupRequestDTO request) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        return null;
    }
}