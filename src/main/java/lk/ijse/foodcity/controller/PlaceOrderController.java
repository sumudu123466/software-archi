package lk.ijse.foodcity.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.foodcity.App;
import lk.ijse.foodcity.db.DBConnection;
import lk.ijse.foodcity.dto.CartTM;
import lk.ijse.foodcity.dto.ItemDTO;
import lk.ijse.foodcity.model.CustomerModel;
import lk.ijse.foodcity.model.ItemModel;
import lk.ijse.foodcity.model.OrderModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlaceOrderController {

    @FXML
    private TextField txtOrderId;
    @FXML
    private ComboBox<String> cmbCustomerId;
    @FXML
    private ComboBox<String> cmbItemCode;
    @FXML
    private Label lblDescription, lblUnitPrice, lblQtyOnHand, lblTotal;
    @FXML
    private TextField txtQty;
    @FXML
    private TableView<CartTM> tblCart;
    @FXML
    private TableColumn<CartTM, String> colCode, colDescription;
    @FXML
    private TableColumn<CartTM, Integer> colQty;
    @FXML
    private TableColumn<CartTM, Double> colUnitPrice, colTotal;

    private ObservableList<CartTM> cartList = FXCollections.observableArrayList();

    public void initialize() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        tblCart.setItems(cartList);
        loadData();
        setNextOrderId();
    }

    private void setNextOrderId() {
        try {
            String nextId = OrderModel.getNextOrderId();
            txtOrderId.setText(nextId);
            txtOrderId.setEditable(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        try {
            cmbCustomerId.setItems(FXCollections.observableArrayList(CustomerModel.getAllCustomerIds()));
            cmbItemCode.setItems(FXCollections.observableArrayList(ItemModel.getItemCodesForCombo()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cmbItemOnAction(ActionEvent event) {
        String code = cmbItemCode.getValue();
        try {
            ItemDTO item = ItemModel.findItem(code);
            if (item != null) {
                lblDescription.setText(item.getName());
                lblUnitPrice.setText(String.valueOf(item.getPrice()));
                lblQtyOnHand.setText(String.valueOf(item.getQty()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        if (txtQty.getText().isEmpty() || cmbItemCode.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "Please select item and enter quantity!").show();
            return;
        }
        String code = cmbItemCode.getValue();
        String description = lblDescription.getText();
        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());
        double total = unitPrice * qty;

        for (CartTM tm : cartList) {
            if (tm.getCode().equals(code)) {
                tm.setQty(tm.getQty() + qty);
                tm.setTotal(tm.getTotal() + total);
                tblCart.refresh();
                calculateNetTotal();
                return;
            }
        }
        cartList.add(new CartTM(code, description, qty, unitPrice, total));
        calculateNetTotal();
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        if (txtOrderId.getText().isEmpty() || cmbCustomerId.getValue() == null || cartList.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Missing Information!").show();
            return;
        }

        int orderId = Integer.parseInt(txtOrderId.getText());
        String customerId = cmbCustomerId.getValue();
        double netTotal = Double.parseDouble(lblTotal.getText());
        ArrayList<CartTM> items = new ArrayList<>(cartList);

        try {
            boolean isSuccess = OrderModel.placeOrder(orderId, customerId, netTotal, items);
            if (isSuccess) {
                new Alert(Alert.AlertType.INFORMATION, "Order Placed Successfully!").show();
                cartList.clear();
                lblTotal.setText("0.00");
                setNextOrderId();
            } else {
                new Alert(Alert.AlertType.ERROR, "Order Failed!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }

    private void calculateNetTotal() {
        double netTotal = 0;
        for (CartTM tm : cartList) netTotal += tm.getTotal();
        lblTotal.setText(String.valueOf(netTotal));
    }

    public void btnPrintOnAction(ActionEvent actionEvent) {
//        try {
//            JasperReport jasperReport = JasperCompileManager.compileReport(
//                    getClass().getResourceAsStream("/reports/Supplier.jrxml")
//            );
//
//            Connection connection = DBConnection.getInstance().getConnection();
//
//            Map<String, Object> parameters = new HashMap<>();
//            parameters.put("p_date", LocalDate.now().toString());
//
//            JasperPrint jasperPrint = JasperFillManager.fillReport(
//                    jasperReport,
//                    parameters, // null
//                    connection
//            );
//
//            JasperViewer.viewReport(jasperPrint, false);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        JasperReport jasperReport = null;


        InputStream reportStream = getClass().getResourceAsStream("/reports/FoodCityBill.jasper");

        if (reportStream == null) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
            return;
        }else {
            System.out.println("report load successfully");
        }


        try {

            jasperReport = (JasperReport) JRLoader.loadObject(reportStream);

            if (jasperReport == null) {
                new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
                return;
            }

            Map<String, Object> parameters = new HashMap<>();


                parameters.put("order_id", txtOrderId.getText());

            Connection connection = DBConnection.getInstance().getConnection();

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

            JasperViewer.viewReport(jasperPrint, false); // false = Don't close app on exit

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void cmbCustomerOnAction(ActionEvent actionEvent) {
    }
}