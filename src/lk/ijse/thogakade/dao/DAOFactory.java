package lk.ijse.thogakade.dao;

import lk.ijse.thogakade.dao.impl.customerDAOImpl;
import lk.ijse.thogakade.dao.impl.itemDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){

    }

    public static DAOFactory getInstance(){
        if(daoFactory==null){
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public enum DaoType{
        CUSTOMER,ITEM,ORDER,ORDER_DETAILS;
    }

    public <T> T getDao(DaoType type){
        switch (type){
            case CUSTOMER:
                return (T) new customerDAOImpl();
            case ITEM:
                return (T) new itemDAOImpl();
            case ORDER:
                return null;
            case ORDER_DETAILS:
                return null;
            default:
                return null;
        }
    }
}
