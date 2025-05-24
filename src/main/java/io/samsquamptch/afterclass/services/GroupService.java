package io.samsquamptch.afterclass.services;

import io.samsquamptch.afterclass.Group;
import io.samsquamptch.afterclass.dto.GroupDTO;
import io.samsquamptch.afterclass.exception.NotFoundException;
import io.samsquamptch.afterclass.repositories.GroupRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public GroupDTO createGroup(String name) {
        Group group = new Group(name, UUID.randomUUID().toString().substring(0, 6));
        Group savedGroup = groupRepository.save(group);
        return new GroupDTO(savedGroup.getId(), savedGroup.getName(), savedGroup.getPassCode(), null);
    }

    public GroupDTO getGroup(Long id) {
        Optional<Group> group = groupRepository.findGroupById(id);
        Group groupEntity = group
                .orElseThrow(() -> new NotFoundException("Group not found"));
        return new GroupDTO(groupEntity.getId(), groupEntity.getName(), groupEntity.getPassCode(), null);

    }

    public void updateGroup(Long id, String name) {
        return;
    }

    public void deleteGroup(Long id) {
        return;
    }
}
