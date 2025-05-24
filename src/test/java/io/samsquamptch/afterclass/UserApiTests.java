package io.samsquamptch.afterclass;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.samsquamptch.afterclass.controllers.UserController;
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

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
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

        when(service.createUser("zXXtpQ","Cian")).thenReturn(testDTO);

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
        UserDTO testDTO1 = new UserDTO(1L, "Cian", null);
        UserDTO testDTO2 = new UserDTO(2L, "Tanjil", null);
        UserDTO testDTO3 = new UserDTO(3L, "Chris", null);
        List<UserDTO> testDTOs = List.of(testDTO1, testDTO2, testDTO3);

        when(service.getAllUsers("zXXtpQ")).thenReturn(testDTOs);

        mvc.perform(get("/api/groups/zXXtpQ/users"))
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
    void testGetUser() throws Exception {
        UserDTO testDTO = new UserDTO(1L, "Cian", null);

        when(service.getUser("zXXtpQ", 1L)).thenReturn(testDTO);

        mvc.perform(get("/api/groups/zXXtpQ/users/1"))
                .andExpectAll(status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.user.id").value(1L),
                        jsonPath("$.user.name").value("Cian"));
    }

    @Test
    void testUpdateUser() throws Exception {
        UserRequestDTO request = new UserRequestDTO("Seb");
        String json = objectMapper.writeValueAsString(request);

        doNothing().when(service).updateUser("zXXtpQ", 1L, "Seb");

        mvc.perform(put("/api/groups/zXXtpQ/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteUser() throws Exception {
        doNothing().when(service).deleteUser("zXXtpQ", 1L);

        mvc.perform(delete("/api/groups/zXXtpQ/users/1")).andExpect(status().isNoContent());
    }
}
