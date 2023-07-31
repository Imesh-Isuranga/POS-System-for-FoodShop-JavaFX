package lk.ijse.thogakade.dao.impl;

import lk.ijse.thogakade.dao.crudUtill;
import lk.ijse.thogakade.dao.custom.customerDAO;
import lk.ijse.thogakade.db.DBConnection;
import lk.ijse.thogakade.dto.customerDTO;
import lk.ijse.thogakade.entity.customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class customerDAOImpl implements customerDAO {
    @Override
    public boolean saveCustomer(customer customer) throws SQLException, ClassNotFoundException {
        return crudUtill.executeUpdate("INSERT INTO Customer values(?,?,?,?)",customer.getId(),customer.getName(),customer.getAddress(),customer.getSalary());
    }

    @Override
    public boolean updateCustomer(customer customer) throws SQLException, ClassNotFoundException {
        return crudUtill.executeUpdate("UPDATE Customer SET name=?,address=?,salary=? WHERE id=?",customer.getName(),customer.getAddress(),customer.getSalary(),customer.getId());
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return crudUtill.executeUpdate("DELETE FROM Customer WHERE id = ?",id);
    }

    @Override
    public customer getCustomer(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = crudUtill.executeQuery("SELECT * FROM Customer WHERE id = ?",id);

        if(rst.next()){
            return new customer(rst.getString(1),rst.getString(2),rst.getString(3),rst.getDouble(4));
        }
        return null;
    }

    @Override
    public ArrayList<customer> getAllCustomer(String text) throws SQLException, ClassNotFoundException {
        ResultSet rst = crudUtill.executeQuery("SELECT * FROM Customer WHERE id LIKE ? OR name LIKE ? OR address LIKE ?",text,text,text);

        ArrayList<customer> customerEntityList = new ArrayList<>();
        while(rst.next()){
            customerEntityList.add(new customer(rst.getString(1),rst.getString(2),rst.getString(3),rst.getDouble(4)));
        }
        return customerEntityList;
    }
}
