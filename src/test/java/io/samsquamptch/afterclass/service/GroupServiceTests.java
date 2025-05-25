package io.samsquamptch.afterclass.service;

import io.samsquamptch.afterclass.services.GroupService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class GroupServiceTests extends AbstractIntegrationTests {

    @Autowired
    private GroupService groupService;

    @Test
    void testCreateGroup() {}

    @Test
    void testGetGroup() {}

    @Test
    void testUpdateGroup() {}

    @Test
    void testDeleteGroup() {}
}
