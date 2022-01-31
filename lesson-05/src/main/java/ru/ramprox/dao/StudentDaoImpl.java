package ru.ramprox.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.ramprox.entity.Student;

import java.util.List;

public class StudentDaoImpl implements StudentDao {

    private final SessionFactory sessionFactory;

    public StudentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void persist(Student student) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(student);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Student student) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(student);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Student student) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(student);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Student findById(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Student student = session.get(Student.class, id);
        session.getTransaction().commit();
        session.close();
        return student;
    }

    @Override
    public List<Student> findAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Student> students = session.createQuery("select s from Student s", Student.class).list();
        session.getTransaction().commit();
        session.close();
        return students;
    }
}
