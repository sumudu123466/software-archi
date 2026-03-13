package lk.ijse.foodcity.model;

import lk.ijse.foodcity.dto.ItemDTO;
import lk.ijse.foodcity.util.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemModel {


    public static boolean saveItem(ItemDTO itemDTO) throws SQLException {
        return CrudUtil.execute(
                "INSERT INTO item (name, price, qty, code) VALUES (?, ?, ?, ?)",
                itemDTO.getName(),
                itemDTO.getPrice(),
                itemDTO.getQty(),
                itemDTO.getCode()
        );
    }


    public static List<ItemDTO> getAllItems() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM item");
        List<ItemDTO> itemList = new ArrayList<>();
        while (rst.next()) {

            itemList.add(new ItemDTO(
                    rst.getString("code"),  // Column 5 හෝ "code"
                    rst.getString("name"),  // Column 2
                    rst.getDouble("price"), // Column 3
                    rst.getInt("qty")       // Column 4
            ));
        }
        return itemList;
    }


    public static ArrayList<String> getItemCodesForCombo() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT code FROM item");
        ArrayList<String> list = new ArrayList<>();
        while (rst.next()) {
            list.add(rst.getString(1));
        }
        return list;
    }


    public static ItemDTO findItem(String code) throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM item WHERE code=?", code);
        if (rst.next()) {

            return new ItemDTO(
                    rst.getString("code"),
                    rst.getString("name"),
                    rst.getDouble("price"),
                    rst.getInt("qty")
            );
        }
        return null;
    }
}