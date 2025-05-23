package io.samsquamptch.afterclass;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    private String name;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Lesson> lessons = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }

    public void addLesson(Lesson lesson) {this.lessons.add(lesson);}

    public void removeLesson(Lesson lesson) {this.lessons.remove(lesson);}
}
