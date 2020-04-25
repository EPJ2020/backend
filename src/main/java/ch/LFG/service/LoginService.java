package ch.LFG.service;

import ch.LFG.entity.Appuser;
import ch.LFG.entity.Userlogin;
import ch.LFG.repository.LoginRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private UserService userService;

    public void createUserLogin(Userlogin login) {
        loginRepository.save(login);
    }

    public Userlogin getUserLogin(long id) {
        return loginRepository.getOne(id);
    }

    public void addUserToLogin(Userlogin login, Appuser user) {

    }

}
