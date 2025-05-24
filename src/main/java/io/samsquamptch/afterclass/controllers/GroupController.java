package io.samsquamptch.afterclass.controllers;

import io.samsquamptch.afterclass.dto.GroupRequestDTO;
import io.samsquamptch.afterclass.dto.GroupDTO;
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
        GroupDTO createdGroup = groupService.createGroup(request);
        return new ResponseEntity<>(createdGroup, HttpStatus.CREATED);
    }

    @GetMapping("/{passCode}")
    public ResponseEntity<GroupDTO> getGroup(@PathVariable String passCode) {
        return null;
    }

    @PutMapping("/{passCode}")
    public ResponseEntity<Void> updateGroup(@PathVariable String passCode, @RequestBody GroupRequestDTO request) {
        return null;
    }

    @DeleteMapping("/{passCode}")
    public ResponseEntity<Void> deleteGroup(@PathVariable String passCode) {
        return null;
    }
}