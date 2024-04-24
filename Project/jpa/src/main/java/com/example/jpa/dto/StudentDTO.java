package com.example.jpa.dto;

import java.util.List;

import com.example.jpa.entity.Course;
import com.example.jpa.entity.StudentDetail;

public class StudentDTO {
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private StudentDetail studentDetail;

    private List<Course> courses;

    public StudentDTO() {

    }

    public StudentDTO(int id, String firstName, String lastName, String email, String password,
            StudentDetail studentDetail, List<Course> courses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.studentDetail = studentDetail;
        this.courses = courses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public StudentDetail getStudentDetail() {
        return studentDetail;
    }

    public void setStudentDetail(StudentDetail studentDetail) {
        this.studentDetail = studentDetail;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "StudentDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                + ", password=" + password + ", studentDetail=" + studentDetail + ", courses=" + courses + "]";
    }

}
