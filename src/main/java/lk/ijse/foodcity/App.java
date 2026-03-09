package lk.ijse.foodcity;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;

public class App extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        URL resource = getClass().getResource("/view/welcome.fxml");

        if (resource == null) {
            throw new RuntimeException("FXML file not found! Check src/main/resources/view/welcome.fxml");
        }

        Parent root = FXMLLoader.load(resource);


        scene = new Scene(root, 1366, 768);

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);


        stage.centerOnScreen();


        stage.setResizable(false);

        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {

        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/view/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}