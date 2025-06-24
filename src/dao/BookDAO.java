package dao;

import dao.criteria.BookSearchCriteria;
import model.Book;
import model.BookAvailability;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    public static Book getBookByISBN(String isbn) throws SQLException {
        String query = "SELECT * FROM books WHERE ISBN = ?";
        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Book(
                        rs.getString("ISBN"),
                        rs.getString("Title"),
                        rs.getString("Author"),
                        rs.getString("Publisher"),
                        rs.getInt("YearPublished"),
                        rs.getString("CallNumber")
                );
            }
        }
        return null;
    }

    public static List<BookAvailability> searchBooks(BookSearchCriteria criteria, boolean useAnd) throws SQLException {
        List<BookAvailability> list = new ArrayList<>();

        StringBuilder sql = new StringBuilder(
                "SELECT "
                + "CASE WHEN COUNT(c.BookID) - COUNT(br.BookID) > 0 THEN TRUE ELSE FALSE END AS IsAvailable, "
                + "b.Title, b.Author, b.Publisher, b.YearPublished, b.ISBN, b.CallNumber "
                + "FROM books b "
                + "JOIN bookcopies c ON b.ISBN = c.ISBN "
                + "LEFT JOIN borrowing br ON c.BookID = br.BookID AND br.ReturnDate IS NULL "
        );

        List<Object> values = new ArrayList<>();

        if (criteria != null && !criteria.isEmpty()) {
            sql.append("WHERE ");
            String joiner = useAnd ? " AND " : " OR ";
            boolean first = true;

            for (String column : criteria.getCriteria().keySet()) {
                if (!first) {
                    sql.append(joiner);
                }
                sql.append("b.").append(column).append(" LIKE ? ");
                values.add("%" + criteria.getCriteria().get(column) + "%");
                first = false;
            }
        }

        sql.append("GROUP BY b.ISBN, b.Title, b.Author, b.Publisher, b.YearPublished, b.CallNumber");

        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < values.size(); i++) {
                stmt.setObject(i + 1, values.get(i));
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new BookAvailability(
                        rs.getBoolean("IsAvailable"),
                        rs.getString("Title"),
                        rs.getString("Author"),
                        rs.getString("Publisher"),
                        rs.getInt("YearPublished"),
                        rs.getString("ISBN"),
                        rs.getString("CallNumber")
                ));
            }
        }

        return list;
    }

    public static List<BookAvailability> getAllBookAvailabilityFlags() throws SQLException {
        List<BookAvailability> list = new ArrayList<>();

        String sql = "SELECT "
                + "CASE WHEN COUNT(c.BookID) - COUNT(br.BookID) > 0 THEN TRUE ELSE FALSE END AS IsAvailable, "
                + "b.Title, b.Author, b.Publisher, b.YearPublished, b.ISBN, b.CallNumber "
                + "FROM books b "
                + "JOIN bookcopies c ON b.ISBN = c.ISBN "
                + "LEFT JOIN borrowing br ON c.BookID = br.BookID AND br.ReturnDate IS NULL "
                + "GROUP BY b.ISBN, b.Title, b.Author, b.Publisher, b.YearPublished, b.CallNumber";

        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                BookAvailability ba = new BookAvailability(
                        rs.getBoolean("IsAvailable"),
                        rs.getString("Title"),
                        rs.getString("Author"),
                        rs.getString("Publisher"),
                        rs.getInt("YearPublished"),
                        rs.getString("ISBN"),
                        rs.getString("CallNumber")
                );
                list.add(ba);
            }
        }

        return list;
    }

    public boolean isBookAvailable(String isbn) throws SQLException {
        String sql = "SELECT COUNT(*) AS Available "
                + "FROM bookcopies c "
                + "LEFT JOIN borrowing br ON c.BookID = br.BookID AND br.ReturnDate IS NULL "
                + "WHERE c.ISBN = ? AND br.BorrowID IS NULL";

        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("Available") > 0;
            }
        }
        return false;
    }

    public int findAvailableBookId(String isbn) throws SQLException {
        String sql = "SELECT c.BookID FROM bookcopies c "
                + "LEFT JOIN borrowing br ON c.BookID = br.BookID AND br.Status = 'BORROWED' "
                + "WHERE c.ISBN = ? AND br.BookID IS NULL LIMIT 1";

        try (Connection conn = DatabaseConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("BookID");
            }
        }
        return -1;
    }

}
