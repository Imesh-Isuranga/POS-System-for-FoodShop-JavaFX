package lk.ijse.thogakade.dao.custom;

import lk.ijse.thogakade.dto.customerDTO;
import lk.ijse.thogakade.entity.customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface customerDAO {
    public boolean saveCustomer(customer customer) throws SQLException, ClassNotFoundException;
    public boolean updateCustomer(customer customer) throws SQLException, ClassNotFoundException;
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
    public customer getCustomer(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<customer> getAllCustomer(String text) throws SQLException, ClassNotFoundException;
}
