package com.endava.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Intern {
    private int id;
    private String name;
    private int age;
    private InternStreams stream;

    public Intern(String name, int age, InternStreams stream) {
        this.name = name;
        this.age = age;
        this.stream = stream;
    }

//    public Intern(int id, String name, int age, InternStreams stream) {
//        this.id = id;
//        this.name = name;
//        this.age = age;
//        this.stream = stream;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
