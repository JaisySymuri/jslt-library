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
public class ChangePasswordDTO {

    private final String studentId;
    private final String oldPassword;
    private final String newPassword;
    private final String confirmPassword;

    public ChangePasswordDTO(String studentId, String oldPassword, String newPassword, String confirmPassword) {
        this.studentId = studentId;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}
