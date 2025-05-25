package io.samsquamptch.afterclass.services;

import io.samsquamptch.afterclass.Group;
import io.samsquamptch.afterclass.User;
import io.samsquamptch.afterclass.dto.LessonDTO;
import io.samsquamptch.afterclass.dto.UserDTO;
import io.samsquamptch.afterclass.exception.NotFoundException;
import io.samsquamptch.afterclass.repositories.GroupRepository;
import io.samsquamptch.afterclass.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final LessonService lessonService;

    public UserService(UserRepository userRepository, GroupRepository groupRepository, LessonService lessonService) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.lessonService = lessonService;
    }

    public UserDTO createUser(Long groupId, String name) {
        Group group = groupRepository.findById(groupId).orElseThrow(NotFoundException::new);

        User user = new User(name);
        user.setGroup(group);
        User savedUser = userRepository.save(user);
        return new UserDTO(savedUser.getId(), savedUser.getName(), new ArrayList<>());
    }

    public List<UserDTO> getAllUsers(Long groupId) {
        List<User> users = userRepository.findByGroupId(groupId);
        return users.stream().map(u -> new UserDTO(
                        u.getId(),
                        u.getName(),
                        getLessonsFromUser(groupId, u.getId())))
                .collect(Collectors.toList());
    }

    public UserDTO getUser(Long groupId, Long id) {
        validateUserAndGroup(groupId, id);
        User user = userRepository.findById(id).orElseThrow(NotFoundException::new);

        return new UserDTO(user.getId(), user.getName(), getLessonsFromUser(groupId, id));
    }

    public void updateUser(Long groupId, Long id, String name) {
        validateUserAndGroup(groupId, id);
        User user = userRepository.findById(id).orElseThrow(NotFoundException::new);
        user.setName(name);
        userRepository.save(user);
    }

    public void deleteUser(Long groupId, Long id) {
        validateUserAndGroup(groupId, id);
        userRepository.deleteById(id);
    }

    private void validateUserAndGroup(Long groupId, Long userId) {
        boolean isValid = userRepository.existsByIdAndGroupId(userId, groupId);
        if (!isValid) {
            throw new IllegalArgumentException("User does not belong to group");
        }
    }

    private List<LessonDTO> getLessonsFromUser(Long groupId, Long userId) {
        return lessonService.getAllLessons(groupId, userId);
    }
}
