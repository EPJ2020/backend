package ch.LFG.controller;

import ch.LFG.entity.Appuser;
import ch.LFG.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/User")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Appuser> findAll() {
        return userService.getAll();
    }
}
