package lk.ijse.foodcity.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.foodcity.bo.custom.OrderBO;
import lk.ijse.foodcity.bo.impl.OrderBOImpl;
import lk.ijse.foodcity.dto.OrderDto;
import lk.ijse.foodcity.dto.OrderDetailDto;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDetailsController {

    private static final Logger LOGGER = Logger.getLogger(OrderDetailsController.class.getName());
    private final OrderBO orderBO = new OrderBOImpl();

    @FXML private TableView<OrderDto> tblOrders;
    @FXML private TableColumn<OrderDto, String> colOrderId, colCustId, colDate;
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
        try {
            List<OrderDto> list = orderBO.getAllOrders();
            tblOrders.setItems(FXCollections.observableArrayList(list));
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error loading orders", e);
            new Alert(Alert.AlertType.ERROR, "Failed to load orders!").show();
        }
    }

    private void loadOrderDetails(String orderId) {
        try {
            List<OrderDetailDto> details = orderBO.getOrderDetails(orderId);
            tblOrderDetails.setItems(FXCollections.observableArrayList(details));
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error loading order details", e);
            new Alert(Alert.AlertType.ERROR, "Failed to load details!").show();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent ignored) {
        String searchId = txtSearchOrder.getText().trim();
        if (searchId.isEmpty()) {
            loadAllOrders();
            return;
        }

        ObservableList<OrderDto> items = tblOrders.getItems();
        for (OrderDto dto : items) {
            if (dto.getOrderId().equalsIgnoreCase(searchId)) {
                tblOrders.getSelectionModel().select(dto);
                tblOrders.scrollTo(dto);
                return;
            }
        }
        new Alert(Alert.AlertType.WARNING, "Order ID Not Found!").show();
    }
}