/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

/**
 *
 * @author bests
 */
public class Session {
    private static String currentStudentId;

    public static void setCurrentStudentId(String studentId) {
        currentStudentId = studentId;
    }

    public static String getCurrentStudentId() {
         if (currentStudentId == null || currentStudentId.trim().isEmpty()) {
            return "231011400253"; // Fallback value
        }
        return currentStudentId;
    }    
}
