package ru.ramprox.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.ramprox.entity.Student;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class StudentDaoImpl implements StudentDao {

    private final SessionFactory sessionFactory;

    public StudentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void persist(Student student) {
        executeWithoutResult(session -> session.persist(student));
    }

    @Override
    public void delete(Student student) {
        executeWithoutResult(session -> session.delete(student));
    }

    @Override
    public void update(Student student) {
        executeWithoutResult(session -> session.update(student));
    }

    @Override
    public Optional<Student> findById(Long id) {
        return executeWithResult(session -> Optional.ofNullable(session.find(Student.class, id)));
    }

    @Override
    public List<Student> findAll() {
        return executeWithResult(session ->
                session.createQuery("select s from Student s", Student.class)
                        .list());
    }

    private <T> T executeWithResult(Function<Session, T> function) {
        Session session = sessionFactory.getCurrentSession();
        T result = null;
        try {
            session.beginTransaction();
            result = function.apply(session);
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    private void executeWithoutResult(Consumer<Session> consumer) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            consumer.accept(session);
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}
