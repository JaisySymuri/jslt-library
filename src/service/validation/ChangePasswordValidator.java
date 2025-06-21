/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.validation;

import dto.ChangePasswordDTO;
import model.Student;
import util.HashUtil;

/**
 *
 * @author bests
 */
public class ChangePasswordValidator {

    public static ValidationResult validate(ChangePasswordDTO request, Student student) {
        if (student == null) {
            return new ValidationResult(false, "Student not found.");
        }

        String storedHashed = student.getPasswordHash();
        String inputHashed = HashUtil.hashPassword(request.getOldPassword());

        if (!storedHashed.equals(inputHashed)) {
            return new ValidationResult(false, "Old password is incorrect.");
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            return new ValidationResult(false, "New password and confirmation do not match.");
        }

        if (request.getNewPassword().length() < 8) {
            return new ValidationResult(false, "New password must be at least 8 characters long.");
        }

        return new ValidationResult(true, "Password is valid.");
    }
}
