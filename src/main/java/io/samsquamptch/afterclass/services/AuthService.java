package io.samsquamptch.afterclass.services;

import io.samsquamptch.afterclass.Group;
import io.samsquamptch.afterclass.User;
import io.samsquamptch.afterclass.exception.UnauthorisedException;
import io.samsquamptch.afterclass.repositories.GroupRepository;
import io.samsquamptch.afterclass.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public AuthService(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    public long authenticateGroup(String passCode) {
        Group group = groupRepository.findByPassCode(passCode)
                .orElseThrow(() -> new UnauthorisedException("No group with passCode found"));
        return group.getId();
    }

    public long authenticateUser(String passCode) {
        User user = userRepository.findByPassCode(passCode)
                .orElseThrow(() -> new UnauthorisedException("No group with passCode found"));
        return user.getId();
    }
}
