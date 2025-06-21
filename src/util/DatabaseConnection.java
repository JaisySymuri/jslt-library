package util;

import model.Borrowing;
import model.Book;
import model.Student;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class DatabaseConnection {
    
    public static Connection connect() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/jslt_library", "root", ""); // Ubah sesuai konfigurasi Anda
            System.out.println("Connected to jslt_library");
        } catch (Exception e) {
            System.out.println("Failed to connect: " + e.getMessage());
        }
        return conn;
    }

    // -------------------- STUDENT --------------------
    public static boolean insertStudent(Student s) {
        String sql = "INSERT INTO Students (Name, Email, Major, EnrollmentYear) VALUES (?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, s.getName());
            stmt.setString(2, s.getEmail());
            stmt.setString(3, s.getMajor());
            stmt.setInt(4, s.getEnrollmentYear());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Insert student failed: " + e.getMessage());
        }
        return false;
    }
    
    public static List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Students")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Student(
                        rs.getString("StudentID"),
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getString("Major"),
                        rs.getInt("EnrollmentYear"),
                        rs.getString("PasswordHash")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Get students failed: " + e.getMessage());
        }
        return list;
    }

    // -------------------- BOOK --------------------
    public static boolean insertBook(Book b) {
        String sql = "INSERT INTO Books (Title, Author, Publisher, ISBN, YearPublished, CopiesAvailable) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, b.getTitle());
            stmt.setString(2, b.getAuthor());
            stmt.setString(3, b.getPublisher());
            stmt.setString(4, b.getIsbn());
            stmt.setInt(5, b.getYearPublished());
            stmt.setInt(6, b.getCopiesAvailable());
            stmt.setString(7, b.getCallNumber());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Insert book failed: " + e.getMessage());
        }
        return false;
    }
    
    public static List<Book> getAllBooks() {
        List<Book> list = new ArrayList<>();
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Books")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Book(
                        rs.getInt("BookID"),
                        rs.getString("Title"),
                        rs.getString("Author"),
                        rs.getString("Publisher"),
                        rs.getString("ISBN"),
                        rs.getInt("YearPublished"),
                        rs.getInt("CopiesAvailable"),
                        rs.getString("CallNumber")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Get books failed: " + e.getMessage());
        }
        return list;
    }

    // -------------------- BORROWING --------------------
    public static boolean insertBorrowing(Borrowing b) {
        String sql = "INSERT INTO Borrowings (StudentID, BookID, BorrowDate, ReturnDate, Returned) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, b.getStudentId());
            stmt.setInt(2, b.getBookId());
            stmt.setString(3, b.getCallNumber());
            stmt.setDate(4, Date.valueOf(b.getBorrowDate()));
            stmt.setDate(5, b.getReturnDate() != null ? Date.valueOf(b.getReturnDate()) : null);
            stmt.setBoolean(6, b.isReturned());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Insert borrowing failed: " + e.getMessage());
        }
        return false;
    }
    
    public static List<Borrowing> getAllBorrowings() {
        List<Borrowing> list = new ArrayList<>();
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Borrowings")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Borrowing(
                        rs.getInt("BorrowingID"),
                        rs.getString("StudentID"),
                        rs.getInt("BookID"),
                        rs.getString("CallNumber"),
                        rs.getDate("BorrowDate").toLocalDate(),
                        rs.getDate("ReturnDate") != null ? rs.getDate("ReturnDate").toLocalDate() : null,
                        rs.getBoolean("Returned")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Get borrowings failed: " + e.getMessage());
        }
        return list;
    }
}
