package com.nls.masternaut;

public class PageRequest<T extends PageRequest<T>> implements Cloneable {
    private Integer pageIndex;
    private Integer pageSize;

    private final transient Class<T> clazz;

    protected PageRequest(Class<T> clazz) {
        this.clazz = clazz;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public T withPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
        return clazz.cast(this);
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public T withPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return clazz.cast(this);
    }

    public T next() {
        try {
            return clazz.cast(this.clone()).withPageIndex(pageIndex == null ? 1 : pageIndex + 1);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
