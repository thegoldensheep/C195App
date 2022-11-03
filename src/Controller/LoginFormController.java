package Controller;

import DAO.UserDAOImpl;
import Model.User;
import Utilities.Popups;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.net.URL;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {
    @FXML
    private TextField login_form_username_textfield;

    @FXML
    private PasswordField login_form_password_field;

    @FXML
    private Label login_form_username_label;

    @FXML
    private Label login_form_password_label;

    @FXML
    private Button login_form_login_button;

    @FXML
    private Label login_form_location_label;

    @FXML
    private Label login_form_title_label;

    private User verifiedUser = null;
    private ObservableList<User> allUsers = FXCollections.observableArrayList();

    ResourceBundle resource_bundle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.resource_bundle = resourceBundle;
        try {
            this.allUsers = UserDAOImpl.getAllUsers();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        String locationName = ZonedDateTime.now().getZone().toString();

        login_form_title_label.setText(resource_bundle.getString("loginTitle"));
        login_form_username_label.setText(resource_bundle.getString("loginUsernameLabel"));
        login_form_password_label.setText(resource_bundle.getString("loginPasswordLabel"));
        login_form_login_button.setText(resource_bundle.getString("loginButtonText"));
        login_form_location_label.setText(resource_bundle.getString("loginLocationLabel")+ " " + locationName);
    }

    public void loginPressed(ActionEvent event){
        validateForm();
    }

    private void validateForm() {
        String errorMessage = "";
        String typedUsername = login_form_username_textfield.getText().trim();
        String typedPassword = login_form_password_field.getText().trim();

        if(typedUsername == "") { errorMessage += resource_bundle.getString("errorUsernameBlank") + "\n"; }
        if(typedPassword == "" ) { errorMessage += resource_bundle.getString("errorPasswordBlank") + "\n"; }

        if(errorMessage=="" && !validateLogin(typedUsername, typedPassword)) {
            errorMessage += resource_bundle.getString("errorUsernamePasswordIncorrect") + "\n";
        }

        if(errorMessage == ""){
            //load next page pass login in
        } else {
            Popups.errorPopup(errorMessage);
        }

    }

    private boolean validateLogin(String username, String password){
        for(User user:allUsers){
            if(user.getUser_name()==username && user.getPassword()==password){
                verifiedUser = user;
                return true;
            }
        }
        return false;
    }


}
