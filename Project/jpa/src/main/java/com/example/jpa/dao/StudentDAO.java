package com.example.jpa.dao;

import java.util.List;

import com.example.jpa.entity.Course;
import com.example.jpa.entity.Student;
import com.example.jpa.entity.StudentDetail;

public interface StudentDAO {

    public void save(Student student);

    public Student findById(Integer id);

    public List<Student> getAllStudent();

    public List<Student> findByUsername(String username);

    public void remove(Integer id);

    public int removeAll();

    public StudentDetail findStudentDetailById(Integer id);

    public void removeDetail(Integer id);

    public void saveCourse(Course course);

    public Course findCourseByIdJoinFetch(Integer id);

    public Course findCourseAndStudentsById(Integer id);

    public Student findStudentAndCoursesByStudentId(Integer id);

    public void updateUser(Student student);

    public Course findCourseById(Integer id);

    public void removeCourse(Integer id);

    public Student findStudentByEmail(String email);

    public void removeCourse(int userId, int courseId);

}
