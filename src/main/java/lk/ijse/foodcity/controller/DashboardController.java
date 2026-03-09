package lk.ijse.foodcity.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;

public class DashboardController {

    @FXML
    private AnchorPane pagingPane;


    private void setPaging(String fxml) {
        try {
            pagingPane.getChildren().clear();
            URL resource = getClass().getResource("/view/" + fxml + ".fxml");

            if (resource == null) {
                new Alert(Alert.AlertType.ERROR, "Cannot find FXML: /view/" + fxml + ".fxml").show();
                return;
            }

            Parent load = FXMLLoader.load(resource);
            pagingPane.getChildren().add(load);


            AnchorPane.setTopAnchor(load, 0.0);
            AnchorPane.setBottomAnchor(load, 0.0);
            AnchorPane.setLeftAnchor(load, 0.0);
            AnchorPane.setRightAnchor(load, 0.0);

        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error loading UI: " + e.getMessage()).show();
        }
    }

    @FXML void btnPlaceOrderOnAction(ActionEvent event) { setPaging("PlaceOrder"); }
    @FXML void btnInventoryOnAction(ActionEvent event) { setPaging("Item"); }
    @FXML void btnCustomerOnAction(ActionEvent event) { setPaging("Customer"); }
    @FXML void btnOrderDetailsOnAction(ActionEvent event) { setPaging("OrderDetails"); }
    @FXML void btnPaymentOnAction(ActionEvent event) { setPaging("payment"); }

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {
        lk.ijse.foodcity.App.setRoot("Login");
    }
}