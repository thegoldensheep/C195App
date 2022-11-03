package Controller;

import DAO.CustomerDAOImpl;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class UserFormController implements Initializable {
    @FXML
    private TextArea test_text_area;
    @FXML
    private TableView customer_tableview;
    @FXML
    private TableColumn customer_id_table_column;
    @FXML
    private TableColumn customer_name_table_column;
    @FXML
    private TableColumn customer_address_table_column;
    @FXML
    private TableColumn customer_postal_code_table_column;
    @FXML
    private TableColumn customer_phone_table_column;
    @FXML
    private Button add_new_customer_button;
    @FXML
    private Button modify_customer_button;
    @FXML
    private Button delete_customer_button;

    ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadAllCustomersFromDatabase();
    }

    private void loadAllCustomersFromDatabase() {
        try {
            allCustomers = CustomerDAOImpl.getAllCustomers();
        } catch (Exception exception) {
            System.out.println("Error when trying to get all customers from database");
            exception.printStackTrace();
        }
    }
}
