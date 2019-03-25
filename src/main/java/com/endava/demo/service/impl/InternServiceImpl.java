package com.endava.demo.service.impl;

import com.endava.demo.dao.InternDAO;
import com.endava.demo.entity.Intern;
import com.endava.demo.service.InternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class InternServiceImpl implements InternService {

    @Autowired
    private InternDAO internDAO;

    @Override
    public List<Intern> getAllInterns() {
        return internDAO.findAll();
    }

    @Override
    public void add(Intern intern) {
        intern.setId(internDAO.getMaxID() + 1);
        internDAO.save(intern);
    }
     @Override
    public void remove(int id)
     {
        internDAO.delete(id);
     }

    @Override
    public Optional<Intern> getInternById(int id){
        return internDAO.getInternById(id);
    }

    @Override
    public void update(Intern intern) {
        internDAO.update(intern);
    }

}
