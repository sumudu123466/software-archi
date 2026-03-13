package lk.ijse.foodcity.dao;

import lk.ijse.foodcity.entity.Item;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO {
    boolean save(Item entity) throws SQLException, ClassNotFoundException;
    boolean update(Item entity) throws SQLException, ClassNotFoundException;
    boolean delete(String code) throws SQLException, ClassNotFoundException;
    ArrayList<Item> getAll() throws SQLException, ClassNotFoundException;
}