/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import dao.StudentDAO;
import dto.ChangePasswordDTO;
import java.sql.SQLException;
import model.Student;
import service.validation.ChangePasswordValidator;
import service.validation.ValidationResult;
import util.HashUtil;

/**
 *
 * @author bests
 */
public class ChangePasswordController {

    public static ValidationResult process(ChangePasswordDTO request) {
        try {
            Student student = StudentDAO.getStudentById(request.getStudentId());
            if (student == null) {
                return new ValidationResult(false, "Student not found.");
            }

            ValidationResult result = ChangePasswordValidator.validate(request, student);
            if (!result.isValid()) {
                return result;
            }

            boolean success = StudentDAO.updatePassword(request);
            if (!success) {
                return new ValidationResult(false, "Failed to update password.");
            }

            return new ValidationResult(true, "Password updated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return new ValidationResult(false, "An error occurred: " + e.getMessage());
        }
    }
}

