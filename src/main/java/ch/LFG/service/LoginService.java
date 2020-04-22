package ch.LFG.service;

import ch.LFG.entity.Appgroup;
import ch.LFG.entity.Appuser;
import ch.LFG.entity.Userlogin;
import ch.LFG.repository.GroupRepository;
import ch.LFG.repository.LoginRepository;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public void createUserLogin(Userlogin login) {
        loginRepository.save(login);
    }

}
