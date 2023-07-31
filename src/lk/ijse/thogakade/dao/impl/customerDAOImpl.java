package lk.ijse.thogakade.dao.impl;

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
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO Customer values(?,?,?,?)");
        stm.setObject(1,customer.getId());
        stm.setObject(2,customer.getName());
        stm.setObject(3,customer.getAddress());
        stm.setObject(4,customer.getSalary());
        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean updateCustomer(customer customer) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("UPDATE Customer SET name=?,address=?,salary=? WHERE id=?");
        stm.setObject(4,customer.getId());
        stm.setObject(1,customer.getName());
        stm.setObject(2,customer.getAddress());
        stm.setObject(3,customer.getSalary());
        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm1 = DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM Customer WHERE id = ?");
        stm1.setObject(1,id);
        return stm1.executeUpdate() > 0;
    }

    @Override
    public customer getCustomer(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer WHERE id = ?");
        stm.setObject(1,id);
        ResultSet rst = stm.executeQuery();

        if(rst.next()){
            return new customer(rst.getString(1),rst.getString(2),rst.getString(3),rst.getDouble(4));
        }
        return null;
    }

    @Override
    public ArrayList<customer> getAllCustomer(String text) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer WHERE id LIKE ? OR name LIKE ? OR address LIKE ?");
        stm.setObject(1,text);
        stm.setObject(2,text);
        stm.setObject(3,text);
        ResultSet rst = stm.executeQuery();

        ArrayList<customer> customerEntityList = new ArrayList<>();
        while(rst.next()){
            customerEntityList.add(new customer(rst.getString(1),rst.getString(2),rst.getString(3),rst.getDouble(4)));
        }
        return customerEntityList;
    }
}
