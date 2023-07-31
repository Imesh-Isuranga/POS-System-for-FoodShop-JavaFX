package lk.ijse.thogakade.BO.Impl;

import lk.ijse.thogakade.BO.custom.customerBO;
import lk.ijse.thogakade.dao.DAOFactory;
import lk.ijse.thogakade.dao.custom.customerDAO;
import lk.ijse.thogakade.dto.customerDTO;
import lk.ijse.thogakade.entity.customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class customerBOImpl implements customerBO {
    customerDAO customerDAO = DAOFactory.getInstance().getDao(DAOFactory.DaoType.CUSTOMER);
    @Override
    public boolean saveCustomer(customerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new customer(customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress(),customerDTO.getSalary()));
    }

    @Override
    public boolean updateCustomer(customerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new customer(customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress(),customerDTO.getSalary()));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public customerDTO getCustomer(String id) throws SQLException, ClassNotFoundException {
        customer customer = customerDAO.get(id);

        if(customer!=null){
            return new customerDTO(customer.getId(),customer.getName(),customer.getAddress(),customer.getSalary());
        }
        return null;
    }

    @Override
    public ArrayList<customerDTO> getAllCustomer(String text) throws SQLException, ClassNotFoundException {
        ArrayList<customer> customerList = customerDAO.getAll(text);
        ArrayList<customerDTO> customerDTOList = new ArrayList<>();

        for (customer customer:customerList
        ) {
            customerDTOList.add(new customerDTO(customer.getId(),customer.getName(),customer.getAddress(),customer.getSalary()));
        }
        return customerDTOList;
    }
}
