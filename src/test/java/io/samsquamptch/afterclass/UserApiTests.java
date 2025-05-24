package io.samsquamptch.afterclass;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.samsquamptch.afterclass.controllers.GroupController;
import io.samsquamptch.afterclass.dto.UserRequestDTO;
import io.samsquamptch.afterclass.dto.UserDTO;
import io.samsquamptch.afterclass.services.UserService;
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

@ExtendWith(SpringExtension.class)
@WebMvcTest(GroupController.class)
public class UserApiTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    private UserService service;

    @Test
    void testCreateUser() throws Exception {
        UserRequestDTO request = new UserRequestDTO("Cian");
        String json = objectMapper.writeValueAsString(request);

        UserDTO testDTO = new UserDTO(1L, "Cian", null);

        when(service.createUser("Cian")).thenReturn(testDTO);

        mvc.perform(post("/api/groups/zXXtpQ/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpectAll(status().isCreated(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.user.id").value(1L),
                        jsonPath("$.user.name").value("Cian"));
    }

    @Test
    void testGetAllUsers() throws Exception {

    }

    @Test
    void testGetUser() throws Exception {

    }

    @Test
    void testUpdateUser() throws Exception {

    }

    @Test
    void testDeleteUser() throws Exception {

    }
}
