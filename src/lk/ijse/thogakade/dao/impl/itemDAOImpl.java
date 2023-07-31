package lk.ijse.thogakade.dao.impl;

import lk.ijse.thogakade.dao.crudUtill;
import lk.ijse.thogakade.dao.custom.itemDAO;
import lk.ijse.thogakade.db.DBConnection;
import lk.ijse.thogakade.db.HibernateUtil;
import lk.ijse.thogakade.dto.itemDTO;
import lk.ijse.thogakade.entity.customer;
import lk.ijse.thogakade.entity.item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class itemDAOImpl implements itemDAO {
    @Override
    public boolean save(item item) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.openSesion();
        Transaction transaction = session.beginTransaction();
        session.save(item);
        transaction.commit();
        return true;
        //return crudUtill.executeUpdate("INSERT INTO Item VALUES(?,?,?,?)",item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQTYOnHand());
    }

    @Override
    public boolean update(item item) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.openSesion();
        Transaction transaction = session.beginTransaction();
        session.update(item);
        transaction.commit();
        session.close();
        return true;
        //return crudUtill.executeUpdate("UPDATE Item SET description=?,unitPrice=?,QTYOnHand=? WHERE Code=?",item.getDescription(),item.getUnitPrice(),item.getQTYOnHand(),item.getCode());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.openSesion();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE item WHERE code=:code");
        query.setParameter("code",id);
        query.executeUpdate();
        transaction.commit();
        return true;
       // return crudUtill.executeUpdate("DELETE FROM Item WHERE code = ?",id);
    }

    @Override
    public item get(String code) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.openSesion();
        return session.get(item.class,code);

        /*ResultSet rst = crudUtill.executeQuery("SELECT * FROM Item WHERE code=?",code);

        if(rst.next()){
            return new item(rst.getString(1),rst.getString(2),rst.getDouble(3),rst.getInt(4));
        }
        return null;*/
    }

    @Override
    public ArrayList<item> getAll(String text) throws SQLException, ClassNotFoundException {
        Session session = HibernateUtil.openSesion();
        Query query = session.createQuery("FROM item");
        List<item> list = query.list();
        ArrayList<item> itemEntityList = new ArrayList<>();

        for (item item:list
        ) {
            itemEntityList.add(new item(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQTYOnHand()));
        }
        return itemEntityList;

        /*ResultSet rst = crudUtill.executeQuery("SELECT * FROM Item WHERE code LIKE ? OR description LIKE ? OR unitPrice LIKE ? OR QTYOnHand LIKE ?",text,text,text,text);

        ArrayList<item> itemEntityList = new ArrayList<>();
        while (rst.next()){
            itemEntityList.add(new item(rst.getString(1),rst.getString(2),rst.getDouble(3),rst.getInt(4)));
        }
        return itemEntityList;*/
    }
    /*@Override
    public boolean saveItem(item item) throws SQLException, ClassNotFoundException {
        return crudUtill.executeUpdate("INSERT INTO Item VALUES(?,?,?,?)",item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQTYOnHand());
    }

    @Override
    public boolean updateItem(item item) throws SQLException, ClassNotFoundException {
        return crudUtill.executeUpdate("UPDATE Item SET description=?,unitPrice=?,QTYOnHand=? WHERE Code=?",item.getDescription(),item.getUnitPrice(),item.getQTYOnHand(),item.getCode());
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return crudUtill.executeUpdate("DELETE FROM Item WHERE code = ?",id);
    }

    @Override
    public item getItem(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = crudUtill.executeQuery("SELECT * FROM Item WHERE code=?",code);

        if(rst.next()){
            return new item(rst.getString(1),rst.getString(2),rst.getDouble(3),rst.getInt(4));
        }
        return null;
    }

    @Override
    public ArrayList<item> getAllItem(String text) throws SQLException, ClassNotFoundException {
        ResultSet rst = crudUtill.executeQuery("SELECT * FROM Item WHERE code LIKE ? OR description LIKE ? OR unitPrice LIKE ? OR QTYOnHand LIKE ?",text,text,text,text);

        ArrayList<item> itemEntityList = new ArrayList<>();
        while (rst.next()){
            itemEntityList.add(new item(rst.getString(1),rst.getString(2),rst.getDouble(3),rst.getInt(4)));
        }
        return itemEntityList;
    }*/
}
