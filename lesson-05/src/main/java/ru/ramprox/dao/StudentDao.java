package ru.ramprox.dao;

import ru.ramprox.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDao {
    void persist(Student student);
    void delete(Student student);
    void update(Student student);
    Optional<Student> findById(Long id);
    List<Student> findAll();
}
