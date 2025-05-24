package io.samsquamptch.afterclass.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;
}
