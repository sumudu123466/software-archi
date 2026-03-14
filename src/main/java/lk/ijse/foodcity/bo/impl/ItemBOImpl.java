package lk.ijse.foodcity.bo.impl;

import lk.ijse.foodcity.bo.custom.ItemBO;
import lk.ijse.foodcity.dao.DAOFactory;
import lk.ijse.foodcity.dao.ItemDAO;
import lk.ijse.foodcity.dao.impl.ItemDAOImpl;
import lk.ijse.foodcity.dto.ItemDTO;
import lk.ijse.foodcity.entity.Item;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(dto.getCode(), dto.getName(), dto.getPrice(), dto.getQty()));
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(dto.getCode(), dto.getName(), dto.getPrice(), dto.getQty()));
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(code);
    }

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> all = itemDAO.getAll();
        ArrayList<ItemDTO> allDTO = new ArrayList<>();
        for (Item i : all) {

            allDTO.add(new ItemDTO(
                    i.getCode(),
                    i.getName(),
                    i.getPrice(),
                    i.getQty()
            ));
        }
        return allDTO;
    }
}