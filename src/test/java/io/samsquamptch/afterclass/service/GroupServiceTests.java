package io.samsquamptch.afterclass.service;

import io.samsquamptch.afterclass.components.EntityRelationValidator;
import io.samsquamptch.afterclass.dto.GroupDTO;
import io.samsquamptch.afterclass.services.GroupService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@Import(EntityRelationValidator.class)
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
        System.out.println(groupDTO.getPassCode());
        assertEquals(2L, groupDTO.getId());
        assertEquals("Birkbeck Socials", groupDTO.getName());
        assertEquals(8, groupDTO.getPassCode().length());
        assertEquals(0, groupDTO.getUsers().size());
    }

    @Test
    void updateGroup() {
        groupService.updateGroup(1L, "Bill Appreciation Society");
        GroupDTO groupDTO = groupService.getGroup(1L);
        assertEquals("Bill Appreciation Society", groupDTO.getName());
    }

    @Test
    void deleteGroup() {
        groupService.deleteGroup(1L);
    }
}
