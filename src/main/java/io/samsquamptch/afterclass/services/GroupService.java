package io.samsquamptch.afterclass.services;

import io.samsquamptch.afterclass.Group;
import io.samsquamptch.afterclass.dto.GroupRequestDTO;
import io.samsquamptch.afterclass.dto.GroupDTO;
import io.samsquamptch.afterclass.repositories.GroupRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public GroupDTO createGroup(GroupRequestDTO request) {
        Group group = new Group(request.getName(), UUID.randomUUID().toString().substring(0, 6));
        Group savedGroup = groupRepository.save(group);
        return new GroupDTO(savedGroup.getId(), savedGroup.getName(), savedGroup.getPassCode(), null);
    }

    public GroupDTO getGroupByPasscode(String passcode) {
        return null;
    }

    public GroupDTO updateGroup(String passCode, String name) {
        return null;
    }

    public ResponseEntity<Void> deleteGroup(String passCode) {
        return null;
    }
}
