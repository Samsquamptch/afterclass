package io.samsquamptch.afterclass.services;

import io.samsquamptch.afterclass.Group;
import io.samsquamptch.afterclass.Lesson;
import io.samsquamptch.afterclass.User;
import io.samsquamptch.afterclass.dto.LessonDTO;
import io.samsquamptch.afterclass.dto.LessonRequestDTO;
import io.samsquamptch.afterclass.exception.NotFoundException;
import io.samsquamptch.afterclass.repositories.GroupRepository;
import io.samsquamptch.afterclass.repositories.LessonRepository;
import io.samsquamptch.afterclass.repositories.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;
    // Replace these with services once they've been set up
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    public LessonService(LessonRepository lessonRepository, UserRepository userRepository, GroupRepository groupRepository) {
        this.lessonRepository = lessonRepository;
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    public LessonDTO createLesson(String passCode, Long userId, LessonRequestDTO request) {
        Group group = groupRepository.findByPassCode(passCode)
                .orElseThrow(() -> new NotFoundException("Group not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (user.getGroup().getId() != group.getId()) {
            throw new IllegalArgumentException("User does not belong to the group");
        }

        Lesson lesson  = new Lesson(request.getName(), request.getWeekDay(), request.getStartTime(), request.getEndTime());
        lesson.setUser(user);
        Lesson savedLesson = lessonRepository.save(lesson);
        return new LessonDTO(savedLesson.getId(),
                savedLesson.getName(),
                savedLesson.getWeekDay(),
                savedLesson.getStartTime(),
                savedLesson.getEndTime());
    }

    public LessonDTO getLesson(String passCode, Long userId, long lessonId) {
        return null;
    }

    public List<LessonDTO> getAllLessons(String passCode, Long userId) {
        return null;
    }

    public void updateLesson(String passCode, Long userId, Long lessonId, LessonRequestDTO requestDTO) {
    }

    public void deleteLesson(String passCode, Long userId, long lessonId) {
    }
}
