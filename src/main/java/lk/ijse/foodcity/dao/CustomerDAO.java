package lk.ijse.foodcity.dao;

import lk.ijse.foodcity.entity.Customer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerDAO extends SuperDAO {
    boolean save(Customer entity) throws SQLException, ClassNotFoundException;
    boolean update(Customer entity) throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException;
    List<String> getIds() throws SQLException, ClassNotFoundException;
    List<String> getAllIds() throws SQLException, ClassNotFoundException;
}