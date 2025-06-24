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

   
}