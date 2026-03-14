package lk.ijse.foodcity.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.foodcity.bo.custom.ItemBO;
import lk.ijse.foodcity.bo.impl.ItemBOImpl;
import lk.ijse.foodcity.dto.ItemDTO;

public class ItemController {

    @FXML private TextField txtCode, txtName, txtPrice, txtQty;
    @FXML private TableView<ItemDTO> tblItem;
    @FXML private TableColumn<ItemDTO, String> colCode, colName;
    @FXML private TableColumn<ItemDTO, Double> colPrice;
    @FXML private TableColumn<ItemDTO, Integer> colQty;

    ItemBO itemBO = new ItemBOImpl();

    public void initialize() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        loadAllItems();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        try {
            if (itemBO.saveItem(new ItemDTO(txtCode.getText(), txtName.getText(),
                    Double.parseDouble(txtPrice.getText()), Integer.parseInt(txtQty.getText())))) {
                new Alert(Alert.AlertType.INFORMATION, "Saved!").show();
                loadAllItems();
                clearFields();
            }
        } catch (Exception e) { new Alert(Alert.AlertType.ERROR, e.getMessage()).show(); }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            if (itemBO.updateItem(new ItemDTO(txtCode.getText(), txtName.getText(),
                    Double.parseDouble(txtPrice.getText()), Integer.parseInt(txtQty.getText())))) {
                new Alert(Alert.AlertType.INFORMATION, "Updated!").show();
                loadAllItems();
            }
        } catch (Exception e) { new Alert(Alert.AlertType.ERROR, e.getMessage()).show(); }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String code = txtCode.getText();

        if (code.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select an item to delete!").show();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this item?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            try {
                if (itemBO.deleteItem(code)) {
                    new Alert(Alert.AlertType.INFORMATION, "Item Deleted Successfully!").show();
                    loadAllItems();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete the item!").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Database Error: " + e.getMessage()).show();
            }
        }
    }

    private void loadAllItems() {
        try {
            tblItem.setItems(FXCollections.observableArrayList(itemBO.getAllItems()));
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void clearFields() {
        txtCode.clear(); txtName.clear(); txtPrice.clear(); txtQty.clear();
    }

    @FXML
    void tblItemOnMouseClicked(MouseEvent event) {
        ItemDTO item = tblItem.getSelectionModel().getSelectedItem();
        if (item != null) {
            txtCode.setText(item.getCode()); txtName.setText(item.getName());
            txtPrice.setText(String.valueOf(item.getPrice())); txtQty.setText(String.valueOf(item.getQty()));
        }
    }
}