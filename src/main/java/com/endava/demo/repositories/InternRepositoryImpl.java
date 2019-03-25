package com.endava.demo.repositories;

import com.endava.demo.entity.Intern;
import org.hibernate.SessionFactory;

import java.util.List;

public class InternRepositoryImpl implements InternRepository {

    private SessionFactory sessionFactory;

    public InternRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Intern> getAllInterns() {
        return sessionFactory.openSession().createQuery("SELECT i FROM Intern i ", Intern.class).getResultList();
    }

    @Override
    public void add(Intern intern) {

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public Intern getInternById(int id) {
        return null;
    }

    @Override
    public void update(Intern intern) {

    }
}
