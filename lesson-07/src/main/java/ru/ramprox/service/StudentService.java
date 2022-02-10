package ru.ramprox.service;

import ru.ramprox.dto.StudentDto;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    void save(StudentDto student);

    Optional<StudentDto> findById(Long id);

    List<StudentDto> findAll();

    void deleteById(Long id);
}
