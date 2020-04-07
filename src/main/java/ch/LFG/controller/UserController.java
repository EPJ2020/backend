package ch.LFG.controller;

import ch.LFG.entity.User;
import ch.LFG.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public List<User> findAll() {
        return userService.getAll();
    }
}
