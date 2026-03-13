package lk.ijse.foodcity.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class LayoutController implements Initializable {

    @FXML
    private AnchorPane mainContent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            setChild("/view/ItemUI.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void clickCustomerNav() throws IOException {
        setChild("/view/CustomerUI.fxml");
    }

    @FXML
    private void clickItemNav() throws IOException {
        setChild("/view/ItemUI.fxml");
    }

    @FXML
    private void clickOrderNav() throws IOException {
        setChild("/view/PlaceOrderUI.fxml");
    }


    private void setChild(String fxmlPath) throws IOException {
        Parent node = FXMLLoader.load(getClass().getResource(fxmlPath));
        mainContent.getChildren().setAll(node);


        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setBottomAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
    }
}