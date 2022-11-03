package Utilities;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public abstract class Popups {

    public static void errorPopup(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setHeaderText("");
        alert.setTitle("");
        alert.showAndWait();
    }

    public static boolean confirmAction(String message){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        return alert.getResult() == ButtonType.YES ? true : false;
    }
}
