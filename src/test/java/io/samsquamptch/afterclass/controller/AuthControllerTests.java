package io.samsquamptch.afterclass.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.samsquamptch.afterclass.controllers.AuthController;
import io.samsquamptch.afterclass.dto.AuthDTO;
import io.samsquamptch.afterclass.services.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AuthController.class)
public class AuthControllerTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    AuthService service;

    private AuthDTO authDTO;
    private Long id;
    MockHttpSession session;

    @BeforeEach
    void setUp() {
        authDTO = new AuthDTO("Passcode");
        id = 1L;
        session = new MockHttpSession();
    }

    @Test
    public void testGroupAuth() throws Exception {
        String json = objectMapper.writeValueAsString(authDTO);

        when(service.authenticateGroup("Passcode")).thenReturn(id);

        mvc.perform(get("/api/auth/group")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .session(session))
                .andExpectAll(status().isOk());

        assertEquals(1L, session.getAttribute("groupId"));
    }

    @Test
    public void testUserAuth() throws Exception {
        String json = objectMapper.writeValueAsString(authDTO);

        session.setAttribute("groupId", 1L);

        when(service.authenticateUser("Passcode", 1L)).thenReturn(id);

        mvc.perform(get("/api/auth/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .session(session))
                .andExpectAll(status().isOk());

        assertEquals(1L, session.getAttribute("userId"));
    }

    @Test
    public void testNoGroupAuth() throws Exception {
        String json = objectMapper.writeValueAsString(authDTO);


    }
}
