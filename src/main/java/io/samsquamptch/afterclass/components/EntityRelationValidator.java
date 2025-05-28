package io.samsquamptch.afterclass.components;

import io.samsquamptch.afterclass.repositories.LessonRepository;
import io.samsquamptch.afterclass.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class EntityRelationValidator {

    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;

    public EntityRelationValidator(UserRepository userRepository, LessonRepository lessonRepository) {
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
    }

    public void validateUserToGroup(Long groupId, Long userId) {
        boolean isValid = userRepository.existsByIdAndGroupId(userId, groupId);
        if (!isValid) {
            throw new IllegalArgumentException("User does not belong to group");
        }
    }

    public void validateLessonToUser(Long userId, Long lessonId) {
        boolean isValid = lessonRepository.existsByIdAndUserId(lessonId, userId);
        if (!isValid) {
            throw new IllegalArgumentException("Lesson does not belong to user");
        }
    }
}
