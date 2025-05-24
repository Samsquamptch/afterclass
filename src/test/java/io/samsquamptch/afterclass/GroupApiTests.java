package io.samsquamptch.afterclass;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.samsquamptch.afterclass.controllers.GroupController;
import io.samsquamptch.afterclass.dto.GroupRequestDTO;
import io.samsquamptch.afterclass.dto.GroupDTO;
import io.samsquamptch.afterclass.services.GroupService;
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

@ExtendWith(SpringExtension.class)
@WebMvcTest(GroupController.class)
public class GroupApiTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    private GroupService service;

    @Test
    void testCreateGroup() throws Exception {
        GroupRequestDTO request = new GroupRequestDTO("Test Group");
        String json = objectMapper.writeValueAsString(request);

        GroupDTO testDTO = new GroupDTO(1L, "Test Group", "zXXtpQ", null);

        when(service.createGroup("Test Group")).thenReturn(testDTO);

        mvc.perform(post("/api/groups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpectAll(status().isCreated(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.id").value(1L),
                        jsonPath("$.name").value("Test Group"),
                        jsonPath("$.passCode").value("zXXtpQ"));
    }

    @Test
    void testGetGroup() throws Exception {
        GroupDTO testDTO = new GroupDTO(1L, "Test Group", "zXXtpQ", null);

        when(service.getGroupByPasscode("zXXtpQ")).thenReturn(testDTO);

        mvc.perform(get("/api/groups/zXXtpQ"))
                .andExpectAll(status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.id").value(1L),
                        jsonPath("$.name").value("Test Group"));
    }

    @Test
    void testUpdateGroup() throws Exception {
        GroupRequestDTO request = new GroupRequestDTO("Updated Group");
        String json = objectMapper.writeValueAsString(request);

        doNothing().when(service).updateGroup("zXXtpQ", "Updated Group");

        mvc.perform(put("/api/groups/zXXtpQ")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteGroup() throws Exception {
        doNothing().when(service).deleteGroup("zXXtpQ");

        mvc.perform(delete("/api/groups/zXXtpQ")).andExpect(status().isNoContent());
    }
}
