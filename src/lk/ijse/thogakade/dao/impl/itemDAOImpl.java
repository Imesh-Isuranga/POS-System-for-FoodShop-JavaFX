package lk.ijse.thogakade.dao.impl;

import lk.ijse.thogakade.dao.custom.itemDAO;
import lk.ijse.thogakade.db.DBConnection;
import lk.ijse.thogakade.dto.itemDTO;
import lk.ijse.thogakade.entity.item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class itemDAOImpl implements itemDAO {
    @Override
    public boolean saveItem(item item) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO Item VALUES(?,?,?,?)");
        stm.setObject(1,item.getCode());
        stm.setObject(2,item.getDescription());
        stm.setObject(3,item.getUnitPrice());
        stm.setObject(4,item.getQTYOnHand());
        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean updateItem(item item) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("UPDATE Item SET description=?,unitPrice=?,QTYOnHand=? WHERE Code=?");
        stm.setObject(4,item.getCode());
        stm.setObject(1,item.getDescription());
        stm.setObject(2,item.getUnitPrice());
        stm.setObject(3,item.getQTYOnHand());
        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm1 = DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM Item WHERE code = ?");
        stm1.setObject(1,id);
        return stm1.executeUpdate() > 0;
    }

    @Override
    public item getItem(String code) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item WHERE code=?");
        stm.setObject(1,code);
        ResultSet rst = stm.executeQuery();

        if(rst.next()){
            return new item(rst.getString(1),rst.getString(2),rst.getDouble(3),rst.getInt(4));
        }
        return null;
    }

    @Override
    public ArrayList<item> getAllItem(String text) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item WHERE code LIKE ? OR description LIKE ? OR unitPrice LIKE ? OR QTYOnHand LIKE ?");
        stm.setObject(1,text);
        stm.setObject(2,text);
        stm.setObject(3,text);
        stm.setObject(4,text);
        ResultSet rst = stm.executeQuery();

        ArrayList<item> itemEntityList = new ArrayList<>();
        while (rst.next()){
            itemEntityList.add(new item(rst.getString(1),rst.getString(2),rst.getDouble(3),rst.getInt(4)));
        }
        return itemEntityList;
    }
}
