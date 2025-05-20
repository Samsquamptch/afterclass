package io.samsquamptch.afterclass;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {
    @PostMapping()
    public String createClass() {
        return null;
    }

    @PutMapping("/{id}")
    public void updateLesson() {}

    @DeleteMapping("/{id}")
    public void deleteLessons() {}

    @GetMapping("/{id}")
    public void getLesson() {}
}
