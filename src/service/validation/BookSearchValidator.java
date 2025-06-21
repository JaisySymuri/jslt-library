/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.validation;

import java.util.regex.Pattern;

/**
 *
 * @author bests
 */
public class BookSearchValidator {
    private static final Pattern ISBN_PATTERN = Pattern.compile("^(97(8|9))?\\d{9}(\\d|X)$");
    private static final Pattern LCC_PATTERN = Pattern.compile("^[A-Z]{1,3}\\d+(\\.\\d+)?(\\.[A-Z]\\d+)?$");

    public static ValidationResult validateSearchFields(
            String bookIdText,
            String title,
            String author,
            String publisher,
            String isbn,
            String yearPublished,
            String callNumber
    ) {
        boolean allEmpty = isEmpty(bookIdText) && isEmpty(title) && isEmpty(author)
                && isEmpty(publisher) && isEmpty(isbn)
                && isEmpty(yearPublished) && isEmpty(callNumber);

        if (allEmpty) {
            return new ValidationResult(false, "At least one field must be filled.");
        }

        if (!isEmpty(yearPublished)) {
            try {
                int year = Integer.parseInt(yearPublished.trim());
                if (year < 1000 || year > 9999) {
                    return new ValidationResult(false, "Year must be a valid 4-digit number.");
                }
            } catch (NumberFormatException e) {
                return new ValidationResult(false, "Year must be numeric.");
            }
        }

        if (!isEmpty(isbn) && !ISBN_PATTERN.matcher(isbn.trim()).matches()) {
            return new ValidationResult(false, "Invalid ISBN format.");
        }

        if (!isEmpty(callNumber) && !LCC_PATTERN.matcher(callNumber.trim()).matches()) {
            return new ValidationResult(false, "Invalid Call Number format.");
        }

        return new ValidationResult(true, null);
    }

    private static boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }
}
