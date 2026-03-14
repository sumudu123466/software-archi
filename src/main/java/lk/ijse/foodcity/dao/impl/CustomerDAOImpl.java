package lk.ijse.foodcity.dao.impl;

import lk.ijse.foodcity.dao.CustomerDAO;
import lk.ijse.foodcity.db.DBConnection;
import lk.ijse.foodcity.entity.Customer;
import lk.ijse.foodcity.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "INSERT INTO customer (cus_id, name, contact, email, id) VALUES (?,?,?,?,?)",
                entity.getCus_id(), entity.getName(), entity.getContact(), entity.getEmail(), entity.getId()
        );
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute(
                "UPDATE customer SET name=?, contact=?, email=? WHERE id=?",
                entity.getName(), entity.getContact(), entity.getEmail(), entity.getId()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("DELETE FROM customer WHERE id=?", id);
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {

        ResultSet rst = CrudUtil.execute("SELECT * FROM customer");
        ArrayList<Customer> customerList = new ArrayList<>();

        while (rst.next()) {
            customerList.add(new Customer(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            ));
        }
        return customerList;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT cus_id FROM customer");
        List<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(rst.getString(1));
        }
        return ids;
    }

    @Override
    public List<String> getAllIds() throws SQLException, ClassNotFoundException {
        String sql = "SELECT cus_id FROM customer";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();

        List<String> customerIds = new ArrayList<>();
        while (rs.next()) {
            customerIds.add(rs.getString("cus_id"));
        }
        return customerIds;
    }
}