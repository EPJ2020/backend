package ch.LFG.controller;

import ch.LFG.entity.Appuser;
import ch.LFG.service.UserService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;


    @org.junit.jupiter.api.Test
    void findAll() {
        Appuser user1 = new Appuser();
        user1.setUserId(4);
        user1.setFirstName("Max");
        user1.setLastName("Musterman");
        //user1.setTags(Arrays.asList("aaa", "bbb"));

        Appuser user2 = new Appuser();
        user1.setUserId(4);
        user1.setFirstName("Hans");
        user1.setLastName("Peter");
        //user1.setTags(Arrays.asList("ccc", "ddd"));

        List<Appuser> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        given(userController.findAll()).willReturn(users);

        List<Appuser> expected = userService.getAll();

        assertEquals(expected, users);

    }

    @org.junit.jupiter.api.Test
    void getUserProfile() {
    }

    @org.junit.jupiter.api.Test
    void setUserProfil() {
    }
}