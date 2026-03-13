package lk.ijse.foodcity.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.foodcity.bo.CustomerBO;
import lk.ijse.foodcity.bo.impl.CustomerBOImpl;
import lk.ijse.foodcity.dto.CustomerDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerController {

    @FXML private TextField txtCustId;
    @FXML private TextField txtCustName;
    @FXML private TextField txtCustContact;
    @FXML private TextField txtCustEmail;

    @FXML private TableView<CustomerDTO> tblCustomer;
    @FXML private TableColumn<CustomerDTO, String> colId;
    @FXML private TableColumn<CustomerDTO, String> colName;
    @FXML private TableColumn<CustomerDTO, String> colContact;
    @FXML private TableColumn<CustomerDTO, String> colEmail;

    CustomerBO customerBO = new CustomerBOImpl();

    public void initialize() {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        loadAllCustomers();



        tblCustomer.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                txtCustId.setText(newVal.getId());
                txtCustName.setText(newVal.getName());
                txtCustContact.setText(newVal.getContact());
                txtCustEmail.setText(newVal.getEmail());
            }
        });
    }

    @FXML
    void saveCustomer(ActionEvent event) {
        if (txtCustId.getText().isEmpty() || txtCustName.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please fill required fields!").show();
            return;
        }


        CustomerDTO dto = new CustomerDTO(0, txtCustName.getText(), txtCustContact.getText(), txtCustEmail.getText(), txtCustId.getText());

        try {

            if (customerBO.saveCustomer(dto)) {
                new Alert(Alert.AlertType.INFORMATION, "Customer Saved!").show();
                loadAllCustomers();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }

    @FXML
    void updateCustomer(ActionEvent event) {
        CustomerDTO dto = new CustomerDTO(0, txtCustName.getText(), txtCustContact.getText(), txtCustEmail.getText(), txtCustId.getText());
        try {

            if (customerBO.updateCustomer(dto)) {
                new Alert(Alert.AlertType.INFORMATION, "Customer Updated!").show();
                loadAllCustomers();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void deleteCustomer(ActionEvent event) {
        String nic = txtCustId.getText();
        if (nic.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a customer or enter NIC!").show();
            return;
        }
        try {

            if (customerBO.deleteCustomer(nic)) {
                new Alert(Alert.AlertType.INFORMATION, "Customer Deleted!").show();
                loadAllCustomers();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadAllCustomers() {
        try {

            ArrayList<CustomerDTO> customers = customerBO.getAllCustomers();
            tblCustomer.setItems(FXCollections.observableArrayList(customers));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        txtCustId.clear();
        txtCustName.clear();
        txtCustContact.clear();
        txtCustEmail.clear();
    }
}