package com.endava.demo.dao.impl;

import com.endava.demo.dao.InternDAO;
import com.endava.demo.entity.Intern;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public class InternDAOImpl implements InternDAO {


    public static List<Intern> internList = new ArrayList<>();

    private static Session currentSession;

    private static Transaction curentTransacion;

    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = new Configuration().addAnnotatedClass(Intern.class).buildSessionFactory();
        return sessionFactory;
    }

    public static Session openSession() {
        return currentSession = getSessionFactory().openSession();
    }

    public static Transaction beginTransaction() {
        curentTransacion = currentSession.beginTransaction();
        return curentTransacion;
    }

    public static void closeTrazaction() {
        beginTransaction().commit();
    }

    @Override
    public List<Intern> findAll() {

        internList = openSession().createQuery("SELECT c FROM Intern c", Intern.class).getResultList();

        openSession().close();

        return internList;
    }

    @Override
    public void save(Intern intern) {
        openSession().persist(intern);
        closeTrazaction();
        openSession().close();

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
        currentSession = openSession();
        curentTransacion = beginTransaction();
        Intern intern = currentSession.get(Intern.class, id);
        currentSession.delete(intern);
        curentTransacion.commit();
        currentSession.close();
        getSessionFactory().close();
    }

    public Optional<Intern> getInternById(int id) {
        currentSession = openSession();
        return currentSession
                .createQuery("SELECT u FROM Intern u " +
                        "WHERE id=:uId", Intern.class)
                .setParameter("uId", id)
                .getResultList().stream().findFirst();

    }

    @Override
    public void update(Intern intern) {
        openSession().merge(intern);
        closeTrazaction();
    }

}

