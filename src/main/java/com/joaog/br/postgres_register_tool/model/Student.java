package com.joaog.br.postgres_register_tool.model;

import jakarta.persistence.*;

@Entity
@Table(name="students", indexes = @Index(name = "idx_student_id", columnList = "id"))
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="name", columnDefinition = "VARCHAR(100)", nullable = false)
    private String name;

    @Column(name="course", columnDefinition = "VARCHAR(100)", nullable = false)
    private String course;

    @Column(name="gender", columnDefinition = "VARCHAR(6)", nullable = false)
    private String gender;

    public Student() {
    }

    public Student(int id, String name, String course, String gender) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.gender = gender;
    }

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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
