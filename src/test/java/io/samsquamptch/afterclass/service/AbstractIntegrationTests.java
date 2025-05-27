package io.samsquamptch.afterclass.service;

import io.samsquamptch.afterclass.Group;
import io.samsquamptch.afterclass.Lesson;
import io.samsquamptch.afterclass.User;
import io.samsquamptch.afterclass.WeekDay;
import io.samsquamptch.afterclass.repositories.GroupRepository;
import io.samsquamptch.afterclass.repositories.LessonRepository;
import io.samsquamptch.afterclass.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalTime;
import java.util.List;

@SpringBootTest
@Transactional
public abstract class AbstractIntegrationTests {

    @Autowired
    protected LessonRepository lessonRepository;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected GroupRepository groupRepository;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        Group group = new Group("Test Group", "zQxprL");
        Group savedGroup = groupRepository.saveAndFlush(group);
        User user1 = new User("Chris", "GrZAqm");
        User user2 = new User("Seb", "jTRcmD");
        user1.setGroup(savedGroup);
        user2.setGroup(savedGroup);
        User savedUser1 = userRepository.saveAndFlush(user1);
        User savedUser2 = userRepository.saveAndFlush(user2);
        Lesson lesson1 = new Lesson("Business Law",
                WeekDay.FRIDAY,
                LocalTime.of(18,0),
                LocalTime.of(21,0));
        Lesson lesson2 = new Lesson("Finance",
                WeekDay.WEDNESDAY,
                LocalTime.of(18,0),
                LocalTime.of(19,30));
        Lesson lesson3 = new Lesson("Computer Networking",
                WeekDay.WEDNESDAY,
                LocalTime.of(18,0),
                LocalTime.of(21,0));
        savedUser1.addLesson(lesson1);
        savedUser1.addLesson(lesson2);
        savedUser2.addLesson(lesson3);
        lessonRepository.saveAllAndFlush(List.of(lesson1, lesson2, lesson3));
    }

    @AfterEach
    void resetAutoIncrement() {
        jdbcTemplate.execute("ALTER TABLE lessons ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE users ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE groups ALTER COLUMN id RESTART WITH 1");
    }
}
