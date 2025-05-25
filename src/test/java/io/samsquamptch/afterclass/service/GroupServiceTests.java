package io.samsquamptch.afterclass.service;

import io.samsquamptch.afterclass.dto.GroupDTO;
import io.samsquamptch.afterclass.services.GroupService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class GroupServiceTests extends AbstractIntegrationTests {

    @Autowired
    private GroupService groupService;

    @Test
    void getGroup() {
        GroupDTO groupDTO = groupService.getGroup(1L);
        assertEquals("Test Group", groupDTO.getName());
        assertEquals("zQxprL", groupDTO.getPassCode());
        assertEquals(2, groupDTO.getUsers().size());
    }

    @Test
    void addGroup() {
        GroupDTO groupDTO = groupService.createGroup("Birkbeck Socials");
        assertEquals(2L, groupDTO.getId());
        assertEquals("Birkbeck Socials", groupDTO.getName());
        assertEquals(6, groupDTO.getPassCode().length());
        assertEquals(0, groupDTO.getUsers().size());
    }


    @Test
    void updateGroup() {}

    @Test
    void deleteGroup() {}
}
