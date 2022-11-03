package Utilities;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public abstract class ScreenLoader {
    public static void loadScreen(Object callingObject, ActionEvent actionEvent, String xmlPath) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(Objects.requireNonNull(callingObject.getClass().getResource(xmlPath)));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
