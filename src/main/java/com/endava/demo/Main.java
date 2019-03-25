package com.endava.demo;

import com.endava.demo.entity.Intern;
import com.endava.demo.repositories.InternRepository;
import com.endava.demo.repositories.InternRepositoryImpl;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Intern.class)
                .buildSessionFactory();
        InternRepository internRepository = new InternRepositoryImpl(sessionFactory);

        List<Intern> l = internRepository.getAllInterns();
        System.out.println(l);
    }
}
