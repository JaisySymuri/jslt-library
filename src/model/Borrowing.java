/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author bests
 */
import java.time.LocalDate;

public class Borrowing {
    private int borrowingId;
    private String studentId;
    private int bookId;
    private String callNumber;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private BorrowStatus status;

    public Borrowing(int borrowingId, String studentId, int bookId, String callNumber, LocalDate borrowDate, LocalDate dueDate, BorrowStatus status) {
        this.borrowingId = borrowingId;
        this.studentId = studentId;
        this.bookId = bookId;
        this.callNumber = callNumber;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.status  = status ;
    }
    
    public Borrowing() {
        
    }

    public Borrowing(String studentId, int bookId, String callNumber, LocalDate borrowDate, LocalDate dueDate, BorrowStatus status) {
        this.studentId = studentId;
        this.bookId = bookId;
        this.callNumber = callNumber;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.status  = status;
    }

    public int getBorrowingId() {
        return borrowingId;
    }

    public void setBorrowingId(int borrowingId) {
        this.borrowingId = borrowingId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    
    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public BorrowStatus getStatus() {
        return status ;
    }
    
    public void setStatus(BorrowStatus status) {
    this.status = status;
}

    public void setReturned(BorrowStatus status) {
        this.status  = status;
    }
}
