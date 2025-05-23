package io.samsquamptch.afterclass;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.samsquamptch.afterclass.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

public class UserApiTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    private GroupService service;
}
