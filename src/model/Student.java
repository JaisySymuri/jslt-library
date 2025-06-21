/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author bests
 */
public class Student {
    private String studentId;
    private String name;
    private String email;
    private String major;
    private int enrollmentYear;
    private String passwordHash;

    public Student(String studentId, String name, String email, String major, int enrollmentYear, String passwordHash) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.major = major;
        this.enrollmentYear = enrollmentYear;
        this.passwordHash = passwordHash;
    }



    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getEnrollmentYear() {
        return enrollmentYear;
    }

    public void setEnrollmentYear(int enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }
    
    public String getPasswordHash() {
    return passwordHash;
}

public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
}
}
