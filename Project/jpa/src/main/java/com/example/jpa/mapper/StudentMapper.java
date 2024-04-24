package com.example.jpa.mapper;

import com.example.jpa.dto.StudentDTO;
import com.example.jpa.entity.Student;

public class StudentMapper {

    public static StudentDTO mapToStudentDTO(Student student) {
        StudentDTO st = new StudentDTO(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getPassword(),
                student.getStudentDetail(),
                student.getCourses());
        return st;
    }
}
