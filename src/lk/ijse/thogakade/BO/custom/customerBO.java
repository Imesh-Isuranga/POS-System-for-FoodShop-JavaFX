package lk.ijse.thogakade.BO.custom;

import lk.ijse.thogakade.BO.superBO;
import lk.ijse.thogakade.dto.customerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface customerBO extends superBO {
    public boolean saveCustomer(customerDTO customer) throws SQLException, ClassNotFoundException;
    public boolean updateCustomer(customerDTO customer) throws SQLException, ClassNotFoundException;
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
    public customerDTO getCustomer(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<customerDTO> getAllCustomer(String text) throws SQLException, ClassNotFoundException;
}
