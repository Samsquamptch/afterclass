package io.samsquamptch.afterclass.service;

import io.samsquamptch.afterclass.exception.UnauthorisedException;
import io.samsquamptch.afterclass.services.AuthService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class AuthServiceTests extends AbstractIntegrationTests {

    @Autowired
    private AuthService authService;

    @Test
    public void groupAuth() throws Exception {
        Long groupId = authService.authenticateGroup("zQxprL");
        assertEquals(1L, groupId);
    }

    @Test
    public void userAuth() throws Exception {
        Long userId = authService.authenticateUser("jTRcmD", 1L);
        assertEquals(2L, userId);
    }

    @Test
    public void unauthorizedAuth() throws Exception {
        Exception exception = assertThrows(UnauthorisedException.class, () -> {
            authService.authenticateGroup("Pass");
        });

        assertEquals("No group with passCode found", exception.getMessage());
    }
}
