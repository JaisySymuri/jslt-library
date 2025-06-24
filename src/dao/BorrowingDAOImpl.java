/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.BorrowStatus;
import model.Borrowing;
import util.DatabaseConnection;

/**
 *
 * @author bests
 */
public class BorrowingDAOImpl implements BorrowingDAO {

    @Override
    public List<Borrowing> getBorrowingsByStudentId(String studentId) throws Exception {
        List<Borrowing> result = new ArrayList<>();
        String query = "SELECT * FROM borrowing WHERE StudentID = ? ORDER BY BorrowDate DESC";
        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(mapRowToBorrowing(rs));
            }
        }
        return result;
    }

    @Override
    public List<Borrowing> getActiveBorrowings(String studentId) throws Exception {
        List<Borrowing> result = new ArrayList<>();
        String query = "SELECT * FROM borrowing WHERE StudentID = ? AND Status = 'BORROWED'";
        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(mapRowToBorrowing(rs));
            }
        }
        return result;
    }

    @Override
    public boolean hasBorrowedAndNotReturned(String studentId, String isbn) throws Exception {
        String sql = "SELECT 1 FROM borrowing br "
                + "JOIN bookcopies bc ON br.BookID = bc.BookID "
                + "WHERE br.StudentID = ? AND bc.ISBN = ? AND br.Status = 'BORROWED' LIMIT 1";

        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, studentId);
            stmt.setString(2, isbn);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    @Override
    public boolean borrowBook(Borrowing borrowing) throws Exception {
        String query = "INSERT INTO borrowing (StudentID, BookID, BorrowDate, DueDate, Status) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, borrowing.getStudentId());
            stmt.setInt(2, borrowing.getBookId());
            stmt.setDate(3, Date.valueOf(borrowing.getBorrowDate()));
            stmt.setDate(4, Date.valueOf(borrowing.getDueDate()));
            stmt.setString(5, borrowing.getStatus().name());

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean returnBook(int borrowingId) throws Exception {
        String query = "UPDATE borrowing SET Status = 'RETURNED' WHERE BorrowID = ?";
        String getBookId = "SELECT BookID FROM borrowing WHERE BorrowID = ?";
        String updateBook = "UPDATE books SET CopiesAvailable = CopiesAvailable + 1 WHERE BookID = ?";

        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement selectStmt = conn.prepareStatement(getBookId);
                PreparedStatement updateStmt = conn.prepareStatement(query)) {

            // Step 1: Get the book ID first
            selectStmt.setInt(1, borrowingId);
            ResultSet rs = selectStmt.executeQuery();
            int bookId = -1;
            if (rs.next()) {
                bookId = rs.getInt("BookID");
            }

            if (bookId == -1) {
                return false; // Book not found for this borrow ID
            }

            // Step 2: Update the borrowing status
            updateStmt.setInt(1, borrowingId);
            int updated = updateStmt.executeUpdate();

            // Step 3: Update the book's available count if status update succeeded
            if (updated > 0) {
                try (PreparedStatement bookStmt = conn.prepareStatement(updateBook)) {
                    bookStmt.setInt(1, bookId);
                    return bookStmt.executeUpdate() > 0;
                }
            }
        }
        return false;
    }

    private Borrowing mapRowToBorrowing(ResultSet rs) throws SQLException {
        return new Borrowing(
                rs.getInt("BorrowID"),
                rs.getString("StudentID"),
                rs.getInt("BookID"),
                rs.getString("CallNumber"),
                rs.getDate("BorrowDate").toLocalDate(),
                rs.getDate("DueDate").toLocalDate(),
                BorrowStatus.valueOf(rs.getString("Status"))
        );
    }

    @Override
    public int getAvailableBookCopy(String isbn) throws Exception {
        String sql = "SELECT c.BookID FROM bookcopies c "
                + "LEFT JOIN borrowing br ON c.BookID = br.BookID AND br.Status = 'BORROWED' "
                + "WHERE c.ISBN = ? AND br.BorrowID IS NULL LIMIT 1";

        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("BookID");
            } else {
                throw new Exception("No available copy found for ISBN: " + isbn);
            }
        }
    }

}
