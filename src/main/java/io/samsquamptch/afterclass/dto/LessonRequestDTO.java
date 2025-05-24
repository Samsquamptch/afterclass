package io.samsquamptch.afterclass.dto;

import io.samsquamptch.afterclass.WeekDay;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonRequestDTO {
    private String name;
    private WeekDay weekDay;
    private LocalTime startTime;
    private LocalTime endTime;
}
