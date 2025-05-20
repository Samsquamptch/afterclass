package io.samsquamptch.afterclass;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class User {
    @Setter
    private String name;
    @Setter
    private int id;
    private List<Lesson> lessons = new ArrayList<Lesson>();

    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public void addLesson(Lesson lesson) {this.lessons.add(lesson);}

    public void removeLesson(Lesson lesson) {this.lessons.remove(lesson);}
}
