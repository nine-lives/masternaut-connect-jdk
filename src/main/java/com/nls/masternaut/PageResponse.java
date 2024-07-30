package com.nls.masternaut;

import java.util.List;

public class PageResponse<T> {
    private Integer totalPages;
    private int totalCount;
    private List<T> items;

    public Integer getTotalPages() {
        return totalPages;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public List<T> getItems() {
        return items;
    }

}
