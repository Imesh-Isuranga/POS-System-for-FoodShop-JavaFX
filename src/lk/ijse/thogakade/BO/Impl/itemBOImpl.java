package lk.ijse.thogakade.BO.Impl;

import lk.ijse.thogakade.BO.custom.itemBO;
import lk.ijse.thogakade.dao.DAOFactory;
import lk.ijse.thogakade.dao.custom.itemDAO;
import lk.ijse.thogakade.dto.itemDTO;
import lk.ijse.thogakade.entity.item;

import java.sql.SQLException;
import java.util.ArrayList;

public class itemBOImpl implements itemBO {

    itemDAO itemDAO = DAOFactory.getInstance().getDao(DAOFactory.DaoType.ITEM);
    @Override
    public boolean saveItem(itemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new item(itemDTO.getCode(),itemDTO.getDescription(),itemDTO.getUnitPrice(),itemDTO.getQTYOnHand()));
    }

    @Override
    public boolean updateItem(itemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new item(itemDTO.getCode(),itemDTO.getDescription(),itemDTO.getUnitPrice(),itemDTO.getQTYOnHand()));
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(id);
    }

    @Override
    public itemDTO getItem(String code) throws SQLException, ClassNotFoundException {
        item item = itemDAO.get(code);
        if(item!=null){
            return new itemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQTYOnHand());
        }
        return null;
    }

    @Override
    public ArrayList<itemDTO> getAllItem(String text) throws SQLException, ClassNotFoundException {
        ArrayList<itemDTO> itemDTOList = new ArrayList<>();
        ArrayList<item> itemList = itemDAO.getAll(text);

        for (item item:itemList
        ) {
            itemDTOList.add(new itemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQTYOnHand()));
        }
        return itemDTOList;
    }
}
