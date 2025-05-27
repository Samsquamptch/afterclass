package io.samsquamptch.afterclass;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;


public class ObjectsTest {

    @Test
    public void createGroup() {
        Group group = new Group("Birkbeck Socials", "zXctPQlk");

        assertEquals("Birkbeck Socials", group.getName());
        assertEquals("zXctPQlk", group.getPassCode());
    }

    @Test
    public void createUser() {
        User user = new User("Cian", "zQwllP");

        assertEquals("Cian", user.getName());
    }

    @Test
    public void createLesson() {
        WeekDay weekDay = WeekDay.MONDAY;
        Lesson lesson = new Lesson("Data Analytics", weekDay, LocalTime.of(18, 0), LocalTime.of(19, 30));

        assertEquals("Data Analytics", lesson.getName());
        assertEquals(weekDay, lesson.getWeekDay());
        assertEquals(LocalTime.of(18, 0), lesson.getStartTime());
        assertEquals(LocalTime.of(19, 30), lesson.getEndTime());
        assertEquals(90, Duration.between(lesson.getStartTime(), lesson.getEndTime()).toMinutes());
    }

    @Test
    public void createGroupUser() {
        Group group = new Group("Birkbeck Socials", "zXctPQlk");
        User user = new User("Cian", "");
        group.addUser(user);


        assertEquals("Cian", group.getUsers().getFirst().getName());
        assertEquals(1, group.getUsers().size());

        User user2 = new User("Bill","zXct");
        User user3 = new User("Tanjil", "PQlk");
        group.addUser(user2);
        group.addUser(user3);

        assertEquals("Tanjil", group.getUsers().getLast().getName());
        assertEquals(3, group.getUsers().size());
    }

    @Test
    public void createUserLesson() {
        User user = new User("Tanjil", "");
        Lesson lesson = new Lesson("Data Analytics", WeekDay.TUESDAY, LocalTime.of(18, 0), LocalTime.of(19, 30));
        user.addLesson(lesson);

        assertEquals("Data Analytics", user.getLessons().getLast().getName());
        assertEquals(1, user.getLessons().size());

        Lesson lesson2 = new Lesson("SP3", WeekDay.WEDNESDAY, LocalTime.of(18, 0), LocalTime.of(21, 0));
        Lesson lesson3 = new Lesson("Computer Networking", WeekDay.FRIDAY, LocalTime.of(19, 30), LocalTime.of(21, 0));

        user.addLesson(lesson2);
        user.addLesson(lesson3);

        assertEquals("Computer Networking", user.getLessons().getLast().getName());
        assertEquals(3, user.getLessons().size());
    }

    @Test
    public void createGroupUserLesson() {
        Group group = new Group("Birkbeck Socials", "zXctPQlk");
        User user = new User("Ethan", "");
        Lesson lesson = new Lesson("Data Analytics", WeekDay.TUESDAY, LocalTime.of(18, 0), LocalTime.of(19, 30));

        user.addLesson(lesson);
        group.addUser(user);

        assertEquals("Ethan", group.getUsers().getFirst().getName());
        assertEquals("Data Analytics", group.getUsers().getFirst().getLessons().getFirst().getName());
    }
}