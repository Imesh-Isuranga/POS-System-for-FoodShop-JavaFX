package lk.ijse.thogakade;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lk.ijse.thogakade.db.DBConnection;
import lk.ijse.thogakade.dto.customerDTO;
import lk.ijse.thogakade.dto.itemDTO;

import java.sql.*;
import java.util.ArrayList;

public class DataBaseAccessCode {
    public boolean saveCustomer(customerDTO customerDTO) throws ClassNotFoundException, SQLException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO Customer values(?,?,?,?)");
        stm.setObject(1,customerDTO.getId());
        stm.setObject(2,customerDTO.getName());
        stm.setObject(3,customerDTO.getAddress());
        stm.setObject(4,customerDTO.getSalary());
        return stm.executeUpdate() > 0;
    }

    public boolean updateCustomer(customerDTO customerDTO) throws ClassNotFoundException, SQLException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("UPDATE Customer SET name=?,address=?,salary=? WHERE id=?");
        stm.setObject(4,customerDTO.getId());
        stm.setObject(1,customerDTO.getName());
        stm.setObject(2,customerDTO.getAddress());
        stm.setObject(3,customerDTO.getSalary());
        return stm.executeUpdate() > 0;
    }

    public boolean deleteCustomer(String id) throws ClassNotFoundException, SQLException {
        PreparedStatement stm1 = DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM Customer WHERE id = ?");
        stm1.setObject(1,id);
        return stm1.executeUpdate() > 0;
    }

    public customerDTO getCustomer(String id) throws ClassNotFoundException, SQLException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer WHERE id = ?");
        stm.setObject(1,id);
        ResultSet rst = stm.executeQuery();

        if(rst.next()){
            return new customerDTO(rst.getString(1),rst.getString(2),rst.getString(3),rst.getDouble(4));
        }
        return null;
    }

    public ArrayList<customerDTO> getAllCustomer(String text) throws ClassNotFoundException, SQLException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer WHERE id LIKE ? OR name LIKE ? OR address LIKE ?");
        stm.setObject(1,text);
        stm.setObject(2,text);
        stm.setObject(3,text);
        ResultSet rst = stm.executeQuery();

        ArrayList<customerDTO> customerDTOList = new ArrayList<>();
        while(rst.next()){
            customerDTOList.add(new customerDTO(rst.getString(1),rst.getString(2),rst.getString(3),rst.getDouble(4)));
        }
        return customerDTOList;
    }


    public boolean saveItem(itemDTO itemDTO) throws ClassNotFoundException, SQLException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO Item VALUES(?,?,?,?)");
        stm.setObject(1,itemDTO.getCode());
        stm.setObject(2,itemDTO.getDescription());
        stm.setObject(3,itemDTO.getUnitPrice());
        stm.setObject(4,itemDTO.getQTYOnHand());
        return stm.executeUpdate() > 0;
    }

    public boolean updateItem(itemDTO itemDTO) throws ClassNotFoundException, SQLException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("UPDATE Item SET description=?,unitPrice=?,QTYOnHand=? WHERE Code=?");
        stm.setObject(4,itemDTO.getCode());
        stm.setObject(1,itemDTO.getDescription());
        stm.setObject(2,itemDTO.getUnitPrice());
        stm.setObject(3,itemDTO.getQTYOnHand());
        return stm.executeUpdate() > 0;
    }

    public boolean deleteItem(String id) throws ClassNotFoundException, SQLException {
        PreparedStatement stm1 = DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM Item WHERE code = ?");
        stm1.setObject(1,id);
        return stm1.executeUpdate() > 0;
    }

    public itemDTO getItem(String code) throws ClassNotFoundException, SQLException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item WHERE code=?");
        stm.setObject(1,code);
        ResultSet rst = stm.executeQuery();

        if(rst.next()){
            return new itemDTO(rst.getString(1),rst.getString(2),rst.getDouble(3),rst.getInt(4));
        }
        return null;
    }

    public ArrayList<itemDTO> getAllItem(String text) throws ClassNotFoundException, SQLException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item WHERE code LIKE ? OR description LIKE ? OR unitPrice LIKE ? OR QTYOnHand LIKE ?");
        stm.setObject(1,text);
        stm.setObject(2,text);
        stm.setObject(3,text);
        stm.setObject(4,text);
        ResultSet rst = stm.executeQuery();

        ArrayList<itemDTO> itemDTOList = new ArrayList<>();
        while (rst.next()){
            itemDTOList.add(new itemDTO(rst.getString(1),rst.getString(2),rst.getDouble(3),rst.getInt(4)));
        }
        return itemDTOList;
    }
}
