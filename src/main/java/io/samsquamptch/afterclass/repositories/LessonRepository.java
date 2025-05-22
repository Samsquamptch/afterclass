package io.samsquamptch.afterclass.repositories;

import io.samsquamptch.afterclass.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
