/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.ChangePasswordDTO;
import dto.UpdateEmailDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Student;
import util.DatabaseConnection;
import util.HashUtil;

/**
 *
 * @author bests
 */
public class StudentDAO {

    public static Student getStudentById(String studentId) throws SQLException {
        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Students WHERE StudentID = ?")) {
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Student(
                        rs.getString("StudentID"),
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getString("Major"),
                        rs.getInt("EnrollmentYear"),
                        rs.getString("PasswordHash") // Add this
                );
            }
        }
        return null;
    }

    public static boolean updateStudent(Student student) throws SQLException {
        String sql = "UPDATE Students SET Name = ?, Email = ?, Major = ?, EnrollmentYear = ? WHERE StudentID = ?";
        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setString(3, student.getMajor());
            stmt.setInt(4, student.getEnrollmentYear());
            stmt.setString(5, student.getStudentId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    public static boolean updatePassword(ChangePasswordDTO request) throws SQLException {
        String sql = "UPDATE Students SET PasswordHash = ? WHERE StudentID = ?";
        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
                    String newHashedPassword = HashUtil.hashPassword(request.getNewPassword());

            stmt.setString(1, newHashedPassword);
            stmt.setString(2, request.getStudentId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    public static boolean updateEmail(UpdateEmailDTO dto) throws SQLException {
        String sql = "UPDATE students SET email = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dto.getEmail());
            stmt.setString(2, dto.getStudentId());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}
