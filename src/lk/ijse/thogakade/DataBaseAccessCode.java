package lk.ijse.thogakade;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lk.ijse.thogakade.dto.customerDTO;

import java.sql.*;
import java.util.ArrayList;

public class DataBaseAccessCode {
    public boolean saveCustomer(customerDTO customerDTO) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Thogakade", "root", "imesh");
        PreparedStatement stm = connection.prepareStatement("INSERT INTO Customer values(?,?,?,?)");
        stm.setObject(1,customerDTO.getId());
        stm.setObject(2,customerDTO.getName());
        stm.setObject(3,customerDTO.getAddress());
        stm.setObject(4,customerDTO.getSalary());
        return stm.executeUpdate() > 0;
    }

    public boolean updateCustomer(customerDTO customerDTO) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Thogakade", "root", "imesh");
        PreparedStatement stm = connection.prepareStatement("UPDATE Customer SET name=?,address=?,salary=? WHERE id=?");
        stm.setObject(4,customerDTO.getId());
        stm.setObject(1,customerDTO.getName());
        stm.setObject(2,customerDTO.getAddress());
        stm.setObject(3,customerDTO.getSalary());
        return stm.executeUpdate() > 0;
    }

    public boolean deleteCustomer(String id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/Thogakade", "root", "imesh");
        PreparedStatement stm1 = connection1.prepareStatement("DELETE FROM Customer WHERE id = ?");
        stm1.setObject(1,id);
        return stm1.executeUpdate() > 0;
    }

    public customerDTO getCustomer(String id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Thogakade", "root", "imesh");
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM Customer WHERE id = ?");
        stm.setObject(1,id);
        ResultSet rst = stm.executeQuery();

        if(rst.next()){
            return new customerDTO(rst.getString(1),rst.getString(2),rst.getString(3),rst.getDouble(4));
        }
        return null;
    }

    public ArrayList<customerDTO> getAllCustomer(String text) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Thogakade", "root", "imesh");
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM Customer WHERE id LIKE ? OR name LIKE ? OR address LIKE ?");
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

}
