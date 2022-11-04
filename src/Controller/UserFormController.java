package Controller;

import DAO.AppointmentDAOImpl;
import DAO.ContactDAOImpl;
import DAO.CustomerDAOImpl;
import DAO.DivisionDAOImpl;
import Model.Appointment;
import Model.Contact;
import Model.Customer;
import Model.Division;
import Utilities.Popups;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class UserFormController implements Initializable {
    @FXML
    private Label add_modify_customer_title_label;
    @FXML
    private Label customer_title_label;
    @FXML
    private TableColumn customer_state_table_column;
    @FXML
    private TableColumn customer_country_table_column;
    @FXML
    private ComboBox customer_input_country_combobox;
    @FXML
    private ComboBox customer_input_state_combobox;
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
    ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    ObservableList<Division> allDivisions = FXCollections.observableArrayList();
    ObservableList<Contact> allContacts = FXCollections.observableArrayList();
    Customer customerOpenForModification = null;

    private String AUTO_GENERATED_TEXT = "auto-generated";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadAllCustomersFromDatabase();
        loadAllAppointmentsFromDatabase();
        loadAllDivisionsFromDatabase();
        loadAllContactsFromDatabase();

        updateCustomerTableView();
        resetCountryStateValues();
        setupCustomerTableviewListener();
        setupCountryComboboxListener();

        System.out.println(allContacts.stream().map(Contact::getContactName).collect(Collectors.toList()));

    }

    private void loadAllContactsFromDatabase() {
        try {
            allContacts = ContactDAOImpl.getAllContacts();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void resetCountryStateValues(){
        ObservableList<String> allCountries = FXCollections.observableArrayList();
        allCountries.add("Country...");
        allCountries.addAll((allDivisions.stream().map(Division::getCountryName)).distinct().toList());
        ObservableList<String> allStates = FXCollections.observableArrayList();
        allStates.add("State/Providence...");
        customer_input_country_combobox.setItems(allCountries);
        customer_input_state_combobox.setItems(allStates);
        customer_input_state_combobox.getSelectionModel().selectFirst();
        customer_input_country_combobox.getSelectionModel().selectFirst();

    }

    private void loadAllDivisionsFromDatabase() {
        try {
            allDivisions = DivisionDAOImpl.getAllDivisions();
        }catch(Exception exception){
            exception.printStackTrace();
            System.out.println("Error getting all divisions");
        }
        for(Division d : allDivisions){
            System.out.println(""+d.getDivisionName()+", "+d.getCountryName()+"\n");
        }
    }

    private void loadAllAppointmentsFromDatabase() {
        try {
            allAppointments = AppointmentDAOImpl.getAllAppointments();
        } catch (Exception exception) {
            System.out.println("No appointments found");
            exception.printStackTrace();
        }

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

    private void setupCountryComboboxListener() {
        customer_input_country_combobox.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
            ObservableList<String> allProvidences = FXCollections.observableArrayList();
            allProvidences.add("State/Providence...");
            for(Division d : allDivisions ){
                if(d.getCountryName().equals(customer_input_country_combobox.getSelectionModel().getSelectedItem())){
                    allProvidences.add(d.getDivisionName());
                }
            }
            customer_input_state_combobox.setItems(allProvidences);
            customer_input_state_combobox.getSelectionModel().selectFirst();
            if(customer_input_state_combobox.getItems().size() > 1 ) {
                customer_input_state_combobox.setDisable(false);
            } else {
                customer_input_state_combobox.setDisable(true);
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
        customer_state_table_column.setCellValueFactory(new PropertyValueFactory<>("division"));
        customer_country_table_column.setCellValueFactory(new PropertyValueFactory<>("country"));
        customer_postal_code_table_column.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        customer_phone_table_column.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }

    private void setupNewCustomer(){
        setCustomerFieldVisibility(true);
        clearCustomerInputForm();
        resetCountryStateValues();
        add_modify_customer_title_label.setText("Add Customer");
        customerOpenForModification = null;
        customer_id_textfield.setText(AUTO_GENERATED_TEXT);
        customer_save_button_input.setText("Save New");
    }

    private void setCustomerFieldVisibility(boolean isVisible) {
        add_modify_customer_title_label.setDisable(!isVisible);
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
        customer_input_country_combobox.setDisable(!isVisible);
        customer_input_state_combobox.setDisable(!isVisible);


        if(!isVisible){
            add_modify_customer_title_label.setText("Add/Modify Customer");
        }
    }

    private void clearCustomerInputForm(){
        customer_name_textfield.clear();
        customer_id_textfield.clear();
        customer_address_textfield.clear();
        customer_postal_textfield.clear();
        customer_phone_textfield.clear();
        resetCountryStateValues();
    }

    private void setupModifyCustomer(Customer customer){
        setCustomerFieldVisibility(true);
        resetCountryStateValues();
        customerOpenForModification = customer;
        add_modify_customer_title_label.setText("Modify Customer");
        customer_id_textfield.setText(""+customer.getCustomerId());
        customer_name_textfield.setText(customer.getName());
        customer_address_textfield.setText(customer.getAddress());
        customer_input_country_combobox.getSelectionModel().select(customer.getCountry());
        customer_input_state_combobox.getSelectionModel().select(customer.getDivision());
        customer_postal_textfield.setText(customer.getPostalCode());
        customer_phone_textfield.setText(customer.getPhone());
        customer_save_button_input.setText("Save Changes");
    }

    public void customerSaveButtonClicked(ActionEvent actionEvent) {
        if(customerAddFormValid()){
            Customer customer = null;
            if(customer_id_textfield.getText().equals(AUTO_GENERATED_TEXT)){
                //New customer
                customer = new Customer(-1, customer_name_textfield.getText(),
                        customer_address_textfield.getText(), customer_postal_textfield.getText(),
                        customer_phone_textfield.getText(), customer_input_state_combobox.getSelectionModel().getSelectedItem().toString(), customer_input_country_combobox.getSelectionModel().getSelectedItem().toString());
                try {
                    CustomerDAOImpl.addNewCustomer(customer);
                    clearCustomerInputForm();
                    setCustomerFieldVisibility(false);
                    loadAllCustomersFromDatabase();
                    updateCustomerTableView();
                } catch (SQLException throwables) {
                    System.out.println("customer could not be added");
                    throwables.printStackTrace();
                }
            }else{
                //Modify Customer
                customer = new Customer(Integer.parseInt(customer_id_textfield.getText()), customer_name_textfield.getText(),
                        customer_address_textfield.getText(), customer_postal_textfield.getText(),
                        customer_phone_textfield.getText(), customer_input_state_combobox.getSelectionModel().getSelectedItem().toString(), customer_input_country_combobox.getSelectionModel().getSelectedItem().toString() );
                CustomerDAOImpl.modifyCustomer(customer);
                clearCustomerInputForm();
                setCustomerFieldVisibility(false);
                loadAllCustomersFromDatabase();
                updateCustomerTableView();
            }
        }
    }

    private boolean customerAddFormValid() {
        String errorMessage = "";
        if(customer_name_textfield.getText().trim()==""){ errorMessage += "Name must be filled out\n"; }
        if(customer_address_textfield.getText().trim()==""){ errorMessage += "Address must be filled out\n"; }
        if(customer_phone_textfield.getText().trim()==""){ errorMessage += "Phone must be filled out\n"; }
        if(customer_postal_textfield.getText().trim()==""){ errorMessage += "Postal must be filled out\n"; }
        String selectedCountry = (String)customer_input_country_combobox.getSelectionModel().getSelectedItem();
        String selectedState = (String)customer_input_state_combobox.getSelectionModel().getSelectedItem();
        if(selectedCountry == "Country..." || selectedCountry == "") { errorMessage += "Country must be selected\n"; }
        if(selectedState == "State/Providence..." || selectedState == "") { errorMessage += "State/Providence must be selected\n"; }

        if(errorMessage.equals("")){
            return true;
        } else {
            Popups.errorPopup(errorMessage);
            return false;
        }
    }

    public void customerCancelButtonClicked(ActionEvent actionEvent) {
        clearCustomerInputForm();
        resetCountryStateValues();
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
            setCustomerFieldVisibility(false);
            updateCustomerTableView();

        }
    }
}
