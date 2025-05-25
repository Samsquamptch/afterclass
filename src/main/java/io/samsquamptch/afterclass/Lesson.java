package io.samsquamptch.afterclass;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@NoArgsConstructor
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    private String name;
    private WeekDay weekDay;
    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Lesson(String name, WeekDay weekDay, LocalTime startTime, LocalTime endTime) {
        this.name = name;
        this.weekDay = weekDay;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
