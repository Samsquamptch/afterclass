package io.samsquamptch.afterclass;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.samsquamptch.afterclass.controllers.LessonController;
import io.samsquamptch.afterclass.dto.LessonDTO;
import io.samsquamptch.afterclass.dto.LessonRequestDTO;
import io.samsquamptch.afterclass.services.LessonService;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.http.MediaType;

import java.time.LocalTime;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LessonController.class)
public class LessonApiTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    private LessonService service;

    @Test
    void testCreateLesson() throws Exception {
        LessonRequestDTO request = new LessonRequestDTO("IDAR",
                WeekDay.TUESDAY,
                LocalTime.of(18, 0),
                LocalTime.of(21, 0));
        String json = objectMapper.writeValueAsString(request);

        LessonDTO testDTO = new LessonDTO(1L,
                "IDAR",
                WeekDay.TUESDAY,
                LocalTime.of(18, 0),
                LocalTime.of(21, 0));

        when(service.createLesson("zXXtpQ", 1L, request)).thenReturn(testDTO);
        mvc.perform(post("/api/groups/zXXtpQ/users/1/lessons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpectAll(status().isCreated(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.lesson.id").value(1L),
                        jsonPath("$.lesson.name").value("IDAR"),
                        jsonPath("$.lesson.weekDay").value(WeekDay.TUESDAY),
                        jsonPath("$.lesson.startTime").value(LocalTime.of(18, 0)),
                        jsonPath("$.lesson.endTime").value(LocalTime.of(21, 0)));
    }

    @Test
    void testGetAllLessons() throws Exception {

    }

    @Test
    void testGetLesson() throws Exception {
        LessonDTO testDTO = new LessonDTO(1L,
                "IDAR",
                WeekDay.TUESDAY,
                LocalTime.of(18, 0),
                LocalTime.of(21, 0));

        when(service.getLesson(1L)).thenReturn(testDTO);

        mvc.perform(get("/api/groups/zXXtpQ/users/1/lessons/1"))
                .andExpectAll(status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.lesson.id").value(1L),
                        jsonPath("$.lesson.name").value("IDAR"),
                        jsonPath("$.lesson.weekDay").value(WeekDay.TUESDAY),
                        jsonPath("$.lesson.startTime").value(LocalTime.of(18, 0)),
                        jsonPath("$.lesson.endTime").value(LocalTime.of(21, 0)));
    }

    @Test
    void testUpdateLesson() throws Exception {

    }

    @Test
    void testDeleteLesson() throws Exception {

    }
}
