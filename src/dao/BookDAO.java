/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.criteria.BookSearchCriteria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Book;
import util.DatabaseConnection;

/**
 *
 * @author bests
 */
public class BookDAO {

    public static Book getBookById(int bookId) throws SQLException {
        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM books WHERE BookID = ?")) {
            stmt.setInt(1, bookId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Book(
                        rs.getInt("BookID"),
                        rs.getString("Title"),
                        rs.getString("Author"),
                        rs.getString("Publisher"),
                        rs.getString("ISBN"),
                        rs.getInt("YearPublished"),
                        rs.getInt("CopiesAvailable"),
                        rs.getString("CallNumber")
                );
            }
        }
        return null;
    }
    
    public static List<Book> searchBooks(BookSearchCriteria criteria, boolean useAnd) throws SQLException {
    List<Book> result = new ArrayList<>();

    if (criteria == null || criteria.isEmpty()) return result;

    StringBuilder query = new StringBuilder("SELECT * FROM books WHERE ");
    List<Object> values = new ArrayList<>();

    String joiner = useAnd ? " AND " : " OR ";
    for (String column : criteria.getCriteria().keySet()) {
        if (!values.isEmpty()) query.append(joiner);
        query.append(column).append(" LIKE ?");
        values.add("%" + criteria.getCriteria().get(column) + "%");
    }

    try (Connection conn = DatabaseConnection.connect();
         PreparedStatement stmt = conn.prepareStatement(query.toString())) {

        for (int i = 0; i < values.size(); i++) {
            stmt.setObject(i + 1, values.get(i));
        }

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            result.add(new Book(
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
    }

    return result;
}


}
