package com.endava.demo.dao.impl;

import com.endava.demo.dao.InternDAO;
import com.endava.demo.entity.Intern;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static com.endava.demo.entity.InternStreams.ANALYST;
import static com.endava.demo.entity.InternStreams.JAVA;

@Repository
public class InternDAOImpl implements InternDAO {

    private SessionFactory sessionFactory;

    private static List<Intern> internList = new ArrayList<>();

    static {
        internList.add(new Intern(1, "Mihaela", 21, JAVA));
        internList.add(new Intern(2, "Eugen", 18, JAVA));
        internList.add(new Intern(3, "Xenia", 19, JAVA));
        internList.add(new Intern(4, "Denisa", 21, ANALYST));
    }

    @Override
    public List<Intern> findAll() {
        return internList;
    }

    @Override
    public void save(Intern intern) {
        internList.add(intern);

    }

    @Override
    public int getMaxID() {
        return internList
                .stream()
                .max(Comparator.comparingInt(Intern::getId))
                .get()
                .getId();
    }

    @Override
    public void delete(int id) {
        for (Intern i : new ArrayList<>(internList))
        {
            if (i.getId() == id)
                internList.remove(i);
        }
    }

    public Optional<Intern> getInternById(int id) {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT u FROM intern u " +
                        "WHERE id=:uId", Intern.class)
                .setParameter("uId", id)
                .getResultList().stream().findFirst();
    }

    @Override
    public void update(Intern intern) {
//        getInternById(intern.getId()).setName(intern.getName());
//        getInternById(intern.getId()).setAge(intern.getAge());
//        getInternById(intern.getId()).setStream(intern.getStream());
    }

    }

