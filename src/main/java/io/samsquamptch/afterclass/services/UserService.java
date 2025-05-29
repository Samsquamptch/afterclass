package io.samsquamptch.afterclass.services;

import io.samsquamptch.afterclass.Group;
import io.samsquamptch.afterclass.User;
import io.samsquamptch.afterclass.components.EntityRelationValidator;
import io.samsquamptch.afterclass.dto.CreatedUserDTO;
import io.samsquamptch.afterclass.dto.LessonDTO;
import io.samsquamptch.afterclass.dto.UserDTO;
import io.samsquamptch.afterclass.exception.NotFoundException;
import io.samsquamptch.afterclass.repositories.GroupRepository;
import io.samsquamptch.afterclass.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final LessonService lessonService;
    private final EntityRelationValidator entityRelationValidator;

    public UserService(UserRepository userRepository,
                       GroupRepository groupRepository,
                       LessonService lessonService,
                       EntityRelationValidator entityRelationValidator) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.lessonService = lessonService;
        this.entityRelationValidator = entityRelationValidator;
    }

    public CreatedUserDTO createUser(Long groupId, String name) {
        Group group = groupRepository.findById(groupId).orElseThrow(NotFoundException::new);
        String passCode;
        do {
            passCode = UUID.randomUUID().toString().substring(0, 8);
        } while (userRepository.existsByPassCode(passCode));
        User user = new User(name, passCode);
        user.setGroup(group);
        User savedUser = userRepository.save(user);
        return new CreatedUserDTO(savedUser.getId(), savedUser.getName(), savedUser.getPassCode(), new ArrayList<>());
    }

    public List<UserDTO> getAllUsers(Long groupId) {
        List<User> users = userRepository.findByGroupId(groupId);
        return users.stream().map(u -> new UserDTO(
                        u.getId(),
                        u.getName(),
                        getLessonsFromUser(groupId, u.getId())))
                .collect(Collectors.toList());
    }

    public void updateUser(Long groupId, Long id, String name) {
        entityRelationValidator.validateUserToGroup(groupId, id);
        User user = userRepository.findById(id).orElseThrow(NotFoundException::new);
        user.setName(name);
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long groupId, Long id) {
        entityRelationValidator.validateUserToGroup(groupId, id);
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
    }

    private List<LessonDTO> getLessonsFromUser(Long groupId, Long userId) {
        return lessonService.getAllLessons(groupId, userId);
    }
}
