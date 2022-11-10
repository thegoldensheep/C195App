package Application;
import DAO.UserDAOImpl;
import Utilities.JBDC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;


public class Main extends Application {
    static Stage stage;

    public static void main(String[] args) {
        JBDC.openConnection(); //open database connection

        launch(args);

        JBDC.closeConnection(); //close database connection
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Locale.setDefault(new Locale("fr", "FR")); //comment this out to get english

        this.stage = primaryStage;
        ResourceBundle resource_bundle = ResourceBundle.getBundle("Language/lang");

        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/login_form.fxml"));
            loader.setResources(resource_bundle);
            root = loader.load();
            stage.setTitle("Scheduling");
            stage.setScene(new Scene(root));
            stage.show();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}