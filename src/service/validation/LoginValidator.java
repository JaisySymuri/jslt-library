/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service.validation;

/**
 *
 * @author bests
 */
public class LoginValidator {

    public static ValidationResult validateLoginFields(String studentIdText, String password) {
        if (studentIdText == null || studentIdText.trim().isEmpty()) {
            return new ValidationResult(false, "Student ID must not be empty.");
        }

        if (password == null || password.trim().isEmpty()) {
            return new ValidationResult(false, "Password must not be empty.");
        }       

        return new ValidationResult(true, null);
    }

    public static int parseStudentId(String studentIdText) {
        return Integer.parseInt(studentIdText.trim());
    }
}
