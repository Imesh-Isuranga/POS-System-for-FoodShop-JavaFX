package lk.ijse.thogakade.dao.custom;

import lk.ijse.thogakade.dao.CrudDAO;
import lk.ijse.thogakade.dto.itemDTO;
import lk.ijse.thogakade.entity.item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface itemDAO extends CrudDAO<item,String> {
    /*public boolean saveItem(item item) throws SQLException, ClassNotFoundException;
    public boolean updateItem(item item) throws SQLException, ClassNotFoundException;
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException;
    public item getItem(String code) throws SQLException, ClassNotFoundException;
    public ArrayList<item> getAllItem(String text) throws SQLException, ClassNotFoundException;*/
}
