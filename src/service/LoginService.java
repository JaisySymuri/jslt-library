/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.StudentDAO;
import exeption.UserNotFoundException;
import model.Student;
import util.HashUtil;

/**
 *
 * @author bests
 */
public class LoginService {

    public static boolean authenticate(String studentId, String plainPassword) throws Exception {
        Student student = StudentDAO.getStudentById(studentId);
        if (student == null) {
            throw new UserNotFoundException();
        }

        String storedHashed = student.getPasswordHash();
        String inputHashed = HashUtil.hashPassword(plainPassword);

        return storedHashed.equals(inputHashed);
    }
}
