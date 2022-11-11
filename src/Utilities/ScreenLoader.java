package Utilities;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * ScreenLoaderClass
 * Contains static methods to load and show screens
 * @author Dillon Shepherd dshep80@wgu.edu
 */
public abstract class ScreenLoader {

    /**
     * Loads a screen given theobject, actionevent, and path
     * @param callingObject the object calling this class
     * @param actionEvent the originating actionevent
     * @param xmlPath the xml path
     */
    public static void loadScreen(Object callingObject, ActionEvent actionEvent, String xmlPath)  {
        try{
            Parent root = FXMLLoader.load(callingObject.getClass().getResource(xmlPath), ResourceBundle.getBundle("Language/lang"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setX(20);
            stage.setY(20);
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Loads a screen given theobject, actionevent, and path
     * @param callingObject the object calling this class
     * @param stage the originating stage
     * @param xmlPath the xml path
     */
    public static void loadScreen(Object callingObject, Stage stage, String xmlPath)  {
        try{
            Parent root = FXMLLoader.load(callingObject.getClass().getResource(xmlPath), ResourceBundle.getBundle("Language/lang"));

            Scene scene = new Scene(root);
            stage.setX(20);
            stage.setY(20);
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
