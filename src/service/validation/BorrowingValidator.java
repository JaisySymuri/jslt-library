/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.validation;

import dao.BookDAO;
import dao.BorrowingDAO;
import java.util.List;
import model.Book;
import model.Borrowing;

/**
 *
 * @author bests
 */
public class BorrowingValidator {

    private final BookDAO bookDAO;
    private final BorrowingDAO borrowingDAO;
    private final int MAX_ACTIVE_BORROWINGS = 5;

    public BorrowingValidator(BookDAO bookDAO, BorrowingDAO borrowingDAO) {
        this.bookDAO = bookDAO;
        this.borrowingDAO = borrowingDAO;
    }

    public ValidationResult validate(String studentId, String isbn) {
    try {
        // 1. Check if any copy of this ISBN is available
        if (!bookDAO.isBookAvailable(isbn)) {
            return ValidationResult.fail("No available copies for this book.");
        }

        // 2. Check if student already borrowed and not returned this ISBN
        if (borrowingDAO.hasBorrowedAndNotReturned(studentId, isbn)) {
            return ValidationResult.fail("You have already borrowed this book and haven't returned it yet.");
        }

        // 3. Check max borrowing limit
        List<Borrowing> activeBorrowings = borrowingDAO.getActiveBorrowings(studentId);
        if (activeBorrowings.size() >= MAX_ACTIVE_BORROWINGS) {
            return ValidationResult.fail("You have reached the maximum allowed borrowings (5 books).");
        }

        return ValidationResult.success();
    } catch (Exception e) {
        e.printStackTrace();
        return ValidationResult.fail("An error occurred during validation.");
    }
}

}
