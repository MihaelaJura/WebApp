package com.endava.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Intern {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="age")
    private int age;

    @Column(name="stream")
    @Enumerated(EnumType.STRING)
    private InternStreams stream;

    public Intern(String name, int age, InternStreams stream) {
        this.name = name;
        this.age = age;
        this.stream = stream;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public InternStreams getStream() {
        return stream;
    }

    public void setStream(InternStreams stream) {
        this.stream = stream;
    }

    @Override
    public String toString() {
        return "Intern{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", stream=" + stream +
                '}';
    }



}
