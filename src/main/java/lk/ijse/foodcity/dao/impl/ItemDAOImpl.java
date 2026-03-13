package lk.ijse.foodcity.dao.impl;

import lk.ijse.foodcity.dao.ItemDAO;
import lk.ijse.foodcity.entity.Item;
import lk.ijse.foodcity.util.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        
        return CrudUtil.execute(
                "INSERT INTO item (code, name, price, qty) VALUES (?,?,?,?)",
                entity.getCode(),
                entity.getName(),
                entity.getUnitPrice(),
                entity.getQtyOnHand()
        );
    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        
        return CrudUtil.execute(
                "UPDATE item SET name=?, price=?, qty=? WHERE code=?",
                entity.getName(),      
                entity.getUnitPrice(), 
                entity.getQtyOnHand(), 
                entity.getCode()      
        );
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
       
        return CrudUtil.execute("DELETE FROM item WHERE code=?", code);
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        
        ResultSet rst = CrudUtil.execute("SELECT * FROM item");
        ArrayList<Item> itemList = new ArrayList<>();
        while (rst.next()) {
            itemList.add(new Item(
                    rst.getString(1), 
                    rst.getString(2), 
                    rst.getDouble(3), 
                    rst.getInt(4)   
            ));
        }
        return itemList;
    }
}
