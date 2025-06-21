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
public class Book {
    private int bookId;
    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private int yearPublished;
    private int copiesAvailable;
    private String callNumber;

    public Book(int bookId, String title, String author, String publisher, String isbn, int yearPublished, int copiesAvailable, String callNumber) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.yearPublished = yearPublished;
        this.copiesAvailable = copiesAvailable;
        this.callNumber = callNumber;
    }

    public Book(String title, String author, String publisher, String isbn, int yearPublished, int copiesAvailable, String callNumber) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.yearPublished = yearPublished;
        this.copiesAvailable = copiesAvailable;
        this.callNumber = callNumber;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public void setCopiesAvailable(int copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }
    
     public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }
    
}

