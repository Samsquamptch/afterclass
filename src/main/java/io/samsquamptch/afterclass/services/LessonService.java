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

    public LessonDTO getLesson(long lessonId) {
        return null;
    }

    public List<LessonDTO> getAllLessons(long userId) {
        return null;
    }

    public LessonDTO updateLesson(long lessonId, LessonDTO lessonDTO) {
        return null;
    }

    public ResponseEntity<Void> deleteLesson(long lessonId) {
        return null;
    }
}
