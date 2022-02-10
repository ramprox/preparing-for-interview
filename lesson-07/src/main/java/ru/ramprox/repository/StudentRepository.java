package ru.ramprox.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ramprox.entity.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
