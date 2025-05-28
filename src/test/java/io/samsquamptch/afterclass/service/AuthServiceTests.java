package io.samsquamptch.afterclass.service;

import io.samsquamptch.afterclass.services.AuthService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class AuthServiceTests {

    @Autowired
    private AuthService authService;

    @Test
    public void groupAuth() throws Exception {}

    @Test
    public void userAuth() throws Exception {}
}
