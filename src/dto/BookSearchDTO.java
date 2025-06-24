/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author bests
 */
public class BookSearchDTO {

    private final String title;
    private final String author;
    private final String publisher;
    private final String isbn;
    private final String yearPublished;
    private final String callNumber;

    public BookSearchDTO(String title, String author, String publisher,
            String isbn, String yearPublished, String callNumber) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.yearPublished = yearPublished;
        this.callNumber = callNumber;
    }

    // Getters only
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getYearPublished() {
        return yearPublished;
    }

    public String getCallNumber() {
        return callNumber;
    }
}
