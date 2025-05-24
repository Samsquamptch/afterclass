package io.samsquamptch.afterclass.dto;

import io.samsquamptch.afterclass.WeekDay;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonDTO {
    private long id;
    private String name;
    private WeekDay weekDay;
    private LocalTime startTime;
    private LocalTime endTime;
}
