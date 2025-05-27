package io.samsquamptch.afterclass.controllers;

import io.samsquamptch.afterclass.dto.GroupRequestDTO;
import io.samsquamptch.afterclass.dto.GroupDTO;
import io.samsquamptch.afterclass.interfaces.GroupValidator;
import io.samsquamptch.afterclass.services.GroupService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/groups")
public class GroupController implements GroupValidator {

    GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }
    
    @PostMapping()
    public ResponseEntity<GroupDTO> createGroup(@RequestBody GroupRequestDTO request, HttpSession session) {
        GroupDTO createdGroup = groupService.createGroup(request.getName());
        session.setAttribute("groupId", createdGroup.getId());
        return new ResponseEntity<>(createdGroup, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<GroupDTO> getGroup(HttpSession session) {
        Long groupId = (Long) session.getAttribute("groupId");
        validateGroup(groupId);
        GroupDTO group = groupService.getGroup(groupId);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<Void> updateGroup(@RequestBody GroupRequestDTO request, HttpSession session) {
        Long groupId = (Long) session.getAttribute("groupId");
        validateGroup(groupId);
        groupService.updateGroup(groupId, request.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteGroup(HttpSession session) {
        Long groupId = (Long) session.getAttribute("groupId");
        validateGroup(groupId);
        groupService.deleteGroup(groupId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}