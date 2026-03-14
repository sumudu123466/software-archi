package lk.ijse.foodcity.dao;

import lk.ijse.foodcity.entity.Item;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lk.ijse.foodcity.dto.ItemDTO;

public interface ItemDAO extends SuperDAO {
    boolean save(Item entity) throws SQLException, ClassNotFoundException;
    boolean update(Item entity) throws SQLException, ClassNotFoundException;
    boolean delete(String code) throws SQLException, ClassNotFoundException;
    ArrayList<Item> getAll() throws SQLException, ClassNotFoundException;
    List<String> getCodes() throws SQLException, ClassNotFoundException;
    Item find(String code) throws SQLException, ClassNotFoundException;
    List<String> getAllCodes() throws SQLException, ClassNotFoundException;
    ItemDTO search(String itemCode) throws SQLException, ClassNotFoundException;
}