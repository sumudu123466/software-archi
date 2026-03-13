package lk.ijse.foodcity.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import lk.ijse.foodcity.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeController implements Initializable {

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label lblStatus;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        new Thread(() -> {
            try {
                for (double i = 0; i <= 10; i += 1) {
                    double progress = i;
                    Platform.runLater(() -> {
                        progressBar.setProgress(progress);
                        if (progress >= 0.9) {
                            lblStatus.setText("Starting Application...");
                        } else {
                            lblStatus.setText("Loading Modules... " + (int)(progress * 100) + "%");
                        }
                    });
                    Thread.sleep(100);
                }


                Platform.runLater(() -> {
                    try {
                        App.setRoot("Login");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}