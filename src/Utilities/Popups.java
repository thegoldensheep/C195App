package Utilities;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public abstract class Popups {

    public static void errorPopup(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.showAndWait();
    }
}
