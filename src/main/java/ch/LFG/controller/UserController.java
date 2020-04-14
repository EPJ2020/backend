package ch.LFG.controller;

import ch.LFG.entity.Appuser;
import ch.LFG.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;

    //further commands see https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Annotations#OpenAPIDefinition
    @Operation(summary = "Get Users", description = "Get list of users")
    @GetMapping
    public List<Appuser> findAll() {
        return userService.getAll();
    }

    @Operation(summary = "Get Users by id", description = "Get one user by his ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Appuser getUserProfile(@PathVariable int id){
        return userService.getUserProfil(id);
    }

    @RequestMapping(value="/set", method = RequestMethod.PUT)
    public void setUserProfil(@RequestBody Appuser user) {
        userService.setUserProfil(user);
    }


}
