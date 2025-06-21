/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import dao.criteria.BookSearchCriteria;
import dto.BookSearchDTO;

/**
 *
 * @author bests
 */
public class BookSearchMapper {
    public static BookSearchCriteria fromDTO(BookSearchDTO dto) {
        BookSearchCriteria criteria = new BookSearchCriteria();

        criteria.addCriteria("BookID", dto.getBookId());
        criteria.addCriteria("Title", dto.getTitle());
        criteria.addCriteria("Author", dto.getAuthor());
        criteria.addCriteria("Publisher", dto.getPublisher());
        criteria.addCriteria("ISBN", dto.getIsbn());
        criteria.addCriteria("YearPublished", dto.getYearPublished());
        criteria.addCriteria("CallNumber", dto.getCallNumber());

        return criteria;
    }
}
