/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import dao.BookDAO;
import dao.BorrowingDAO;
import java.time.LocalDate;
import model.BorrowStatus;
import model.Borrowing;
import service.validation.BorrowingValidator;
import service.validation.ValidationResult;

/**
 *
 * @author bests
 */
public class BorrowBookService {
    private final BookDAO bookDAO;
    private final BorrowingDAO borrowingDAO;
    private final BorrowingValidator validator;

    public BorrowBookService(BookDAO bookDAO, BorrowingDAO borrowingDAO) {
        this.bookDAO = bookDAO;
        this.borrowingDAO = borrowingDAO;
        this.validator = new BorrowingValidator(bookDAO, borrowingDAO);
    }

    public ValidationResult borrow(String studentId, String isbn) {
        try {
            // 1. Validate
            ValidationResult validation = validator.validate(studentId, isbn);
            if (!validation.isValid()) {
                return validation;
            }

            // 2. Find an available physical copy
            int bookId = bookDAO.findAvailableBookId(isbn);
            if (bookId == -1) {
                return ValidationResult.fail("No available physical copy found.");
            }

            // 3. Perform insert
            Borrowing borrowing = new Borrowing();
            borrowing.setStudentId(studentId);
            borrowing.setBookId(bookId);
            borrowing.setBorrowDate(LocalDate.now());
            borrowing.setDueDate(LocalDate.now().plusDays(7));
            borrowing.setStatus(BorrowStatus.BORROWED);

            boolean success = borrowingDAO.borrowBook(borrowing);
            return success
                ? ValidationResult.success("Book borrowed successfully.")
                : ValidationResult.fail("Failed to borrow book. Please try again.");

        } catch (Exception e) {
            e.printStackTrace();
            return ValidationResult.fail("Error during borrowing: " + e.getMessage());
        }
    }
}
