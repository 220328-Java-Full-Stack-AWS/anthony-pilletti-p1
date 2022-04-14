package com.revature.ers.dao;

import java.util.List;

public interface CRUDInterface<T> {
    //CRUD - create, read, update, delete
    public T create(T model);
    public T read(int id);
    public void update(T model);
    public void delete(int id);
    public void delete(T model);
    public List<T> getAll();

}
