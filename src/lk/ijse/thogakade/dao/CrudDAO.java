package lk.ijse.thogakade.dao;

import lk.ijse.thogakade.entity.item;


import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO <T,ID>{

    public boolean save(T item) throws SQLException, ClassNotFoundException;
    public boolean update(T item) throws SQLException, ClassNotFoundException;
    public boolean delete(ID id) throws SQLException, ClassNotFoundException;
    public T get(ID code) throws SQLException, ClassNotFoundException;
    public ArrayList<T> getAll(ID text) throws SQLException, ClassNotFoundException;
}
