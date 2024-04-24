package com.example.jpa.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.jpa.entity.Course;
import com.example.jpa.entity.Student;
import com.example.jpa.entity.StudentDetail;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class StudentDAOImpl implements StudentDAO {

    private EntityManager entityManager;

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> getAllStudent() {
        // FROM Class Name (It's not database table!)
        // TypedQuery<Student> query = entityManager.createQuery("FROM Student",
        // Student.class);

        TypedQuery<Student> query = entityManager.createQuery("FROM Student ORDER BY id desc", Student.class);
        return query.getResultList();
    }

    @Override
    public List<Student> findByUsername(String username) {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student WHERE firstName=:data", Student.class);

        query.setParameter("data", username);

        return query.getResultList();
    }

    @Override
    @Transactional
    public void updateUser(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void remove(Integer id) {
        Student before = entityManager.find(Student.class, id);

        entityManager.remove(before);
    }

    @Override
    @Transactional
    public int removeAll() {
        int numRowsDeleted = entityManager.createQuery("DELETE from Student").executeUpdate();

        return numRowsDeleted;
    }

    @Override
    public StudentDetail findStudentDetailById(Integer id) {
        return entityManager.find(StudentDetail.class, id);
    }

    @Override
    @Transactional
    public void removeDetail(Integer id) {
        StudentDetail before = entityManager.find(StudentDetail.class, id);

        // break bi-directional link (supaya ga ikut kehapus Student nya)

        before.getStudent().setStudentDetail(null);

        entityManager.remove(before);
    }

    @Override
    @Transactional
    public void saveCourse(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseByIdJoinFetch(Integer id) {
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c " +
                "JOIN FETCH c.students "
                + "where c.id =:data", Course.class);

        // note that JOIN FETCH could be done multiple JOIN FETCH JOIN FETCH

        query.setParameter("data", id);

        Course course = query.getSingleResult();

        return course;
    }

    @Override
    public Course findCourseAndStudentsById(Integer id) {
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c " +
                "JOIN FETCH c.students "
                + "where c.id =:data", Course.class);

        query.setParameter("data", id);

        Course course = query.getSingleResult();

        return course;
    }

    @Override
    public Student findStudentAndCoursesByStudentId(Integer id) {
        TypedQuery<Student> query = entityManager.createQuery("select s from Student s " +
                "JOIN FETCH s.courses "
                + "where s.id =:data", Student.class);

        query.setParameter("data", id);

        Student student = query.getSingleResult();

        return student;
    }

    @Override
    public Course findCourseById(Integer id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void removeCourse(Integer id) {
        Course before = entityManager.find(Course.class, id);

        entityManager.remove(before);
    }

    @Override
    public Student findStudentByEmail(String email) {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student WHERE email=:data", Student.class);

        query.setParameter("data", email);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void removeCourse(int userId, int courseId) {
        Student tempStudent = entityManager.find(Student.class, userId);
        Course tempCourse = entityManager.find(Course.class, courseId);

        tempStudent.removeCourse(tempCourse);
        entityManager.merge(tempStudent);
    }

}
