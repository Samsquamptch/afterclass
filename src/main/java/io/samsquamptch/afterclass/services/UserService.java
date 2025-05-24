package io.samsquamptch.afterclass.services;

import io.samsquamptch.afterclass.dto.UserDTO;
import io.samsquamptch.afterclass.repositories.GroupRepository;
import io.samsquamptch.afterclass.repositories.UserRepository;

import java.util.List;

public class UserService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    public UserService(UserRepository userRepository, GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    public UserDTO createUser(Long groupId, String name) {
        return null;
    }

    public List<UserDTO> getAllUsers(Long groupId) {
        return null;
    }

    public UserDTO getUser(Long groupId, Long id) {
        return null;
    }

    public void updateUser(Long groupId, Long id, String name) {

    }

    public void deleteUser(Long groupId, Long id) {

    }
}
