package ch.LFG.service;

import ch.LFG.entity.Appuser;
import ch.LFG.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private JpaRepository<Appuser, Long> userRepository;

    public List<Appuser> getAll(){
        return userRepository.findAll();
    }

    public Appuser getUserProfil(long id){ return userRepository.getOne(id);}

    public void setUserProfil(Appuser user){ userRepository.save(user);}
}

