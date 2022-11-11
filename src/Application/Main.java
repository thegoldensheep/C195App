package Application;
import DAO.UserDAOImpl;
import Utilities.JBDC;
import Utilities.ScreenLoader;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Main class to launch the application.
 * @author Dillon Shepherd dshep80@wgu.edu
 */
public class Main extends Application {
    static Stage stage;

    /**
     * Starts the application.
     * @param args the arguments send when the application is launched
     */
    public static void main(String[] args) {
        JBDC.openConnection(); //open database connection

        launch(args);

        JBDC.closeConnection(); //close database connection
    }

    /**
     * The start method for the application which sets the language resource and loads login screen
     * @param primaryStage the stage to load the login screen into
     * @throws Exception if path cannot be found
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        //Locale.setDefault(new Locale("fr", "FR")); //comment this out to get english

        this.stage = primaryStage;
        ScreenLoader.loadScreen(this, primaryStage, "/View/login_form.fxml");
    }


}