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
}
