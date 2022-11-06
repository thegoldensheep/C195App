package Controller;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;

public class CustomTimePicker extends HBox {
    @FXML
    private TextField hour;
    @FXML
    private TextField minute;
    @FXML
    private Button am ;
    @FXML
    private Button pm;

    private String INACTIVE_BACKGROUND_COLOR = "#ccc";
    private String ACTIVE_BACKGROUND_COLOR = "#aaf";
    private String ACTIVE_TEXT_COLOR = "#222";
    private String INACTIVE_TEXT_COLOR = "#aaa";

    private int hour_value=0;
    private int minute_value=0;
    private String am_pm_value="";

    private String hour_prompt = "hour";
    private String minute_prompt = "minute";

    public CustomTimePicker() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/custom_time_picker.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try{
            fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    @FXML
    private void onAmButtonClicked(ActionEvent event) {
        System.out.println("Clicked AM");
    }

    @FXML
    private void onPmButtonClicked(ActionEvent event) {
        System.out.println("Clicked PM");
    }
}
