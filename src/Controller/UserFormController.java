package Controller;

import DAO.CustomerDAOImpl;
import Model.Customer;
import Utilities.Popups;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class UserFormController implements Initializable {
    @FXML
    private Label customer_id_input_label;
    @FXML
    private TextField customer_id_textfield;
    @FXML
    private TextField customer_name_textfield;
    @FXML
    private TextField customer_address_textfield;
    @FXML
    private TextField customer_postal_textfield;
    @FXML
    private TextField customer_phone_textfield;
    @FXML
    private Label customer_name_input_label;
    @FXML
    private Label customer_address_input_label;
    @FXML
    private Label customer_postal_input_label;
    @FXML
    private Label customer_phone_input_label;
    @FXML
    private Button customer_save_button_input;
    @FXML
    private Button customer_cancel_button_input;
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
    Customer customerOpenForModification = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadAllCustomersFromDatabase();
        updateCustomerTableView();
        setupCustomerTableviewListener();


    }

    private void setupCustomerTableviewListener() {
        customer_tableview.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
            if(customer_tableview.getSelectionModel().getSelectedItem() != null){
                modify_customer_button.setDisable(false);
                delete_customer_button.setDisable(false);
            }else{
                modify_customer_button.setDisable(true);
                delete_customer_button.setDisable(true);
            }
        });
    }

    private void loadAllCustomersFromDatabase() {
        try {
            allCustomers = CustomerDAOImpl.getAllCustomers();
        } catch (Exception exception) {
            System.out.println("Error when trying to get all customers from database");
            exception.printStackTrace();
        }


    }

    private void updateCustomerTableView() {
        customer_tableview.setItems(allCustomers);
        customer_id_table_column.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customer_name_table_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        customer_address_table_column.setCellValueFactory(new PropertyValueFactory<>("address"));
        customer_postal_code_table_column.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        customer_phone_table_column.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }

    private void setupNewCustomer(){
        setCustomerFieldVisibility(true);
        customerOpenForModification = null;
        clearCustomerInputForm();
        customer_id_textfield.setText("auto-generated");
        customer_save_button_input.setText("Save New");
    }

    private void setCustomerFieldVisibility(boolean isVisible) {
        customer_id_input_label.setDisable(!isVisible);
        customer_name_textfield.setDisable(!isVisible);
        customer_name_input_label.setDisable(!isVisible);
        customer_address_input_label.setDisable(!isVisible);
        customer_address_textfield.setDisable(!isVisible);
        customer_postal_input_label.setDisable(!isVisible);
        customer_postal_textfield.setDisable(!isVisible);
        customer_phone_textfield.setDisable(!isVisible);
        customer_phone_input_label.setDisable(!isVisible);
        customer_cancel_button_input.setDisable(!isVisible);
        customer_save_button_input.setDisable(!isVisible);
    }

    private void clearCustomerInputForm(){
        customer_name_textfield.clear();
        customer_id_textfield.clear();
        customer_address_textfield.clear();
        customer_postal_textfield.clear();
        customer_phone_textfield.clear();
    }

    private void setupModifyCustomer(Customer customer){
        setCustomerFieldVisibility(true);
        customerOpenForModification = customer;
        customer_id_textfield.setText(""+customer.getCustomerId());
        customer_name_textfield.setText(customer.getName());
        customer_address_textfield.setText(customer.getAddress());
        customer_postal_textfield.setText(customer.getPostalCode());
        customer_phone_textfield.setText(customer.getPhone());
        customer_save_button_input.setText("Save Changes");
    }

    public void customerSaveButtonClicked(ActionEvent actionEvent) {
        
    }

    public void customerCancelButtonClicked(ActionEvent actionEvent) {
        clearCustomerInputForm();
        setCustomerFieldVisibility(false);
    }

    public void modifyCustomerButtonClicked(ActionEvent actionEvent) {
        setupModifyCustomer((Customer)customer_tableview.getSelectionModel().getSelectedItem());
    }

    public void addCustomerButtonClicked(ActionEvent actionEvent) {
        setupNewCustomer();
    }

    public void deleteCustomerButtonClicked(ActionEvent actionEvent) {
        Customer selectedCustomer = (Customer)customer_tableview.getSelectionModel().getSelectedItem();
        if(Popups.confirmAction("Are you sure you want to delete customer "+selectedCustomer.getName()+"?")){
            //possibly add feature to show what appointments will be deleted
            CustomerDAOImpl.deleteCustomer(selectedCustomer);
            clearCustomerInputForm();
            customerOpenForModification = null;
            loadAllCustomersFromDatabase();

        }
    }
}
