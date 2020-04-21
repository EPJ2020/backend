package ch.LFG.service;

import ch.LFG.entity.Appgroup;
import ch.LFG.entity.Appuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private JpaRepository<Appgroup, Long> groupRepository;

    public List<Appgroup> getAll(){
        return groupRepository.findAll();
    }

    public Appgroup getGroupProfil(long id){ return groupRepository.getOne(id);}

    public void setGroupProfil(Appgroup group){ groupRepository.save(group);}

    public Appgroup updateGroupProfile(Appgroup group, long id){ groupRepository.save(group); return groupRepository.getOne(id);}
}

