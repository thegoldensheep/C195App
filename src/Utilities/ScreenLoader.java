package Utilities;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
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
            FXMLLoader loader = new FXMLLoader();
            ResourceBundle resource_bundle = ResourceBundle.getBundle("Language/lang");
            loader.setResources(resource_bundle);
            Parent root = loader.load(Objects.requireNonNull(callingObject.getClass().getResource(xmlPath)));
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
}
