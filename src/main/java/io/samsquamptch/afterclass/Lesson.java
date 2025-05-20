package io.samsquamptch.afterclass;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class Lesson {
    private String name;
    private int id;
    private WeekDay weekDay;
    private LocalTime startTime;
    private LocalTime endTime;
}
