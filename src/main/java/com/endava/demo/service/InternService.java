package com.endava.demo.service;

import com.endava.demo.entity.Intern;

import java.util.List;
import java.util.Optional;

public interface InternService {
    public List<Intern> getAllInterns();

    public void add(Intern intern);

    public void remove(int id);

    Optional<Intern> getInternById(int id);

    void update(Intern intern);
}
