package lk.ijse.foodcity.dao.impl;

import lk.ijse.foodcity.dao.CustomerDAO;
import lk.ijse.foodcity.entity.Customer;
import lk.ijse.foodcity.util.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        // NIC (id) එක පාවිච්චි කරලා අදාළ පාරිභෝගිකයාගේ විස්තර Update කිරීම
        return CrudUtil.execute(
                "UPDATE customer SET name=?, contact=?, email=? WHERE id=?",
                entity.getName(), entity.getContact(), entity.getEmail(), entity.getId()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        // NIC එක පාවිච්චි කරලා පාරිභෝගිකයා ඉවත් කිරීම
        return CrudUtil.execute("DELETE FROM customer WHERE id=?", id);
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        // සියලුම පාරිභෝගිකයින් Database එකෙන් ලබා ගැනීම
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
}