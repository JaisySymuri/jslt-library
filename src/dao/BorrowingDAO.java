/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Borrowing;

/**
 *
 * @author bests
 */
public interface BorrowingDAO {

    List<Borrowing> getBorrowingsByStudentId(String studentId) throws Exception;

    List<Borrowing> getActiveBorrowings(String studentId) throws Exception;

    boolean hasBorrowedAndNotReturned(String studentId, String isbn) throws Exception;

    boolean borrowBook(Borrowing borrowing) throws Exception;

    boolean returnBook(int borrowingId) throws Exception;
    
    int getAvailableBookCopy(String isbn) throws Exception;

}
