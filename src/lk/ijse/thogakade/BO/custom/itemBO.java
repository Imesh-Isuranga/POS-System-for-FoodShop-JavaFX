package lk.ijse.thogakade.BO.custom;

import lk.ijse.thogakade.BO.superBO;
import lk.ijse.thogakade.dto.itemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface itemBO extends superBO {
    public boolean saveItem(itemDTO item) throws SQLException, ClassNotFoundException;
    public boolean updateItem(itemDTO item) throws SQLException, ClassNotFoundException;
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException;
    public itemDTO getItem(String code) throws SQLException, ClassNotFoundException;
    public ArrayList<itemDTO> getAllItem(String text) throws SQLException, ClassNotFoundException;
}
