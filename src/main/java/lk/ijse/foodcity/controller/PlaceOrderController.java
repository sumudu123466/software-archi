package lk.ijse.foodcity.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.foodcity.bo.custom.PlaceOrderBO;
import lk.ijse.foodcity.bo.impl.PlaceOrderBOImpl;
import lk.ijse.foodcity.db.DBConnection;
import lk.ijse.foodcity.dto.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlaceOrderController {

    private static final Logger LOGGER = Logger.getLogger(PlaceOrderController.class.getName());

    @FXML private TextField txtOrderId;
    @FXML private ComboBox<String> cmbCustomerId;
    @FXML private ComboBox<String> cmbItemCode;
    @FXML private Label lblDescription, lblUnitPrice, lblQtyOnHand, lblTotal;
    @FXML private TextField txtQty;
    @FXML private TableView<CartTM> tblCart;
    @FXML private TableColumn<CartTM, String> colCode, colDescription;
    @FXML private TableColumn<CartTM, Integer> colQty;
    @FXML private TableColumn<CartTM, Double> colUnitPrice, colTotal;

    private final PlaceOrderBO placeOrderBO = new PlaceOrderBOImpl();
    private final ObservableList<CartTM> cartList = FXCollections.observableArrayList();

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
            txtOrderId.setText(placeOrderBO.getNextOrderId());
            txtOrderId.setEditable(false);
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error setting next Order ID", e);
        }
    }

    private void loadData() {
        try {
            List<String> customerIds = placeOrderBO.getAllCustomerIds();
            List<String> itemCodes = placeOrderBO.getItemCodes();

            System.out.println("Customer IDs count: " + (customerIds != null ? customerIds.size() : "null"));
            System.out.println("Item Codes count: " + (itemCodes != null ? itemCodes.size() : "null"));

            if (customerIds != null) {
                cmbCustomerId.setItems(FXCollections.observableArrayList(customerIds));
            }
            if (itemCodes != null) {
                cmbItemCode.setItems(FXCollections.observableArrayList(itemCodes));
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error loading ComboBox data", e);
            new Alert(Alert.AlertType.ERROR, "Data Load Error: " + e.getMessage()).show();
        }
    }

    @FXML
    void cmbCustomerOnAction(ActionEvent event) {
    }

    @FXML
    void cmbItemOnAction(ActionEvent ignored) {
        String code = cmbItemCode.getValue();


        System.out.println("Selected Item Code: " + code);

        if (code == null) return;

        try {
            ItemDTO item = placeOrderBO.findItem(code);

            if (item != null) {
                lblDescription.setText(item.getName());
                lblUnitPrice.setText(String.format("%.2f", item.getPrice()));
                lblQtyOnHand.setText(String.valueOf(item.getQty()));

                System.out.println("Data loaded: " + item.getName());
            } else {
                new Alert(Alert.AlertType.WARNING, "Item not found in database!").show();

                lblDescription.setText("");
                lblUnitPrice.setText("");
                lblQtyOnHand.setText("");
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error finding item", e);
            new Alert(Alert.AlertType.ERROR, "Database Error: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent ignored) {
        if (txtQty.getText().isEmpty() || cmbItemCode.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "Please select item and enter quantity!").show();
            return;
        }

        try {
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
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid Quantity!").show();
        }
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent ignored) {
        if (txtOrderId.getText().isEmpty() || cmbCustomerId.getValue() == null || cartList.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Missing Information!").show();
            return;
        }

        ArrayList<CartDetailDto> detailDTOs = new ArrayList<>();
        for (CartTM tm : cartList) {
            detailDTOs.add(new CartDetailDto(tm.getCode(), tm.getQty(), tm.getUnitPrice(), tm.getTotal()));
        }

        PlaceOrderDto dto = new PlaceOrderDto(
                txtOrderId.getText(),
                cmbCustomerId.getValue(),
                java.time.LocalDateTime.now().toString(),
                Double.parseDouble(lblTotal.getText()),
                detailDTOs
        );

        try {
            if (placeOrderBO.placeOrder(dto)) {
                new Alert(Alert.AlertType.INFORMATION, "Order Placed Successfully!").show();
                cartList.clear();
                lblTotal.setText("0.00");
                txtQty.clear();
                setNextOrderId();
            } else {
                new Alert(Alert.AlertType.ERROR, "Order Failed!").show();
            }
        } catch (SQLException  e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
            LOGGER.log(Level.SEVERE, "Failed to place order", e);
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
            LOGGER.log(Level.SEVERE, "Failed to place order", e);
        }
    }

    private void calculateNetTotal() {
        double netTotal = 0;
        for (CartTM tm : cartList) netTotal += tm.getTotal();
        lblTotal.setText(String.format("%.2f", netTotal));
    }

    @FXML
    public void btnPrintOnAction(ActionEvent ignored) {
        try {
            InputStream reportStream = getClass().getResourceAsStream("/reports/FoodCityBill.jasper");
            if (reportStream == null) {
                new Alert(Alert.AlertType.ERROR, "Report file not found!").show();
                return;
            }
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportStream);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("order_id", txtOrderId.getText());
            Connection connection = DBConnection.getInstance().getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Report generation failed", e);
        }
    }
}