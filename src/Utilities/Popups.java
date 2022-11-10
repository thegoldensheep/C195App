package Utilities;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public abstract class Popups {
    private static int WIDTH = 600;
    private static Dialog dialog = new Dialog();

    public static void errorPopup(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setHeaderText("");
        alert.setTitle("");
        alert.showAndWait();
    }

    public static boolean confirmAction(String message){
        dialog.setResizable(true);
        dialog.setHeaderText("");
        dialog.setTitle("");
        dialog.setWidth(WIDTH);

        dialog.setContentText(message);
        dialog.getDialogPane().getButtonTypes().clear();
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
        dialog.showAndWait();
        return  dialog.getResult() == ButtonType.YES ? true : false;

    }
    public static void showInformation(String message){
        dialog.setResizable(true);
        dialog.setHeaderText("");
        dialog.setTitle("");
        dialog.setWidth(WIDTH);

        dialog.setContentText(message);
        dialog.getDialogPane().getButtonTypes().clear();
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.showAndWait();

    }
}
