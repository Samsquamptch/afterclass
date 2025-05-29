package io.samsquamptch.afterclass.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.samsquamptch.afterclass.components.SessionValidator;
import io.samsquamptch.afterclass.controllers.UserController;
import io.samsquamptch.afterclass.dto.CreatedUserDTO;
import io.samsquamptch.afterclass.dto.UserRequestDTO;
import io.samsquamptch.afterclass.dto.UserDTO;
import io.samsquamptch.afterclass.services.UserService;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@Import(SessionValidator.class)
public class UserControllerTests {
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

        CreatedUserDTO testDTO = new CreatedUserDTO(1L, "Cian", "xPLFar", new ArrayList<>());

        when(service.createUser(1L,"Cian")).thenReturn(testDTO);

        mvc.perform(post("/api/users")
                        .sessionAttr("groupId", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpectAll(status().isCreated(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.id").value(1L),
                        jsonPath("$.name").value("Cian"),
                        jsonPath("$.lessons.size()").value(0),
                        jsonPath("$.passCode").value("xPLFar"));
    }

    @Test
    void testGetAllUsers() throws Exception {
        UserDTO testDTO1 = new UserDTO(1L, "Cian", null);
        UserDTO testDTO2 = new UserDTO(2L, "Tanjil", null);
        UserDTO testDTO3 = new UserDTO(3L, "Chris", null);
        List<UserDTO> testDTOs = List.of(testDTO1, testDTO2, testDTO3);

        when(service.getAllUsers(1L)).thenReturn(testDTOs);

        mvc.perform(get("/api/users")
                        .sessionAttr("groupId", 1L))
                .andExpectAll(status().isOk(),
                        jsonPath("$.size()").value(3),
                        jsonPath("$[0].id").value(1),
                        jsonPath("$[0].name").value("Cian"),
                        jsonPath("$[1].id").value(2),
                        jsonPath("$[1].name").value("Tanjil"),
                        jsonPath("$[2].id").value(3),
                        jsonPath("$[2].name").value("Chris"));
    }

    @Test
    void testUpdateUser() throws Exception {
        UserRequestDTO request = new UserRequestDTO("Seb");
        String json = objectMapper.writeValueAsString(request);

        doNothing().when(service).updateUser(1L, 1L, "Seb");

        mvc.perform(put("/api/users")
                        .sessionAttr("groupId", 1L)
                        .sessionAttr("userId", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteUser() throws Exception {
        doNothing().when(service).deleteUser(1L, 1L);

        mvc.perform(delete("/api/users")
                        .sessionAttr("groupId", 1L)
                        .sessionAttr("userId", 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    void testUnauthorised() throws Exception {
        doNothing().when(service).deleteUser(1L, 1L);

        mvc.perform(delete("/api/users")
                        .sessionAttr("groupId", 1L))
                .andExpect(status().isUnauthorized());
    }
}
