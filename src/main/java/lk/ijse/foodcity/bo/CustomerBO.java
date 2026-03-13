package lk.ijse.foodcity.bo;

import lk.ijse.foodcity.dto.CustomerDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO {
    boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
    ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;
}