/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import exeption.UserNotFoundException;
import service.LoginService;
import service.result.LoginResult;
import util.Session;

/**
 *
 * @author bests
 */
public class LoginController {

    public static LoginResult login(String studentId, String password) {
        try {
            boolean success = LoginService.authenticate(studentId, password);
            if (success) {
                Session.setCurrentStudentId(studentId);
                return new LoginResult(true, "Login successful.");
            } else {
                return new LoginResult(false, "Invalid credentials.");
            }
        } catch (UserNotFoundException e) {
            return new LoginResult(false, "Student ID not found");
        } catch (Exception e) {
            return new LoginResult(false, "Error: " + e.getMessage());
        }
    }
}
