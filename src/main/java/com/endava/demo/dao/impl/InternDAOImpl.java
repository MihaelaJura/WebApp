package com.endava.demo.dao.impl;

import com.endava.demo.dao.InternDAO;
import com.endava.demo.entity.Intern;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public class InternDAOImpl implements InternDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Intern> findAll() {
        return entityManager.createQuery("SELECT c FROM Intern c", Intern.class).getResultList();
    }

    @Override
    @Transactional
    public void save(Intern intern) {
        entityManager.persist(intern);
    }


    @Override
    public void delete(int id) {

        entityManager.createQuery("DELETE FROM Intern u " +
                "WHERE u.id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }

    public Optional<Intern> getInternById(int id) {

        return entityManager
                .createQuery("SELECT u FROM Intern u " +
                        "WHERE id=:uId", Intern.class)
                .setParameter("uId", id)
                .getResultList().stream().findFirst();
    }

    @Override
    public void update(Intern intern) {
        entityManager.merge(intern);
    }

}

