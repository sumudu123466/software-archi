package lk.ijse.foodcity.bo;

import lk.ijse.foodcity.dto.ItemDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO {
    boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteItem(String code) throws SQLException, ClassNotFoundException;
    ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;
}