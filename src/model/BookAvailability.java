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
public class BookAvailability {
    private boolean available;
    private String title;
    private String author;
    private String publisher;
    private int yearPublished;
    private String isbn;
    private String callNumber;

    public BookAvailability(boolean available, String title, String author, String publisher,
                            int yearPublished, String isbn, String callNumber) {
        this.available = available;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.yearPublished = yearPublished;
        this.isbn = isbn;
        this.callNumber = callNumber;
    }

    public boolean isAvailable() { return available; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getPublisher() { return publisher; }
    public int getYearPublished() { return yearPublished; }
    public String getIsbn() { return isbn; }
    public String getCallNumber() { return callNumber; }
}


