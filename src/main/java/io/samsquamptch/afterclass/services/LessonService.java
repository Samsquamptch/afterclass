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
import java.util.stream.Collectors;

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
        validateUserAndGroup(passCode, userId);
        User user = userRepository.findById(userId).orElseThrow(NotFoundException::new);

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
        validateUserAndGroup(passCode, userId);

        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(NotFoundException::new);
        return new LessonDTO(lesson.getId(),
                lesson.getName(),
                lesson.getWeekDay(),
                lesson.getStartTime(),
                lesson.getEndTime());

    }

    public List<LessonDTO> getAllLessons(String passCode, Long userId) {
        validateUserAndGroup(passCode, userId);

        List<Lesson> lessons = lessonRepository.findByUserId(userId);
        return lessons.stream()
                .map(lesson -> new LessonDTO(
                        lesson.getId(),
                        lesson.getName(),
                        lesson.getWeekDay(),
                        lesson.getStartTime(),
                        lesson.getEndTime()))
                .collect(Collectors.toList());
    }

    public void updateLesson(String passCode, Long userId, Long lessonId, LessonRequestDTO requestDTO) {
    }

    public void deleteLesson(String passCode, Long userId, long lessonId) {
    }

    private void validateUserAndGroup(String passCode, Long userId) {
        Group group = groupRepository.findByPassCode(passCode)
                .orElseThrow(() -> new NotFoundException("Group not found"));

        boolean isValid = userRepository.existsByIdAndGroupId(userId, group.getId());
        if (!isValid) {
            throw new IllegalArgumentException("User does not belong to the group");
        }
    }
}
