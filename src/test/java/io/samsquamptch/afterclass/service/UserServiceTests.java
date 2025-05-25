package io.samsquamptch.afterclass.service;

import io.samsquamptch.afterclass.services.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class UserServiceTests extends AbstractIntegrationTests {

    @Autowired
    private UserService userService;

    @Test
    public void getUser() {}

    @Test
    public void getAllUsers() {}

    @Test
    public void addUser() {}

    @Test
    public void updateUser() {}

    @Test
    public void deleteUser() {}
}
