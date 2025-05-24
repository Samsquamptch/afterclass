package io.samsquamptch.afterclass.services;

import io.samsquamptch.afterclass.Group;
import io.samsquamptch.afterclass.dto.GroupDTO;
import io.samsquamptch.afterclass.repositories.GroupRepository;
import org.springframework.stereotype.Service;

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

    public GroupDTO getGroupByPasscode(String passcode) {
        return null;
    }

    public void updateGroup(String passCode, String name) {
        return;
    }

    public void deleteGroup(String passCode) {
        return;
    }
}
