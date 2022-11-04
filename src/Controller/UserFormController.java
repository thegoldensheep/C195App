package Controller;

import DAO.*;
import Model.*;
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
import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UserFormController implements Initializable {

    @FXML
    private RadioButton appointment_current_month_radio;
    @FXML
    private TextField appointment_type_input_textfield;
    @FXML
    private Label appointment_type_input_label;
    @FXML
    private TableView appointments_tableview;
    @FXML
    private TableColumn appointment_id_column;
    @FXML
    private TableColumn appointment_title_column;
    @FXML
    private TableColumn appointment_description_column;
    @FXML
    private TableColumn appointment_location_column;
    @FXML
    private TableColumn appointment_contact_column;
    @FXML
    private TableColumn appointment_type_column;
    @FXML
    private TableColumn appointment_start_column;
    @FXML
    private TableColumn appointment_end_column;
    @FXML
    private TableColumn appointment_customer_id_column;
    @FXML
    private TableColumn appointment_user_id_column;
    @FXML
    private Button add_new_appointment_button;
    @FXML
    private Button modify_appointment_button;
    @FXML
    private Button delete_appointment_button;
    @FXML
    private Label add_modify_appointment_title;
    @FXML
    private Label appointment_id_input_label;
    @FXML
    private TextField appointment_id_input_textfield;
    @FXML
    private Label appointment_title_input_label;
    @FXML
    private TextField appointment_title_input_textfield;
    @FXML
    private Label appointment_description_input_label;
    @FXML
    private TextField appointment_description_input_textfield;
    @FXML
    private TextField appointment_location_input_textfield;
    @FXML
    private Label appointment_location_input_label;
    @FXML
    private ComboBox appointment_contact_input_combobox;
    @FXML
    private DatePicker appointment_end_input_datepicker;
    @FXML
    private Label appointment_end_input_label;
    @FXML
    private ComboBox appointment_end_minute_input_combobox;
    @FXML
    private ComboBox appointment_end_hour_input_combobox;
    @FXML
    private ComboBox appointment_customer_id_input_combobox;
    @FXML
    private ComboBox appointment_user_id_input_combobox;
    @FXML
    private Button appointment_save_input_button;
    @FXML
    private Button appointment_cancel_input_button;
    @FXML
    private DatePicker appointment_start_input_datepicker;
    @FXML
    private Label appointment_start_input_label;
    @FXML
    private ComboBox appointment_start_minute_input_combobox;
    @FXML
    private ComboBox appointment_start_hour_input_combobox;
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
    ObservableList<User> allUsers = FXCollections.observableArrayList();

    Customer customerOpenForModification = null;

    private String AUTO_GENERATED_TEXT = "auto-generated";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadAllCustomersFromDatabase();
        loadAllAppointmentsFromDatabase();
        loadAllDivisionsFromDatabase();
        loadAllContactsFromDatabase();
        loadAllUsersFromDatabase();

        updateCustomerTableView();
        updateAppointmentTableView();
        resetCountryStateValues();
        setupCustomerTableviewListener();
        setupAppointmentTableviewListener();
        setupCountryComboboxListener();
        loadAppointmentInputDefaults();

        //create 100 random appointments and add them to the database
        /*
        for(int i = 0 ; i < 100 ; i++) {
            addRandomAppointment();
        }
        */




        System.out.println(allContacts.stream().map(Contact::getContactName).collect(Collectors.toList()));

    }

    private void updateAppointmentTableView() {
        appointment_id_column.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appointment_title_column.setCellValueFactory(new PropertyValueFactory<>("title"));
        appointment_description_column.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointment_location_column.setCellValueFactory(new PropertyValueFactory<>("location"));
        appointment_contact_column.setCellValueFactory(new PropertyValueFactory<>("contact"));
        appointment_type_column.setCellValueFactory(new PropertyValueFactory<>("type"));
        appointment_start_column.setCellValueFactory(new PropertyValueFactory<>("start"));
        appointment_end_column.setCellValueFactory(new PropertyValueFactory<>("end"));
        appointment_customer_id_column.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        appointment_user_id_column.setCellValueFactory(new PropertyValueFactory<>("userId"));

        appointments_tableview.setItems(allAppointments);
    }

    private void setupAppointmentTableviewListener() {
        appointments_tableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                modify_appointment_button.setDisable(false);
                delete_appointment_button.setDisable(false);
            } else {
                modify_appointment_button.setDisable(true);
                delete_appointment_button.setDisable(true);
            }
        });
    }


    private void loadAllContactsFromDatabase() {
        try {
            allContacts = ContactDAOImpl.getAllContacts();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAllUsersFromDatabase() {
        try {
            allUsers = UserDAOImpl.getAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void resetCountryStateValues() {
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

    private void loadAppointmentInputDefaults() {
        ObservableList<String> allHours = FXCollections.observableArrayList();
        allHours.add("Hour...");
        ObservableList<String> allMinutes = FXCollections.observableArrayList();
        allMinutes.add("Minute...");
        ObservableList<String> allContacts = FXCollections.observableArrayList();
        allContacts.add("Contact...");
        ObservableList<String> allUsers = FXCollections.observableArrayList();
        allUsers.add("User ID...");
        ObservableList<String> allCustomers = FXCollections.observableArrayList();
        allCustomers.add("Customer ID...");

        ZoneId newYorkZoneId = ZoneId.of("America/New_York");
        ZoneId localZoneId = ZoneId.systemDefault();
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime nowInNewYork = now.withZoneSameInstant(newYorkZoneId);
        long offset = (nowInNewYork.getOffset().getTotalSeconds() - now.getOffset().getTotalSeconds())/3600;
        //add to allHours a list of numbers 8-22 minus the offset
        for (int i = 8; i < 23; i++) {
            allHours.add(String.valueOf(i - offset));
        }

        allMinutes.add("00");
        allMinutes.addAll(IntStream.rangeClosed(1, 59).mapToObj(String::valueOf).toList());
        allContacts.addAll(this.allContacts.stream().map(Contact::getContactName).toList());
        allUsers.addAll(this.allUsers.stream().map(user -> user.getUserId() + " - " + user.getUserName()).toList());
        allCustomers.addAll(this.allCustomers.stream().map(customer -> customer.getCustomerId() + " - " + customer.getName()).toList());
        appointment_start_hour_input_combobox.setItems(allHours);
        appointment_start_minute_input_combobox.setItems(allMinutes);
        appointment_end_hour_input_combobox.setItems(allHours);
        appointment_end_minute_input_combobox.setItems(allMinutes);
        appointment_contact_input_combobox.setItems(allContacts);
        appointment_user_id_input_combobox.setItems(allUsers);
        appointment_customer_id_input_combobox.setItems(allCustomers);
        appointment_description_input_textfield.setText("");
        appointment_title_input_textfield.setText("");
        appointment_location_input_textfield.setText("");
        appointment_type_input_textfield.setText("");
        appointment_start_hour_input_combobox.getSelectionModel().selectFirst();
        appointment_start_minute_input_combobox.getSelectionModel().selectFirst();
        appointment_end_hour_input_combobox.getSelectionModel().selectFirst();
        appointment_end_minute_input_combobox.getSelectionModel().selectFirst();
        appointment_contact_input_combobox.getSelectionModel().selectFirst();
        appointment_user_id_input_combobox.getSelectionModel().selectFirst();
        appointment_customer_id_input_combobox.getSelectionModel().selectFirst();
        //reset the datepicker to default
        appointment_end_input_datepicker.setValue(LocalDate.now());
        appointment_start_input_datepicker.setValue(LocalDate.now());
    }



    private void loadAllDivisionsFromDatabase() {
        try {
            allDivisions = DivisionDAOImpl.getAllDivisions();
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Error getting all divisions");
        }
        for (Division d : allDivisions) {
            System.out.println("" + d.getDivisionName() + ", " + d.getCountryName() + "\n");
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
            if (customer_tableview.getSelectionModel().getSelectedItem() != null) {
                modify_customer_button.setDisable(false);
                delete_customer_button.setDisable(false);
            } else {
                modify_customer_button.setDisable(true);
                delete_customer_button.setDisable(true);
            }
        });
    }

    private void setupCountryComboboxListener() {
        customer_input_country_combobox.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
            ObservableList<String> allProvidences = FXCollections.observableArrayList();
            allProvidences.add("State/Providence...");
            for (Division d : allDivisions) {
                if (d.getCountryName().equals(customer_input_country_combobox.getSelectionModel().getSelectedItem())) {
                    allProvidences.add(d.getDivisionName());
                }
            }
            customer_input_state_combobox.setItems(allProvidences);
            customer_input_state_combobox.getSelectionModel().selectFirst();
            if (customer_input_state_combobox.getItems().size() > 1) {
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

    private void setupNewCustomer() {
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


        if (!isVisible) {
            add_modify_customer_title_label.setText("Add/Modify Customer");
        }
    }

    private void clearCustomerInputForm() {
        customer_name_textfield.clear();
        customer_id_textfield.clear();
        customer_address_textfield.clear();
        customer_postal_textfield.clear();
        customer_phone_textfield.clear();
        resetCountryStateValues();
    }

    private void setupModifyCustomer(Customer customer) {
        setCustomerFieldVisibility(true);
        resetCountryStateValues();
        customerOpenForModification = customer;
        add_modify_customer_title_label.setText("Modify Customer");
        customer_id_textfield.setText("" + customer.getCustomerId());
        customer_name_textfield.setText(customer.getName());
        customer_address_textfield.setText(customer.getAddress());
        customer_input_country_combobox.getSelectionModel().select(customer.getCountry());
        customer_input_state_combobox.getSelectionModel().select(customer.getDivision());
        customer_postal_textfield.setText(customer.getPostalCode());
        customer_phone_textfield.setText(customer.getPhone());
        customer_save_button_input.setText("Save Changes");
    }

    public void customerSaveButtonClicked(ActionEvent actionEvent) {
        if (customerAddFormValid()) {
            Customer customer = null;
            if (customer_id_textfield.getText().equals(AUTO_GENERATED_TEXT)) {
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
            } else {
                //Modify Customer
                customer = new Customer(Integer.parseInt(customer_id_textfield.getText()), customer_name_textfield.getText(),
                        customer_address_textfield.getText(), customer_postal_textfield.getText(),
                        customer_phone_textfield.getText(), customer_input_state_combobox.getSelectionModel().getSelectedItem().toString(), customer_input_country_combobox.getSelectionModel().getSelectedItem().toString());
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
        if (customer_name_textfield.getText().trim() == "") {
            errorMessage += "Name must be filled out\n";
        }
        if (customer_address_textfield.getText().trim() == "") {
            errorMessage += "Address must be filled out\n";
        }
        if (customer_phone_textfield.getText().trim() == "") {
            errorMessage += "Phone must be filled out\n";
        }
        if (customer_postal_textfield.getText().trim() == "") {
            errorMessage += "Postal must be filled out\n";
        }
        String selectedCountry = (String) customer_input_country_combobox.getSelectionModel().getSelectedItem();
        String selectedState = (String) customer_input_state_combobox.getSelectionModel().getSelectedItem();
        if (selectedCountry == "Country..." || selectedCountry == "") {
            errorMessage += "Country must be selected\n";
        }
        if (selectedState == "State/Providence..." || selectedState == "") {
            errorMessage += "State/Providence must be selected\n";
        }

        if (errorMessage.equals("")) {
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
        setupModifyCustomer((Customer) customer_tableview.getSelectionModel().getSelectedItem());
    }

    public void addCustomerButtonClicked(ActionEvent actionEvent) {
        setupNewCustomer();
    }

    public void deleteCustomerButtonClicked(ActionEvent actionEvent) {
        Customer selectedCustomer = (Customer) customer_tableview.getSelectionModel().getSelectedItem();
        if (Popups.confirmAction("Are you sure you want to delete customer " + selectedCustomer.getName() + "?")) {
            //possibly add feature to show what appointments will be deleted
            CustomerDAOImpl.deleteCustomer(selectedCustomer);
            clearCustomerInputForm();
            customerOpenForModification = null;
            loadAllCustomersFromDatabase();
            setCustomerFieldVisibility(false);
            updateCustomerTableView();

        }
    }

    public void addRandomAppointment(){
        //generate 15 first names
        String[] firstNames = {"John", "James", "Robert", "Michael", "William", "David", "Richard", "Charles", "Joseph", "Thomas", "Christopher", "Daniel", "Paul", "Mark", "Donald"};

        //generate 30 last names
        String[] lastNames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor", "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia", "Martinez", "Robinson", "Clark", "Rodriguez", "Lewis", "Lee", "Walker", "Hall", "Allen", "Young", "Hernandez", "King"};

        //generate a full name from firstNames and LastNames
        String newName = firstNames[(int) (Math.random() * firstNames.length)] + " " + lastNames[(int) (Math.random() * lastNames.length)];
        int newCustomerId = allCustomers.get((int)(Math.random() * allCustomers.size())).getCustomerId();
        int newUserId = allUsers.get((int)(Math.random() * allUsers.size())).getUserId();

        String[] titles = {"Sir", "Mam", "Mr.", "Mrs.", "Ms.", "Dr.", "Prof."};

        String[] newType = {"Consultation", "Follow Up", "New Patient", "Annual Checkup", "Physical", "Surgery", "X-Ray", "MRI", "CT Scan", "Ultrasound", "Blood Test", "Lab Work", "Dental Cleaning", "Dental Filling", "Dental Extraction"};

        //generate 15 random unique locations
        String[] newLocation = {"Room 1", "Room 2", "Room 3", "Room 4", "Room 5", "Room 6", "Room 7", "Room 8", "Room 9", "Room 10", "Room 11", "Room 12", "Room 13", "Room 14", "Room 15"};

        //generate 15 random unique additional notes
        String[] newDescription = {"Patient is allergic to penicillin", "Patient is allergic to sulfa", "Patient is allergic to codeine", "Patient is allergic to morphine", "Patient is allergic to latex", "Patient is allergic to peanuts", "Patient is allergic to shellfish", "Patient is allergic to dairy", "Patient is allergic to eggs", "Patient is allergic to soy", "Patient is allergic to wheat", "Patient is allergic to gluten", "Patient is allergic to nuts", "Patient is allergic to fish", "Patient is allergic to cats"};

        //generate a random contact from allContacts
        String newContact = allContacts.get((int)(Math.random() * allContacts.size())).getContactName();

        //generate a random time between 8am and 2pm EST on today's date
        LocalDateTime newStart = LocalDateTime.now().withHour((int)(Math.random() * 6 + 8)).withMinute((int)(Math.random() * 60)).withSecond(0).withNano(0);
        newStart = newStart.plusDays((int)(Math.random() * 199 + 2));
        LocalDateTime newEnd = newStart.plusMinutes((int)(Math.random() * 30 + 30));

        //generate a random title from titles
        String newTitle = titles[(int)(Math.random() * titles.length)];

        //generate a random type from newType
        String newTypeString = newType[(int)(Math.random() * newType.length)];

        //generate a random location from newLocation
        String newLocationString = newLocation[(int)(Math.random() * newLocation.length)];

        //generate a random description from newDescription
        String newDescriptionString = newDescription[(int)(Math.random() * newDescription.length)];

        Appointment newAppointment = new Appointment(-1, newTitle, newDescriptionString, newLocationString, newTypeString, newContact, newStart, newEnd, newCustomerId, newUserId);
        AppointmentDAOImpl.addAppointment(newAppointment);




    }

    public void onAddAppointmentClicked(ActionEvent actionEvent) {
        setAppointmentFieldVisibility(true);
        loadAppointmentInputDefaults();
        add_modify_appointment_title.setText("Add Appointment");
        appointment_save_input_button.setText("Save Appointment");
    }

    public void onModifyAppointmentClicked(ActionEvent actionEvent) {
        Appointment selectedAppointment = (Appointment) appointments_tableview.getSelectionModel().getSelectedItem();
        if (selectedAppointment != null) {
            setAppointmentFieldVisibility(true);
            loadAppointmentInputDefaults();
            add_modify_appointment_title.setText("Modify Appointment");
            appointment_save_input_button.setText("Save Changes");
            appointment_title_input_textfield.setText(selectedAppointment.getTitle());
            appointment_description_input_textfield.setText(selectedAppointment.getDescription());
            appointment_location_input_textfield.setText(selectedAppointment.getLocation());
            appointment_type_input_textfield.setText(selectedAppointment.getType());
            appointment_contact_input_combobox.getSelectionModel().select(selectedAppointment.getContact());
            appointment_start_input_datepicker.setValue(selectedAppointment.getStart().toLocalDate());
            appointment_end_input_datepicker.setValue(selectedAppointment.getEnd().toLocalDate());

            //convert the EST start time to local hour and minute
            int startHour = selectedAppointment.getStart().atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("America/New_York")).toLocalTime().getHour();
            int startMinute = selectedAppointment.getStart().atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("America/New_York")).toLocalTime().getMinute();

            appointment_start_hour_input_combobox.getSelectionModel().select(startHour);
            if(startMinute!=0) {
                appointment_start_minute_input_combobox.getSelectionModel().select(startMinute);
            } else {
                appointment_start_minute_input_combobox.getSelectionModel().select("00");
            }
            appointment_end_hour_input_combobox.getSelectionModel().select(startHour);
            if(startMinute!=0) {
                appointment_end_minute_input_combobox.getSelectionModel().select(startMinute);
            } else {
                appointment_end_minute_input_combobox.getSelectionModel().select("00");
            }


            //make a string with customer id followed by hyphen then customer name
            String customerString = selectedAppointment.getCustomerId() + " - " + CustomerDAOImpl.getCustomerById(selectedAppointment.getCustomerId()).getName();
            appointment_customer_id_input_combobox.getSelectionModel().select(customerString);

            //make a string with user id followed by hyphen then user name
            String userString = selectedAppointment.getUserId() + " - " + UserDAOImpl.getUserById(selectedAppointment.getUserId()).getUserName();
            appointment_user_id_input_combobox.getSelectionModel().select(userString);


        }
    }

    private void setAppointmentFieldVisibility(boolean b) {
        //set all of the appointment input fields to the opposite of b
        add_modify_appointment_title.setDisable(!b);
        appointment_id_input_label.setDisable(!b);
        appointment_title_input_label.setDisable(!b);
        appointment_title_input_textfield.setDisable(!b);
        appointment_description_input_label.setDisable(!b);
        appointment_description_input_textfield.setDisable(!b);
        appointment_location_input_label.setDisable(!b);
        appointment_location_input_textfield.setDisable(!b);
        appointment_contact_input_combobox.setDisable(!b);
        appointment_type_input_label.setDisable(!b);
        appointment_type_input_textfield.setDisable(!b);
        appointment_start_input_label.setDisable(!b);
        appointment_start_input_datepicker.setDisable(!b);
        appointment_start_hour_input_combobox.setDisable(!b);
        appointment_start_minute_input_combobox.setDisable(!b);
        appointment_end_hour_input_combobox.setDisable(!b);
        appointment_end_minute_input_combobox.setDisable(!b);
        appointment_end_input_datepicker.setDisable(!b);
        appointment_end_input_label.setDisable(!b);
        appointment_save_input_button.setDisable(!b);
        appointment_customer_id_input_combobox.setDisable(!b);
        appointment_user_id_input_combobox.setDisable(!b);
        appointment_cancel_input_button.setDisable(!b);


    }

    public void onCancelAppointmentClicked(ActionEvent actionEvent) {
        loadAppointmentInputDefaults();
        setAppointmentFieldVisibility(false);
    }

    public void onCurrentWeekRadioSelected(ActionEvent actionEvent) {
        //set the tableview to show appointments only from the current week
        updateAppointmentTableviewFilterWeekly();


    }

    private void updateAppointmentTableviewFilterWeekly() {
        //get all appointments from allAppointmentList between last sunday and now
        LocalDate now = LocalDate.now();
        LocalDate lastSunday = now.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY));
        ObservableList<Appointment> weeklyAppointments = FXCollections.observableArrayList();
        for (Appointment appointment : allAppointments) {
            if (appointment.getStart().toLocalDate().isAfter(lastSunday) && appointment.getStart().toLocalDate().isBefore(now)) {
                weeklyAppointments.add(appointment);
            }
        }
        appointments_tableview.setItems(weeklyAppointments);
    }



    public void onCurrentMonthRadioSelected(ActionEvent actionEvent) {
        updateAppointmentTableviewFilterMonthly();
    }

    private void updateAppointmentTableviewFilterMonthly() {
        //get all appointments from allAppointments between the first of the month and now
        LocalDate now = LocalDate.now();
        LocalDate firstOfMonth = now.with(TemporalAdjusters.firstDayOfMonth());
        ObservableList<Appointment> monthlyAppointments = FXCollections.observableArrayList();
        for (Appointment appointment : allAppointments) {
            if (appointment.getStart().toLocalDate().isAfter(firstOfMonth) && appointment.getStart().toLocalDate().isBefore(now)) {
                monthlyAppointments.add(appointment);
            }
        }
        appointments_tableview.setItems(monthlyAppointments);
    }

    public void onAllRadioSelected(ActionEvent actionEvent) {
        updateAppointmentTableviewFilterAll();
    }

    private void updateAppointmentTableviewFilterAll() {
        appointments_tableview.setItems(allAppointments);
    }

    public void onDeleteAppointmentClicked(ActionEvent actionEvent) {
        if(Popups.confirmAction("Are you sure you want to delete the selected appointment?")){
            Appointment selectedAppointment = (Appointment) appointments_tableview.getSelectionModel().getSelectedItem();
            AppointmentDAOImpl.deleteAppointment(selectedAppointment);
            loadAllAppointmentsFromDatabase();
            appointments_tableview.setItems(allAppointments);
        };
    }
}
