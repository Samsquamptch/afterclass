package io.samsquamptch.afterclass.services;

import io.samsquamptch.afterclass.Group;
import io.samsquamptch.afterclass.dto.GroupDTO;
import io.samsquamptch.afterclass.dto.UserDTO;
import io.samsquamptch.afterclass.exception.NotFoundException;
import io.samsquamptch.afterclass.repositories.GroupRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserService userService;

    public GroupService(GroupRepository groupRepository, UserService userService) {
        this.groupRepository = groupRepository;
        this.userService = userService;
    }

    public GroupDTO createGroup(String name) {
        Group group = new Group(name, UUID.randomUUID().toString().substring(0, 6));
        Group savedGroup = groupRepository.save(group);
        return new GroupDTO(savedGroup.getId(), savedGroup.getName(), savedGroup.getPassCode(), new ArrayList<>());
    }

    public GroupDTO getGroup(Long id) {
        Group group = groupRepository.findById(id).orElseThrow(NotFoundException::new);
        return new GroupDTO(group.getId(), group.getName(), group.getPassCode(), getUsersFromGroup(id));
    }

    public void updateGroup(Long id, String name) {
        Group group = groupRepository.findById(id).orElseThrow(NotFoundException::new);
        group.setName(name);
        groupRepository.save(group);
    }

    public void deleteGroup(Long id) {
        Group group = groupRepository.findById(id).orElseThrow(NotFoundException::new);
        groupRepository.delete(group);
    }

    private List<UserDTO> getUsersFromGroup(Long groupId) {
        return userService.getAllUsers(groupId);
    }
}
