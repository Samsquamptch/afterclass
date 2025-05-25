package io.samsquamptch.afterclass.service;

import io.samsquamptch.afterclass.Lesson;
import io.samsquamptch.afterclass.WeekDay;
import io.samsquamptch.afterclass.dto.LessonDTO;
import io.samsquamptch.afterclass.dto.LessonRequestDTO;
import io.samsquamptch.afterclass.exception.NotFoundException;
import io.samsquamptch.afterclass.services.LessonService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
public class LessonServiceTests extends AbstractIntegrationTests {

    @Autowired
    private LessonService lessonService;

    @Test
    public void GetLesson() {
        LessonDTO lessonDTO = lessonService.getLesson(1L, 1L, 1L);
        assertEquals("Business Law", lessonDTO.getName());
        assertEquals(WeekDay.FRIDAY, lessonDTO.getWeekDay());
        assertEquals(LocalTime.of(18,0), lessonDTO.getStartTime());
        assertEquals(LocalTime.of(21,0), lessonDTO.getEndTime());
    }

    @Test
    public void GetAllLessons() {
        List<LessonDTO> lessonDTOs = lessonService.getAllLessons(1L, 1L);
        assertEquals(2, lessonDTOs.size());
        assertEquals("Business Law", lessonDTOs.getFirst().getName());
        assertEquals(WeekDay.FRIDAY, lessonDTOs.getFirst().getWeekDay());
        assertEquals(LocalTime.of(18,0), lessonDTOs.get(0).getStartTime());
        assertEquals(LocalTime.of(21,0), lessonDTOs.get(0).getEndTime());
        assertEquals("Finance", lessonDTOs.get(1).getName());
        assertEquals(WeekDay.WEDNESDAY, lessonDTOs.get(1).getWeekDay());
        assertEquals(LocalTime.of(18,0), lessonDTOs.get(1).getStartTime());
        assertEquals(LocalTime.of(19,30), lessonDTOs.get(1).getEndTime());
    }

    @Test
    public void AddLesson() {
        LessonRequestDTO lessonRequestDTO = new LessonRequestDTO("Marketing",
                WeekDay.TUESDAY,
                LocalTime.of(19,30),
                LocalTime.of(21,0));
        LessonDTO lessonDTO = lessonService.createLesson(1L, 1L, lessonRequestDTO);
        assertEquals("Marketing", lessonDTO.getName());
        assertEquals(WeekDay.TUESDAY, lessonDTO.getWeekDay());
        assertEquals(LocalTime.of(19,30), lessonDTO.getStartTime());
        assertEquals(LocalTime.of(21,0), lessonDTO.getEndTime());
        assertTrue(lessonRepository.existsById(lessonDTO.getId()));
    }

    @Test
    public void UpdateLesson() {
        LessonRequestDTO lessonRequestDTO = new LessonRequestDTO("Business Law",
                WeekDay.MONDAY,
                LocalTime.of(19,30),
                LocalTime.of(21,0));
        lessonService.updateLesson(1L,1L,1L, lessonRequestDTO);
        Lesson lesson = lessonRepository.findById(1L).orElseThrow(NotFoundException::new);
        assertEquals(WeekDay.MONDAY, lesson.getWeekDay());
        assertEquals(LocalTime.of(19,30), lesson.getStartTime());
    }

    @Test
    public void DeleteLesson() {
        assertTrue(lessonRepository.existsById(1L));
        lessonService.deleteLesson(1L, 1L, 1L);
        assertFalse(lessonRepository.existsById(1L));
    }
}
