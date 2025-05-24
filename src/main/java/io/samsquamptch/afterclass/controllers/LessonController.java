package io.samsquamptch.afterclass.controllers;

import io.samsquamptch.afterclass.dto.LessonDTO;
import io.samsquamptch.afterclass.dto.LessonRequestDTO;
import io.samsquamptch.afterclass.services.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups/{passCode}/users/{userId}/lessons")
public class LessonController {

    LessonService lessonService;

    public LessonController(LessonService lessonService) {this.lessonService = lessonService;}

    @PostMapping()
    public ResponseEntity<LessonDTO> createLesson(@PathVariable String passCode,
                                                 @PathVariable Long userId,
                                                 @RequestBody LessonRequestDTO request) {
        LessonDTO lessonDTO = lessonService.createLesson(passCode, userId, request);
        return new ResponseEntity<>(lessonDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LessonDTO>> getLessons(@PathVariable String passCode,
                                                      @PathVariable Long userId) {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonDTO> getLesson(@PathVariable String passCode,
                          @PathVariable Long userId,
                          @PathVariable Long id) {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateLesson(@PathVariable String passCode,
                             @PathVariable Long userId,
                             @PathVariable Long id,
                             @RequestBody LessonRequestDTO request) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLessons(@PathVariable String passCode,
                              @PathVariable Long userId,
                              @PathVariable Long id,
                              @RequestBody LessonRequestDTO request) {
        return null;
    }
}
