package ch.LFG.service;

import ch.LFG.entity.Appuser;
import ch.LFG.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<Appuser> getAll(){
        return userRepository.findAll();
    }
}

