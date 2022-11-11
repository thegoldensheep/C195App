package Controller;

import DAO.UserDAOImpl;
import Model.User;
import Utilities.FileIOUtil;
import Utilities.Popups;
import Utilities.ScreenLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

/**
 * FXML Controller class for login screen
 *
 * @author Dillon Shepherd dshep80@wgu.edu
 */
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
    private ResourceBundle resource_bundle;

    /**
     * initialize the login controller class
     * @param url the url
     * @param resource_bundle the resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resource_bundle) {
        this.allUsers = UserDAOImpl.getAllUsers();
        String locationName = ZonedDateTime.now().getZone().toString();
        this.resource_bundle = resource_bundle;

        login_form_title_label.setText(resource_bundle.getString("loginTitle"));
        login_form_username_label.setText(resource_bundle.getString("loginUsernameLabel"));
        login_form_password_label.setText(resource_bundle.getString("loginPasswordLabel"));
        login_form_login_button.setText(resource_bundle.getString("loginButtonText"));
        login_form_location_label.setText(resource_bundle.getString("loginLocationLabel") + " " + locationName);
    }

    /**
     * Login pressed. Writes to file any successes and failures, and loads the main screen if successful
     * @param event the event from the node that called this method
     */
    @FXML
    private void loginPressed(ActionEvent event) {
        validateForm();
        if (verifiedUser != null) {
            FileIOUtil.writeToFile("login_activity.txt", "Successful login: user " + verifiedUser.getUserName() + " at " + ZonedDateTime.now() + "\n", false);
            User.setCurrentlyLoggedInUser(verifiedUser.getUserName());
            ScreenLoader.loadScreen(this, event, "/View/user_form.fxml");
        }
    }

    /**
     * Validates the form and shows any errors
     */
    private void validateForm() {
        String errorMessage = "";
        String typedUsername = login_form_username_textfield.getText().trim();
        String typedPassword = login_form_password_field.getText().trim();

        if (typedUsername == "") {
            errorMessage += resource_bundle.getString("errorUsernameBlank") + "\n";
        }
        if (typedPassword == "") {
            errorMessage += resource_bundle.getString("errorPasswordBlank") + "\n";
        }

        if (errorMessage == "" && !validateLogin(typedUsername, typedPassword)) {
            errorMessage += resource_bundle.getString("errorUsernamePasswordIncorrect") + "\n";
        }

        if (errorMessage != "") {
            FileIOUtil.writeToFile("login_activity.txt", "Unsuccessful login: user " + typedUsername + " at " + ZonedDateTime.now() + "\n", false);
            Popups.errorPopup(errorMessage);
        }

    }

    /**
     * Validates the username/password combination and returns true if valid, false if invalid
     * @param username the username
     * @param password the password
     * @return
     */
    private boolean validateLogin(String username, String password) {
        for (User user : allUsers) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                verifiedUser = user;
                return true;
            }
        }
        return false;
    }

    /**
     * Listens for return being pressed while password field is in focus and fires login clicked
     * @param keyEvent the key event from the node that called this method
     */
    @FXML
    private void onPasswordFieldKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            login_form_login_button.fire();
        }
    }

    /**
     * Listens for return being pressed while username field is focused and fires login clicked
     * @param keyEvent the key event from the node that called this method
     */
    @FXML
    private void onUsernameTextfieldKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            login_form_login_button.fire();
        }
    }


}
