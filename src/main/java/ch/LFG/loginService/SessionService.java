package ch.LFG.loginService;

import ch.LFG.entity.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public class SessionService {

    @Autowired
    private JpaRepository<Login, Long> groupRepository;

    public void registerUser(Login user) {
    }

}
