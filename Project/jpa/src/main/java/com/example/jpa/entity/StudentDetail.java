package com.example.jpa.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "student_detail")
public class StudentDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "phone_number")
    private String phoneNumber;

    // Supaya Student nya ga ikutan kehapus ketika Detail di delete
    // @OneToOne(mappedBy = "studentDetail", cascade = {CascadeType.DETACH,
    // CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @OneToOne(mappedBy = "studentDetail", cascade = CascadeType.ALL)
    private Student student;

    public StudentDetail() {

    }

    public StudentDetail(String schoolName, String phoneNumber) {
        this.schoolName = schoolName;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "StudentDetail [id=" + id + ", schoolName=" + schoolName + ", phoneNumber=" + phoneNumber + "]";
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

}
