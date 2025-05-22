package io.samsquamptch.afterclass.services;

import io.samsquamptch.afterclass.Group;
import io.samsquamptch.afterclass.dto.CreateGroupRequest;
import io.samsquamptch.afterclass.dto.GroupDTO;
import io.samsquamptch.afterclass.repositories.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public GroupDTO createGroup(CreateGroupRequest request) {
        Group group = new Group();
        group.setName(request.getName());
        group.setPassCode(request.getPassCode());

        Group savedGroup = groupRepository.save(group);
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setId(savedGroup.getId());
        groupDTO.setName(savedGroup.getName());
        groupDTO.setPassCode(savedGroup.getPassCode());
        return groupDTO;
    }
}
