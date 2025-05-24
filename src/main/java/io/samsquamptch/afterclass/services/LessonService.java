package io.samsquamptch.afterclass.services;

import io.samsquamptch.afterclass.Lesson;
import io.samsquamptch.afterclass.dto.LessonDTO;
import io.samsquamptch.afterclass.dto.LessonRequestDTO;
import io.samsquamptch.afterclass.repositories.LessonRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class LessonService {

    private final LessonRepository lessonRepository;

    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public LessonDTO createLesson(String passCode, Long userId, LessonRequestDTO requestDTO) {
        return null;
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
