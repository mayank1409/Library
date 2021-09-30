package com.library.app.service;

import java.util.List;

public interface GenericService<T, ID> {
    public T save(T t);
    public T update(T t);
    public T get(ID id);
    public List<T> getAll();
    public void delete(ID id);
}
