package lk.ijse.foodcity.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.foodcity.dto.PaymentDto;
import lk.ijse.foodcity.model.PaymentModel;
import java.sql.SQLException;

public class PaymentController {
    @FXML private TextField txtOrderId, txtAmount;
    @FXML private DatePicker txtDate;
    @FXML private TableView<PaymentDto> tblPayment;
    @FXML private TableColumn<PaymentDto, Integer> colPayId, colOrderId;
    @FXML private TableColumn<PaymentDto, Double> colAmount;
    @FXML private TableColumn<PaymentDto, String> colDate;

    public void initialize() {//inh
        colPayId.setCellValueFactory(new PropertyValueFactory<>("payId"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        loadAllPayments();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        try {
            PaymentDto dto = new PaymentDto(0,
                    Integer.parseInt(txtOrderId.getText()),
                    Double.parseDouble(txtAmount.getText()),
                    txtDate.getValue().toString());

            if (PaymentModel.savePayment(dto)) {
                new Alert(Alert.AlertType.INFORMATION, "Saved!").show();
                loadAllPayments();
                clearFields();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtOrderId.clear();
        txtAmount.clear();
        txtDate.setValue(null);
    }

    private void loadAllPayments() {
        try {
            tblPayment.setItems(FXCollections.observableArrayList(PaymentModel.getAllPayments()));
        } catch (SQLException e) { e.printStackTrace(); }
    }
}