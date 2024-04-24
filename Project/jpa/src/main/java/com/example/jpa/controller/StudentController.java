package com.example.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa.dao.StudentDAO;
import com.example.jpa.dto.LoginDTO;
import com.example.jpa.dto.StudentDTO;
import com.example.jpa.entity.Course;
import com.example.jpa.entity.Student;
import com.example.jpa.mapper.StudentMapper;

@RestController
public class StudentController {

    // Baru ditambahkan
    @Autowired
    private final StudentDAO service;

    public StudentController(StudentDAO service) {
        this.service = service;
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody Student student) {
        service.save(student);
        return new ResponseEntity<>("user registered successfully", HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDto) {
        Student searchedUser = service.findStudentByEmail(loginDto.getEmail());
        if (searchedUser == null) {
            return new ResponseEntity<>("Email not found", HttpStatus.BAD_REQUEST);
        }
        if (searchedUser.getPassword().equals(loginDto.getPassword())) {
            return new ResponseEntity<>(String.valueOf(searchedUser.getId()), HttpStatus.OK);
        }
        return new ResponseEntity<>("Username or Password does not match", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> findStudentAndCoursesByStudentId(@PathVariable("id") int userId) {
        Student response = service.findStudentAndCoursesByStudentId(userId);
        StudentDTO responseDTO = StudentMapper.mapToStudentDTO(response);
        System.out.println("Response = " + response.getCourses());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> findCourseAndStudentsByCourseId(@PathVariable("id") int courseId) {
        Course response = service.findCourseAndStudentsById(courseId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("enroll/{studentId}/{courseId}")
    public ResponseEntity<String> enrollById(@PathVariable("studentId") int userId,
            @PathVariable("courseId") int courseId) {
        Student tempStudent = service.findStudentAndCoursesByStudentId(userId);

        Course course = service.findCourseById(courseId);

        tempStudent.addCourse(course);

        service.updateUser(tempStudent);

        return new ResponseEntity<>("Sucess", HttpStatus.OK);
    }

    @PutMapping("unenroll/{studentId}/{courseId}")
    public ResponseEntity<String> unenroll(@PathVariable("studentId") int userId,
            @PathVariable("courseId") int courseId) {
        service.removeCourse(userId, courseId);

        return new ResponseEntity<>("Sucess", HttpStatus.OK);
    }

    @PostMapping("newcourse/{studentId}")
    public ResponseEntity<String> createNewCourse(@PathVariable("studentId") int userId,
            @RequestBody Course course) {

        Course tempCourse = new Course(course.getCourseName());

        Student tempStudent = service.findById(userId);

        tempCourse.addStudent(tempStudent);

        service.saveCourse(tempCourse);

        return new ResponseEntity<>("Sucess", HttpStatus.OK);
    }

    @DeleteMapping("deletecourse/{courseId}")
    public ResponseEntity<String> deleteCourseById(@PathVariable("courseId") int courseId) {

        service.removeCourse(courseId);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

}
