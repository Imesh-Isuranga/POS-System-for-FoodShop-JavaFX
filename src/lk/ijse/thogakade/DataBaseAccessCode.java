package lk.ijse.thogakade;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lk.ijse.thogakade.dao.CrudDAO;
import lk.ijse.thogakade.dao.impl.customerDAOImpl;
import lk.ijse.thogakade.dao.impl.itemDAOImpl;
import lk.ijse.thogakade.db.DBConnection;
import lk.ijse.thogakade.dto.customerDTO;
import lk.ijse.thogakade.dto.itemDTO;
import lk.ijse.thogakade.entity.customer;
import lk.ijse.thogakade.entity.item;

import java.sql.*;
import java.util.ArrayList;

public class DataBaseAccessCode {
    public boolean saveCustomer(customerDTO customerDTO) throws ClassNotFoundException, SQLException {
        return new customerDAOImpl().save(new customer(customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress(),customerDTO.getSalary()));
    }

    public boolean updateCustomer(customerDTO customerDTO) throws ClassNotFoundException, SQLException {
        return new customerDAOImpl().update(new customer(customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress(),customerDTO.getSalary()));
    }

    public boolean deleteCustomer(String id) throws ClassNotFoundException, SQLException {
        return new customerDAOImpl().delete(id);
    }

    public customerDTO getCustomer(String id) throws ClassNotFoundException, SQLException {
        customer customer = new customerDAOImpl().get(id);

        if(customer!=null){
            return new customerDTO(customer.getId(),customer.getName(),customer.getAddress(),customer.getSalary());
        }
        return null;
    }

    public ArrayList<customerDTO> getAllCustomer(String text) throws ClassNotFoundException, SQLException {
        ArrayList<customer> customerList = new customerDAOImpl().getAll(text);
        ArrayList<customerDTO> customerDTOList = new ArrayList<>();

        for (customer customer:customerList
             ) {
            customerDTOList.add(new customerDTO(customer.getId(),customer.getName(),customer.getAddress(),customer.getSalary()));
        }
        return customerDTOList;
    }


    public boolean saveItem(itemDTO itemDTO) throws ClassNotFoundException, SQLException {
        return new itemDAOImpl().save(new item(itemDTO.getCode(),itemDTO.getDescription(),itemDTO.getUnitPrice(),itemDTO.getQTYOnHand()));
    }

    public boolean updateItem(itemDTO itemDTO) throws ClassNotFoundException, SQLException {
        return new itemDAOImpl().update(new item(itemDTO.getCode(),itemDTO.getDescription(),itemDTO.getUnitPrice(),itemDTO.getQTYOnHand()));
    }

    public boolean deleteItem(String id) throws ClassNotFoundException, SQLException {
        return new itemDAOImpl().delete(id);
    }

    public itemDTO getItem(String code) throws ClassNotFoundException, SQLException {
        item item = new itemDAOImpl().get(code);
        if(item!=null){
            return new itemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQTYOnHand());
        }
        return null;
    }

    public ArrayList<itemDTO> getAllItem(String text) throws ClassNotFoundException, SQLException {
        ArrayList<itemDTO> itemDTOList = new ArrayList<>();
        ArrayList<item> itemList = new itemDAOImpl().getAll(text);

        for (item item:itemList
             ) {
            itemDTOList.add(new itemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQTYOnHand()));
        }
        return itemDTOList;
    }
}
