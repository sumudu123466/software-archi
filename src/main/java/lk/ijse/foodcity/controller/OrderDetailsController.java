package lk.ijse.foodcity.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.foodcity.dto.OrderDto;
import lk.ijse.foodcity.dto.OrderDetailDto;
import lk.ijse.foodcity.model.OrderModel;

import java.util.ArrayList;

public class OrderDetailsController {

    @FXML private TableView<OrderDto> tblOrders;
    @FXML private TableColumn<OrderDto, String> colOrderId;
    @FXML private TableColumn<OrderDto, String> colCustId;
    @FXML private TableColumn<OrderDto, String> colDate;
    @FXML private TableColumn<OrderDto, Double> colTotal;

    @FXML private TableView<OrderDetailDto> tblOrderDetails;
    @FXML private TableColumn<OrderDetailDto, String> colItemCode;
    @FXML private TableColumn<OrderDetailDto, Integer> colQty;
    @FXML private TableColumn<OrderDetailDto, Double> colUnitPrice, colSubTotal;

    @FXML private TextField txtSearchOrder;

    public void initialize() {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colCustId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colSubTotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));

        loadAllOrders();

        tblOrders.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                loadOrderDetails(newValue.getOrderId());
            }
        });
    }

    private void loadAllOrders() {
        ArrayList<OrderDto> list = OrderModel.getAllOrders();
        tblOrders.setItems(FXCollections.observableArrayList(list));
    }

    private void loadOrderDetails(String orderId) {
        ArrayList<OrderDetailDto> details = OrderModel.getOrderDetails(orderId);
        tblOrderDetails.setItems(FXCollections.observableArrayList(details));
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String searchId = txtSearchOrder.getText().trim();
        if (searchId.isEmpty()) {
            loadAllOrders();
            return;
        }

        ObservableList<OrderDto> currentList = tblOrders.getItems();
        for (OrderDto dto : currentList) {
            if (dto.getOrderId().equalsIgnoreCase(searchId)) {
                tblOrders.getSelectionModel().select(dto);
                tblOrders.scrollTo(dto);
                return;
            }
        }
        new Alert(Alert.AlertType.WARNING, "Order ID Not Found!").show();
    }
}