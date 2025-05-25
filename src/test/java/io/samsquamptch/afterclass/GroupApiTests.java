package io.samsquamptch.afterclass;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.samsquamptch.afterclass.controllers.GroupController;
import io.samsquamptch.afterclass.dto.GroupRequestDTO;
import io.samsquamptch.afterclass.dto.GroupDTO;
import io.samsquamptch.afterclass.services.GroupService;
import org.junit.jupiter.api.BeforeEach;
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

    private GroupRequestDTO request;
    private GroupDTO testDTO;

    @BeforeEach
    public void setup() {
        request = new GroupRequestDTO("Test Group");
        testDTO = new GroupDTO(1L, "Test Group", "zXXtpQ", null);
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

        mvc.perform(get("/api/groups/1"))
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

        mvc.perform(put("/api/groups/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteGroup() throws Exception {
        doNothing().when(service).deleteGroup(1L);

        mvc.perform(delete("/api/groups/1")).andExpect(status().isNoContent());
    }
}
