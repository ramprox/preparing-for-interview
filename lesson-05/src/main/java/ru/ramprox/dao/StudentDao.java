package ru.ramprox.dao;

import ru.ramprox.entity.Student;

import java.util.List;

public interface StudentDao {
    void persist(Student student);
    void delete(Student student);
    void update(Student student);
    Student findById(Long id);
    List<Student> findAll();
}
