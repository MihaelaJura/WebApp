package com.endava.demo.service;

import com.endava.demo.entity.Intern;

import java.util.List;

public interface InternService {
    public List<Intern> getAllInterns();

    public void add(Intern intern);

    public void remove(int id);

    Intern getInternById(int id);

    void update(Intern intern);
}
