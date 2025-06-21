/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.criteria;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author bests
 */
public class BookSearchCriteria {
    private final Map<String, Object> criteria = new LinkedHashMap<>();

    public void addCriteria(String column, Object value) {
        if (value != null && !value.toString().trim().isEmpty()) {
            criteria.put(column, value);
        }
    }

    public Map<String, Object> getCriteria() {
        return criteria;
    }

    public boolean isEmpty() {
        return criteria.isEmpty();
    }
}
