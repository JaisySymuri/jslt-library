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
public class BorrowingDisplay {
    private String title;
    private LocalDate dueDate;
    private long daysRemaining; // optional, calculated in Swing

    public BorrowingDisplay(String title, LocalDate dueDate) {
        this.title = title;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}
