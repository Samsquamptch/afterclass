package io.samsquamptch.afterclass.controllers;

import io.samsquamptch.afterclass.components.SessionValidator;
import io.samsquamptch.afterclass.dto.LessonDTO;
import io.samsquamptch.afterclass.dto.LessonRequestDTO;
import io.samsquamptch.afterclass.services.LessonService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    private final LessonService lessonService;
    private final SessionValidator sessionValidator;

    public LessonController(LessonService lessonService, SessionValidator sessionValidator) {
        this.lessonService = lessonService;
        this.sessionValidator = sessionValidator;
    }

    @PostMapping()
    public ResponseEntity<LessonDTO> createLesson(@RequestBody LessonRequestDTO request, HttpSession session) {
        Long groupId = sessionValidator.validateSessionAttribute("groupId", session);
        Long userId = sessionValidator.validateSessionAttribute("userId", session);
        LessonDTO lessonDTO = lessonService.createLesson(groupId, userId, request);
        return new ResponseEntity<>(lessonDTO, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<LessonDTO>> getLessons(HttpSession session) {
        Long groupId = sessionValidator.validateSessionAttribute("groupId", session);
        Long userId = sessionValidator.validateSessionAttribute("userId", session);
        List<LessonDTO> lessonDTOS = lessonService.getAllLessons(groupId, userId);
        return new ResponseEntity<>(lessonDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonDTO> getLesson(@PathVariable Long id, HttpSession session) {
        Long groupId = sessionValidator.validateSessionAttribute("groupId", session);
        Long userId = sessionValidator.validateSessionAttribute("userId", session);
        LessonDTO lessonDTO = lessonService.getLesson(groupId, userId, id);
        return new ResponseEntity<>(lessonDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateLesson(@PathVariable Long id,
                                             @RequestBody LessonRequestDTO request,
                                             HttpSession session) {
        Long groupId = sessionValidator.validateSessionAttribute("groupId", session);
        Long userId = sessionValidator.validateSessionAttribute("userId", session);
        lessonService.updateLesson(groupId, userId, id, request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLessons(@PathVariable Long id, HttpSession session) {
        Long groupId = sessionValidator.validateSessionAttribute("groupId", session);
        Long userId = sessionValidator.validateSessionAttribute("userId", session);
        lessonService.deleteLesson(groupId, userId, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
