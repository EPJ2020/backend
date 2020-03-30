package ch.LFG.controller;

import ch.LFG.entity.User;
import ch.LFG.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void findAll() {
        final User user1 = new User();
        user1.setName("Abrams");
        user1.setfirstName("William");
        user1.setId(1);

        final User user2 = new User();
        user2.setName("Lord");
        user2.setfirstName("Wilhelm");
        user2.setId(2);

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        given(userController.findAll()).willReturn(users);

        List<User> expected = userService.getAll();

        assertEquals(expected, users);
    }


}