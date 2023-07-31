package lk.ijse.thogakade.dao;

import lk.ijse.thogakade.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class crudUtill {
    public static PreparedStatement getPreparedStatement(String SQL,Object...params) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
        for (int i=0;i< params.length;i++){
            stm.setObject((i+1),params[i]);
        }
        return stm;
    }

    public static boolean executeUpdate(String SQL,Object...params) throws SQLException, ClassNotFoundException {
        return getPreparedStatement(SQL,params).executeUpdate()>0;
    }

    public static ResultSet executeQuery(String SQL, Object...params) throws SQLException, ClassNotFoundException {
        return getPreparedStatement(SQL,params).executeQuery();
    }
}
