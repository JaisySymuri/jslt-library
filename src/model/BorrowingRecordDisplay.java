/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.time.LocalDate;

/**
 *
 * @author bests
 */
public class BorrowingRecordDisplay {
    private int borrowId;
    private String title;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate; // nullable

    public BorrowingRecordDisplay(int borrowId, String title, LocalDate borrowDate,
                                  LocalDate dueDate, LocalDate returnDate) {
        this.borrowId = borrowId;
        this.title = title;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    // Getters
    public int getBorrowId() { return borrowId; }
    public String getTitle() { return title; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getDueDate() { return dueDate; }
    public LocalDate getReturnDate() { return returnDate; }
}
