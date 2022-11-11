package Utilities;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * Helper class Popups
 * Contains static methods to create and show popups of different types that can be called upon
 */
public abstract class Popups {
    private static int WIDTH = 600;

    /**
     * Creates an error popup given a string
     * @param message the message to display
     */
    public static void errorPopup(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setHeaderText("");
        alert.setTitle("Error");
        alert.showAndWait();
    }

    /**
     * Creates a confirmation popup given a string
     * @param ctrl: a node to be drawn on the screen
     * @return a boolean of whether the user clicked yes or not
     */
    public static boolean confirmAction(Node ctrl){
        Dialog dialog = new Dialog();
        dialog.setResizable(true);
        dialog.setHeaderText("");
        dialog.setTitle("Confirm");

        ctrl.setStyle("-fx-font-size:14px;-fx-font-weight:bold");

        dialog.getDialogPane().setContent(ctrl);
        dialog.getDialogPane().getButtonTypes().clear();
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);



        dialog.showAndWait();
        Boolean returnValue =  dialog.getResult() == ButtonType.YES ? true : false;
        dialog.close();
        dialog = null;
        return returnValue;

    }

    /**
     * Creates a popup with a given node
     * @param ctrl: a node to be drawn on the screen
     */
    public static void showInformation(Node ctrl){
        Dialog dialog = new Dialog();
        dialog.setResizable(true);
        dialog.setHeaderText("");
        dialog.setTitle("");

        ctrl.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        dialog.getDialogPane().setContent(ctrl);
        dialog.getDialogPane().getButtonTypes().clear();
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

        dialog.showAndWait();
        dialog.close();
        dialog = null;
    }
}
