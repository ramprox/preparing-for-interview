package ru.ramprox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ramprox.dto.StudentDto;
import ru.ramprox.entity.Student;
import ru.ramprox.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    @Override
    public void save(StudentDto studentDto) {
        Student student = new Student(studentDto.getId(),
                studentDto.getName(), studentDto.getAge());
        studentRepository.save(student);
    }

    @Override
    public Optional<StudentDto> findById(Long id) {
        return studentRepository.findById(id)
                .map(student -> new StudentDto(student.getId(),
                        student.getName(), student.getAge()));
    }

    @Override
    public List<StudentDto> findAll() {
        List<StudentDto> students = new ArrayList<>();
        studentRepository.findAll()
                .forEach(student -> students.add(new StudentDto(student.getId(),
                        student.getName(), student.getAge())));
        return students;
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }
}
