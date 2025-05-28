package io.samsquamptch.afterclass.services;

import io.samsquamptch.afterclass.Lesson;
import io.samsquamptch.afterclass.User;
import io.samsquamptch.afterclass.components.EntityRelationValidator;
import io.samsquamptch.afterclass.dto.LessonDTO;
import io.samsquamptch.afterclass.dto.LessonRequestDTO;
import io.samsquamptch.afterclass.exception.NotFoundException;
import io.samsquamptch.afterclass.repositories.LessonRepository;
import io.samsquamptch.afterclass.repositories.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;
    private final EntityRelationValidator entityRelationValidator;

    public LessonService(LessonRepository lessonRepository,
                         UserRepository userRepository,
                         EntityRelationValidator entityRelationValidator) {
        this.lessonRepository = lessonRepository;
        this.userRepository = userRepository;
        this.entityRelationValidator = entityRelationValidator;
    }

    public LessonDTO createLesson(Long groupId, Long userId, LessonRequestDTO request) {
        entityRelationValidator.validateUserToGroup(groupId, userId);
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

    public LessonDTO getLesson(Long groupId, Long userId, Long lessonId) {
        entityRelationValidator.validateUserToGroup(groupId, userId);
        entityRelationValidator.validateLessonToUser(userId, lessonId);

        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(NotFoundException::new);
        return new LessonDTO(lesson.getId(),
                lesson.getName(),
                lesson.getWeekDay(),
                lesson.getStartTime(),
                lesson.getEndTime());
    }

    public List<LessonDTO> getAllLessons(Long groupId, Long userId) {
        entityRelationValidator.validateUserToGroup(groupId, userId);

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

    public void updateLesson(Long groupId, Long userId, Long lessonId, LessonRequestDTO requestDTO) {
        entityRelationValidator.validateUserToGroup(groupId, userId);
        entityRelationValidator.validateLessonToUser(userId, lessonId);

        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(NotFoundException::new);
        lesson.setName(requestDTO.getName());
        lesson.setWeekDay(requestDTO.getWeekDay());
        lesson.setStartTime(requestDTO.getStartTime());
        lesson.setEndTime(requestDTO.getEndTime());
        lessonRepository.save(lesson);
    }

    public void deleteLesson(Long groupId, Long userId, Long lessonId) {
        entityRelationValidator.validateUserToGroup(groupId, userId);
        entityRelationValidator.validateLessonToUser(userId, lessonId);
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(NotFoundException::new);
        lessonRepository.delete(lesson);
    }
}
