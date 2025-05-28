package io.samsquamptch.afterclass.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.samsquamptch.afterclass.components.SessionValidator;
import io.samsquamptch.afterclass.controllers.GroupController;
import io.samsquamptch.afterclass.dto.GroupRequestDTO;
import io.samsquamptch.afterclass.dto.GroupDTO;
import io.samsquamptch.afterclass.services.GroupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.http.MediaType;

import java.util.ArrayList;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GroupController.class)
@Import(SessionValidator.class)
public class GroupControllerTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    private GroupService service;

    private GroupRequestDTO request;
    private GroupDTO testDTO;

    @BeforeEach
    public void setup() {
        request = new GroupRequestDTO("Test Group");
        testDTO = new GroupDTO(1L, "Test Group", "zXXtpQ", new ArrayList<>());
    }

    @Test
    void testCreateGroup() throws Exception {
        String json = objectMapper.writeValueAsString(request);

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
        when(service.getGroup(1L)).thenReturn(testDTO);

        mvc.perform(get("/api/groups")
                        .sessionAttr("groupId", 1L))
                .andExpectAll(status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.id").value(1L),
                        jsonPath("$.name").value("Test Group"),
                        jsonPath("$.passCode").value("zXXtpQ"));
    }

    @Test
    void testUpdateGroup() throws Exception {
        String json = objectMapper.writeValueAsString(request);

        doNothing().when(service).updateGroup(1L, "Test Group");

        mvc.perform(put("/api/groups")
                        .sessionAttr("groupId", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteGroup() throws Exception {
        doNothing().when(service).deleteGroup(1L);

        mvc.perform(delete("/api/groups")
                        .sessionAttr("groupId", 1L))
                .andExpect(status().isNoContent());
    }
}
