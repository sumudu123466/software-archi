package lk.ijse.foodcity.dao.impl;

import lk.ijse.foodcity.dao.ItemDAO;
import lk.ijse.foodcity.db.DBConnection;
import lk.ijse.foodcity.dto.ItemDTO;
import lk.ijse.foodcity.entity.Item;
import lk.ijse.foodcity.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "INSERT INTO items (code, name, price, qty) VALUES (?,?,?,?)",
                entity.getCode(),
                entity.getName(),
                entity.getPrice(),
                entity.getQty()
        );
    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "UPDATE items SET name=?, price=?, qty=? WHERE code=?",
                entity.getName(),
                entity.getPrice(),
                entity.getQty(),
                entity.getCode()
        );
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM items WHERE code=?", code);
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM items");
        ArrayList<Item> itemList = new ArrayList<>();
        while (rst.next()) {

            itemList.add(new Item(
                    rst.getString("code"),
                    rst.getString("name"),
                    rst.getDouble("price"),
                    rst.getInt("qty")
            ));
        }
        return itemList;
    }

    @Override
    public List<String> getCodes() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT code FROM items");
        List<String> itemCodes = new ArrayList<>();
        while (rst.next()) {
            itemCodes.add(rst.getString("code"));
        }
        return itemCodes;
    }

    @Override
    public Item find(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM items WHERE code = ?", code);
        if (rst.next()) {
            return new Item(
                    rst.getString("code"),
                    rst.getString("name"),
                    rst.getDouble("price"),
                    rst.getInt("qty")
            );
        }
        return null;
    }

    @Override
    public List<String> getAllCodes() throws SQLException, ClassNotFoundException {
        String sql = "SELECT code FROM items";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<String> codes = new ArrayList<>();
        while (rs.next()) {
            codes.add(rs.getString("code"));
        }
        return codes;
    }

    @Override
    public ItemDTO search(String itemCode) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM items WHERE code = ?";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setString(1, itemCode);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            return new ItemDTO(
                    rs.getString("code"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("qty")
            );
        }
        return null;
    }
}