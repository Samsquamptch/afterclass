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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
        mvc.perform(post("/api/groups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetGroup() throws Exception {
        GroupDTO testDTO = new GroupDTO(1, "Test Group", "zXXtpQ", null);

        // mock the service to return the DTO when called with the passcode
        when(service.getGroupByPasscode("zXXtpQ")).thenReturn(testDTO);

        mvc.perform(get("/api/groups/zXXtpQ")).andExpectAll(status().isOk(),
                content().contentType(MediaType.APPLICATION_JSON),
                jsonPath("$.group.id").value(1),
                jsonPath("$.group.name").value("Test Grouo"));
    }

    @Test
    void testUpdateGroup() throws Exception {
        GroupRequestDTO request = new GroupRequestDTO("Updated Group");
        String json = objectMapper.writeValueAsString(request);

        GroupDTO testDTO = new GroupDTO(1, "Updated Group", "zXXtpQ", null);

        when(service.updateGroup("zXXtpQ", "Updated Group")).thenReturn(testDTO);

        mvc.perform(put("/api/groups/zXXtpQ")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.group.id").value(1))
                .andExpect(jsonPath("$.group.name").value("Updated Group"));
    }
}
