package com.nls.masternaut;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.nls.masternaut.client.IClient;

import java.util.ArrayList;
import java.util.List;

public abstract class PageRequest<T extends PageRequest<T, R>, R> implements Cloneable {
    private Integer pageIndex;
    private Integer pageSize;

    @JsonIgnore
    private final transient Class<T> clazz;

    @JsonIgnore
    private final transient IClient client;

    protected PageRequest(IClient client, Class<T> clazz) {
        this.client = client;
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

    public Page<R> fetch() {
        return new Page<>(
            client.get(getPath(), this, new TypeReference<PageResponse<R>>() { }),
            () -> this.next().fetch());
    }

    public List<R> collect() {
        if (getPageSize() == null) {
            withPageSize(getMaxPageSize());
        }

        List<R> result = new ArrayList<>();
        Page<R> page = withPageIndex(0).fetch();
        do {
            result.addAll(page.getItems());
            page = page.next();
        } while (page.hasNext());

        return result;
    }

    public int getMaxPageSize() {
        return 50;
    }

    protected abstract String getPath();
}
