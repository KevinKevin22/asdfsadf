package com.example.jpa;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.jpa.dao.StudentDAO;
import com.example.jpa.entity.Course;
import com.example.jpa.entity.Student;
import com.example.jpa.entity.StudentDetail;

@SpringBootApplication
public class JpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

	// Custom Code
	// @Bean
	// public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
	// return runner -> {
	// // readStudent(studentDAO); (Done)

	// // readAllStudent(studentDAO);

	// // findByUsername(studentDAO);

	// // updateUser(studentDAO);

	// // deleteUserById(studentDAO);

	// // deleteAll(studentDAO);

	// // findStudentDetailById(studentDAO);

	// // createCourse(studentDAO);

	// // findcourseByIdFetch(studentDAO);

	// // createCourseAndStudents(studentDAO); (Done)=======================

	// // findCourseAndStudentsByCourseId(studentDAO);

	// // findStudentAndCoursesByStudentId(studentDAO);

	// // addCourseToStudent(studentDAO);

	// // addCourseToStudentById(studentDAO);

	// // deleteCourseById(studentDAO);
	// };
	// }

	private void readStudent(StudentDAO studentDAO) {
		int id = 4;

		Student tempStudent = studentDAO.findById(id);
		System.out.println("Found with id = 2" + tempStudent.getFirstName());
	}

	private void findByUsername(StudentDAO studentDAO) {
		List<Student> theStudents = studentDAO.findByUsername("Test");

		for (Student x : theStudents) {
			System.out.println(x);
		}
	}

	private void updateUser(StudentDAO studentDAO) {
		int id = 1;
		Student tempStudent = studentDAO.findById(id);

		tempStudent.setFirstName("Minion");

		studentDAO.updateUser(tempStudent);

		tempStudent = studentDAO.findById(id);
		System.out.println(tempStudent);
	}

	private void findStudentDetailById(StudentDAO studentDAO) {
		int id = 2;
		StudentDetail tempDetail = studentDAO.findStudentDetailById(id);
		System.out.println(tempDetail.getStudent());
	}

	private void createCourse(StudentDAO studentDAO) {
		Course course = new Course("Computer Science");

		Student tempStudent = studentDAO.findById(4);
		Student tempStudent2 = studentDAO.findById(5);

		course.addStudent(tempStudent2);
		course.addStudent(tempStudent);
		studentDAO.saveCourse(course);
	}

	public void findcourseByIdFetch(StudentDAO studentDAO) {
		int id = 8;
		Course course = studentDAO.findCourseByIdJoinFetch(id);
		System.out.println(course);
		// Will retrieve both student and courses information cause of JOIN FETCH
	}

	private void createCourseAndStudents(StudentDAO studentDAO) {
		Course tempCourse = new Course("Programming");

		Student tempStudent = new Student("Test", "Test", "Test", "Test");

		tempCourse.addStudent(tempStudent);

		studentDAO.saveCourse(tempCourse);
	}

	private void findCourseAndStudentsByCourseId(StudentDAO studentDAO) {
		int id = 14;

		Course tempCourse = studentDAO.findCourseAndStudentsById(id);

		System.out.println(tempCourse);
		System.out.println(tempCourse.getStudents());
	}

	private void findStudentAndCoursesByStudentId(StudentDAO studentDAO) {
		int id = 13;

		Student tempStudent = studentDAO.findStudentAndCoursesByStudentId(id);

		System.out.println(tempStudent);
		System.out.println(tempStudent.getCourses());
	}

	private void addCourseToStudent(StudentDAO studentDAO) {
		int id = 13;
		Student tempStudent = studentDAO.findStudentAndCoursesByStudentId(id);

		Course course = new Course("Discrete Math");
		tempStudent.addCourse(course);

		studentDAO.updateUser(tempStudent);
	}

	private void addCourseToStudentById(StudentDAO studentDAO) {
		int id = 13;
		Student tempStudent = studentDAO.findStudentAndCoursesByStudentId(id);

		Course course = studentDAO.findCourseById(6);

		tempStudent.addCourse(course);

		studentDAO.updateUser(tempStudent);
	}

	private void deleteCourseById(StudentDAO studentDAO) {
		int id = 6;

		studentDAO.removeCourse(id);
	}

}
