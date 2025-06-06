package io.samsquamptch.afterclass.repositories;

import io.samsquamptch.afterclass.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findByUserId(Long userId);

    boolean existsByIdAndUserId(Long lessonId, Long userId);
}
