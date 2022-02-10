package ru.ramprox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ramprox.dto.StudentDto;
import ru.ramprox.service.StudentService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String findAll(Model model) {
        List<StudentDto> students = studentService.findAll();
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/new")
    public String newStudent(Model model) {
        model.addAttribute("student", new StudentDto());
        return "student_form";
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable(value = "id") Long id, Model model) {
        Optional<StudentDto> studentDtoOpt = studentService.findById(id);
        StudentDto studentDto = studentDtoOpt.orElseThrow(() -> new RuntimeException("Student not found"));
        model.addAttribute("student", studentDto);
        return "student_form";
    }

    @PostMapping
    public String save(StudentDto studentDto) {
        studentService.save(studentDto);
        return "redirect:/student";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        studentService.deleteById(id);
        return "redirect:/student";
    }
}
