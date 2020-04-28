package ch.lfg.service;

import ch.lfg.entity.Appuser;
import ch.lfg.entity.Userlogin;
import ch.lfg.repository.LoginRepository;

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
