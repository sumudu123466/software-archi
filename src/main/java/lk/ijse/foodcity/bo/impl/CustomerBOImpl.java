package lk.ijse.foodcity.bo.impl;

import lk.ijse.foodcity.bo.custom.CustomerBO;
import lk.ijse.foodcity.dao.CustomerDAO;
import lk.ijse.foodcity.dao.DAOFactory;
import lk.ijse.foodcity.dao.impl.CustomerDAOImpl;
import lk.ijse.foodcity.db.DBConnection;
import lk.ijse.foodcity.dto.CustomerDTO;
import lk.ijse.foodcity.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(dto.getCus_id(), dto.getName(), dto.getContact(), dto.getEmail(), dto.getId()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {

        return customerDAO.update(new Customer(dto.getCus_id(), dto.getName(), dto.getContact(), dto.getEmail(), dto.getId()));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> allEntities = customerDAO.getAll();
        ArrayList<CustomerDTO> allDTOs = new ArrayList<>();


        for (Customer c : allEntities) {
            allDTOs.add(new CustomerDTO(c.getCus_id(), c.getName(), c.getContact(), c.getEmail(), c.getId()));
        }
        return allDTOs;
    }


    public List<String> getIds() throws SQLException, ClassNotFoundException {
        String sql = "SELECT nic FROM customer";
        ResultSet resultSet = DBConnection.getInstance().getConnection().createStatement().executeQuery(sql);

        List<String> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }
}