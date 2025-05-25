package io.samsquamptch.afterclass.service;

import io.samsquamptch.afterclass.Group;
import io.samsquamptch.afterclass.Lesson;
import io.samsquamptch.afterclass.User;
import io.samsquamptch.afterclass.WeekDay;
import io.samsquamptch.afterclass.dto.LessonDTO;
import io.samsquamptch.afterclass.dto.LessonRequestDTO;
import io.samsquamptch.afterclass.exception.NotFoundException;
import io.samsquamptch.afterclass.repositories.GroupRepository;
import io.samsquamptch.afterclass.repositories.LessonRepository;
import io.samsquamptch.afterclass.repositories.UserRepository;
import io.samsquamptch.afterclass.services.LessonService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
public class LessonServiceTests {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        Group group = new Group("Test Group", "zQxprL");
        Group savedGroup = groupRepository.saveAndFlush(group);
        User user = new User("Chris");
        user.setGroup(savedGroup);
        User savedUser = userRepository.saveAndFlush(user);
        Lesson lesson1 = new Lesson("Business Law",
                WeekDay.FRIDAY,
                LocalTime.of(18,0),
                LocalTime.of(21,0));
        Lesson lesson2 = new Lesson("Finance",
                WeekDay.WEDNESDAY,
                LocalTime.of(18,0),
                LocalTime.of(19,30));
        lesson1.setUser(savedUser);
        lesson2.setUser(savedUser);
        lessonRepository.saveAndFlush(lesson1);
        lessonRepository.saveAndFlush(lesson2);
    }

    @AfterEach
    void resetAutoIncrement() {
        jdbcTemplate.execute("ALTER TABLE lessons ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE users ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE groups ALTER COLUMN id RESTART WITH 1");
    }


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
        assertEquals("Business Law", lessonDTOs.get(0).getName());
        assertEquals(WeekDay.FRIDAY, lessonDTOs.get(0).getWeekDay());
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
