package com.endava.demo.service;

import com.endava.demo.entity.Intern;

import java.util.List;
import java.util.Optional;

public interface InternService {
    List<Intern> getAllInterns();

    void add(Intern intern);

    void remove(int id);

    Optional<Intern> getInternById(int id);

    void update(Intern intern);
}
