package io.samsquamptch.afterclass;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.samsquamptch.afterclass.controllers.GroupController;
import io.samsquamptch.afterclass.services.LessonService;
import io.samsquamptch.afterclass.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GroupController.class)
public class LessonApiTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    private LessonService service;

    @Test
    void testCreateLesson() throws Exception {

    }

    @Test
    void testGetAllLessons() throws Exception {

    }

    @Test
    void testGetLesson() throws Exception {

    }

    @Test
    void testUpdateLesson() throws Exception {

    }

    @Test
    void testDeleteLesson() throws Exception {

    }
}
