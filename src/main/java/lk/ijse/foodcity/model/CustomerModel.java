package lk.ijse.foodcity.model;

import lk.ijse.foodcity.dto.CustomerDTO;
import lk.ijse.foodcity.util.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {

    public static boolean saveCustomer(CustomerDTO dto) throws SQLException {
        // cus_id is auto-increment, so we only insert name, contact, email, and id
        return CrudUtil.execute(
                "INSERT INTO customer (name, contact, email, id) VALUES (?,?,?,?)",
                dto.getName(), dto.getContact(), dto.getEmail(), dto.getId()
        );
    }

    public static boolean updateCustomer(CustomerDTO dto) throws SQLException {
        return CrudUtil.execute(
                "UPDATE customer SET name=?, contact=?, email=? WHERE id=?",
                dto.getName(), dto.getContact(), dto.getEmail(), dto.getId()
        );
    }

    public static boolean deleteCustomer(String id) throws SQLException {
        return CrudUtil.execute("DELETE FROM customer WHERE id=?", id);
    }

    public static ArrayList<CustomerDTO> getAllCustomers() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM customer");
        ArrayList<CustomerDTO> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new CustomerDTO(
                    rs.getInt(1),    // cus_id
                    rs.getString(2), // name
                    rs.getString(3), // contact
                    rs.getString(4), // email
                    rs.getString(5)  // id
            ));
        }
        return list;
    }

    public static ArrayList<String> getAllCustomerIds() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT id FROM customer");
        ArrayList<String> list = new ArrayList<>();
        while (rst.next()) {
            list.add(rst.getString(1));
        }
        return list;
    }
}