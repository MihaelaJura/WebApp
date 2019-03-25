package com.endava.demo.repositories;

import com.endava.demo.entity.Intern;

import java.util.List;

public interface InternRepository {
    List<Intern> getAllInterns();

    void add(Intern intern);

    void remove(int id);

    Intern getInternById(int id);

    void update(Intern intern);
}
