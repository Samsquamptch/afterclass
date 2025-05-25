package io.samsquamptch.afterclass.service;

import io.samsquamptch.afterclass.dto.UserDTO;
import io.samsquamptch.afterclass.services.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UserServiceTests extends AbstractIntegrationTests {

    @Autowired
    private UserService userService;

    @Test
    public void getUser() {
        UserDTO userDTO = userService.getUser(1L, 1L);
        assertEquals("Chris", userDTO.getName());
        assertEquals(1L, userDTO.getId());
        assertEquals(2, userDTO.getLessons().size());
    }

    @Test
    public void getAllUsers() {
        List<UserDTO> userDTOs = userService.getAllUsers(1L);
        assertEquals(2, userDTOs.size());
        assertEquals("Chris", userDTOs.get(0).getName());
        assertEquals(2, userDTOs.get(0).getLessons().size());
        assertEquals("Seb", userDTOs.get(1).getName());
        assertEquals(1, userDTOs.get(1).getLessons().size());
    }

    @Test
    public void addUser() {
        UserDTO userDTO = userService.createUser(1L, "Cian");
        assertEquals("Cian", userDTO.getName());
        assertTrue(userRepository.existsById(userDTO.getId()));
    }

    @Test
    public void updateUser() {
        userService.updateUser(1L, 1L, "Christopher");
        UserDTO userDTO = userService.getUser(1L, 1L);
        assertEquals("Christopher", userDTO.getName());
    }

    @Test
    public void deleteUser() {
        assertTrue(userRepository.existsById(2L));
        userService.deleteUser(1L, 2L);
        assertFalse(userRepository.existsById(2L));
    }
}
