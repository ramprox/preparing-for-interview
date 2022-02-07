package ru.ramprox;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.ramprox.dao.StudentDao;
import ru.ramprox.dao.StudentDaoImpl;
import ru.ramprox.entity.Student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        SessionFactory sessionFactory = SessionFactoryFactory.getSessionFactory();
        StudentDao studentDao = new StudentDaoImpl(sessionFactory);

        // ----- Insert students -------
        Random random = new Random();
        for (int i = 1; i <= 1000; i++) {
            Student student = new Student("student " + i, random.nextInt(5) + 1);
            studentDao.persist(student);
        }

        // ------ Select By Id ---------
        Student student = studentDao.findById(414L).get();
        System.out.println(student);

        // ------ Update ---------------
        student.setName("John");
        studentDao.update(student);

        // ------ Checking update
        student = studentDao.findById(414L).get();
        System.out.println(student);

        // ------ Delete ------------
        studentDao.delete(student);

        // ------ Checking delete -----
        Optional<Student> studentOpt = studentDao.findById(414L);
        studentOpt.ifPresent(System.out::println);

        // ------ Find all students ---------
        List<Student> students = studentDao.findAll();
        System.out.println(students.size());
    }
}
