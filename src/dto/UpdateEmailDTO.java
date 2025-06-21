/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author bests
 */
public class UpdateEmailDTO {

    private String studentId;
    private String email;

    public UpdateEmailDTO(String studentId, String email) {
        this.studentId = studentId;
        this.email = email;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getEmail() {
        return email;
    }
}
