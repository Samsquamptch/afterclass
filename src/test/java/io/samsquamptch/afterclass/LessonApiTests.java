package io.samsquamptch.afterclass;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.samsquamptch.afterclass.controllers.LessonController;
import io.samsquamptch.afterclass.dto.LessonDTO;
import io.samsquamptch.afterclass.dto.LessonRequestDTO;
import io.samsquamptch.afterclass.services.LessonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
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

    private LessonRequestDTO request;
    private LessonDTO testDTO;

    @BeforeEach
    public void setup() {
        request = new LessonRequestDTO("IDAR",
                WeekDay.TUESDAY,
                LocalTime.of(18, 0),
                LocalTime.of(21, 0));
        testDTO = new LessonDTO(1L,
                "IDAR",
                WeekDay.TUESDAY,
                LocalTime.of(18, 0),
                LocalTime.of(21, 0));
    }

    @Test
    void testCreateLesson() throws Exception {
        String json = objectMapper.writeValueAsString(request);

        when(service.createLesson("zXXtpQ", 1L, request)).thenReturn(testDTO);
        mvc.perform(post("/api/groups/zXXtpQ/users/1/lessons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpectAll(status().isCreated(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.id").value(1L),
                        jsonPath("$.name").value("IDAR"),
                        jsonPath("$.weekDay").value("TUESDAY"),
                        jsonPath("$.startTime").value("18:00"),
                        jsonPath("$.endTime").value("21:00"));
    }

    @Test
    void testGetAllLessons() throws Exception {
        LessonDTO testDTO2 = new LessonDTO(2L,
                "Computer Networking",
                WeekDay.WEDNESDAY,
                LocalTime.of(18, 0),
                LocalTime.of(19, 30));
        List<LessonDTO> testDTOs = List.of(testDTO, testDTO2);

        when(service.getAllLessons("zXXtpQ", 1L)).thenReturn(testDTOs);

        mvc.perform(get("/api/groups/zXXtpQ/users/1/lessons"))
                .andExpectAll(status().isOk(),
                        jsonPath("$.size()").value(2),
                        jsonPath("$[0].id").value(1),
                        jsonPath("$[0].name").value("IDAR"),
                        jsonPath("$[0].weekDay").value("TUESDAY"),
                        jsonPath("$[0].startTime").value("18:00"),
                        jsonPath("$[0].endTime").value("21:00"),
                        jsonPath("$[1].id").value(2),
                        jsonPath("$[1].name").value("Computer Networking"),
                        jsonPath("$[1].weekDay").value("WEDNESDAY"),
                        jsonPath("$[1].startTime").value("18:00"),
                        jsonPath("$[1].endTime").value("19:30"));
    }

    @Test
    void testGetLesson() throws Exception {
        when(service.getLesson("zXXtpQ", 1L, 1L)).thenReturn(testDTO);

        mvc.perform(get("/api/groups/zXXtpQ/users/1/lessons/1"))
                .andExpectAll(status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.id").value(1L),
                        jsonPath("$.name").value("IDAR"),
                        jsonPath("$.weekDay").value("TUESDAY"),
                        jsonPath("$.startTime").value("18:00"),
                        jsonPath("$.endTime").value("21:00"));
    }

    @Test
    void testUpdateLesson() throws Exception {
        String json = objectMapper.writeValueAsString(request);

        doNothing().when(service).updateLesson("zXXtpQ", 1L, 1L, request);

        mvc.perform(put("/api/groups/zXXtpQ/users/1/lessons/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteLesson() throws Exception {
        doNothing().when(service).deleteLesson("zXXtpQ", 1L, 1L);

        mvc.perform(delete("/api/groups/zXXtpQ/users/1/lessons/1")).andExpect(status().isNoContent());
    }
}
