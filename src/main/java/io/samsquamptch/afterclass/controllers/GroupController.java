package io.samsquamptch.afterclass.controllers;

import io.samsquamptch.afterclass.dto.CreateGroupRequest;
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
    public ResponseEntity<GroupDTO> createGroup(@RequestBody CreateGroupRequest request) {
        GroupDTO createdGroup = groupService.createGroup(request);
        return new ResponseEntity<>(createdGroup, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public void updateGroup(@PathVariable Integer id) {}

    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable Integer id) {}

    @GetMapping("/{id}")
    public void getGroup(@PathVariable Integer id) {}

}