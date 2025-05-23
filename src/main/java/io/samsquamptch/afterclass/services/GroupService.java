package io.samsquamptch.afterclass.services;

import io.samsquamptch.afterclass.Group;
import io.samsquamptch.afterclass.dto.CreateGroupRequest;
import io.samsquamptch.afterclass.dto.GroupDTO;
import io.samsquamptch.afterclass.repositories.GroupRepository;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public GroupDTO createGroup(CreateGroupRequest request) {
        Group group = new Group(request.getName(), request.getPassCode());
        Group savedGroup = groupRepository.save(group);
        return new GroupDTO(savedGroup.getId(), savedGroup.getName(), savedGroup.getPassCode());
    }

    public GroupDTO getByPasscode(String passcode) {
        return null;
    }

    public GroupDTO updateGroup(String name) {
        return null;
    }
}
