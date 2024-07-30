package com.nls.masternaut;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class Page<T> {
    private final Integer totalPages;
    private final int totalCount;
    private final List<T> items;
    private final Supplier<Page<T>> nextSupplier;

    Page(PageResponse<T> response, Supplier<Page<T>> nextSupplier) {
        this.totalCount = response.getTotalCount();
        this.totalPages = response.getTotalPages();
        this.items = Collections.unmodifiableList(response.getItems());
        this.nextSupplier = nextSupplier;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public Page<T> next() {
        return nextSupplier.get();
    }

    public boolean hasNext() {
        return !items.isEmpty() && items.size() < totalCount;
    }
}
