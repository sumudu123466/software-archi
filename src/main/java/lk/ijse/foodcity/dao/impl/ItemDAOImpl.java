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
        // Table එකේ columns (code, name, price, qty) ලෙස ඇති බව සහතික කරගන්න
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
        // Update එකේදී WHERE clause එකට අදාළ අගය (code) අන්තිමටම තිබිය යුතුයි
        return CrudUtil.execute(
                "UPDATE item SET name=?, price=?, qty=? WHERE code=?",
                entity.getName(),      // 1st ?
                entity.getUnitPrice(), // 2nd ?
                entity.getQtyOnHand(), // 3rd ?
                entity.getCode()       // 4th ?
        );
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        // Delete query එකේ column name එක හරියටම 'code' ද කියලා බලන්න
        return CrudUtil.execute("DELETE FROM item WHERE code=?", code);
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        // ResultSet එකෙන් data ගනිද්දී column index (1,2,3,4) පාවිච්චි කිරීම වඩාත් ආරක්ෂිතයි
        ResultSet rst = CrudUtil.execute("SELECT * FROM item");
        ArrayList<Item> itemList = new ArrayList<>();
        while (rst.next()) {
            itemList.add(new Item(
                    rst.getString(1), // code
                    rst.getString(2), // name
                    rst.getDouble(3), // price
                    rst.getInt(4)     // qty
            ));
        }
        return itemList;
    }
}