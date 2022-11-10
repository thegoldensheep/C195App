package Controller;

import DAO.*;
import Model.*;
import Utilities.Popups;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

public class UserFormController implements Initializable {
    @FXML
    public Label appointment_time_semicolon_label;
    @FXML
    public TextField appointment_start_hour_input_textfield;
    @FXML
    public TextField appointment_start_min_input_textfield;
    @FXML
    public Button appointment_start_am_input_button;
    @FXML
    public Button appointment_start_pm_input_button;
    @FXML
    public HBox appointment_start_bounding_hbox;
    @FXML
    public TextField appointment_end_hour_input_textfield;
    @FXML
    public Label appointment_time_semicolon_label_2;
    @FXML
    public HBox appointment_end_bounding_hbox;
    @FXML
    public TextField appointment_end_min_input_textfield;
    @FXML
    public Button appointment_end_am_input_button;
    @FXML
    public Button appointment_end_pm_input_button;
    @FXML
    public Button customer_show_appointments_button;
    @FXML
    public Button appointment_customer_report_button;
    @FXML
    public ChoiceBox appointment_customer_report_choicebox;
    @FXML
    public Label appointment_contact_report_label;
    @FXML
    private Pane add_modify_customer_pane;
    @FXML
    private Pane add_modify_appointment_pane;
    @FXML
    private RadioButton appointment_all_radio;
    @FXML
    private RadioButton appointment_current_week_radio;
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
    private Label appointment_end_input_label;;

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
        //generateRandomAll(100);

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
        setupDatePickers();

        setAppointmentFieldVisibility(false);
        setCustomerFieldVisibility(false);

        setupHourMinuteTextfields();

        setupContactsReport();



    }

    private void setupContactsReport() {
        try{
            ObservableList<Contact> contacts = FXCollections.observableArrayList();
            contacts = ContactDAOImpl.getAllContacts().stream()
                    .sorted(Comparator.comparing(Contact::getContactName))
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
            for (Contact contact : contacts) {
                appointment_customer_report_choicebox.getItems().add(""+contact.getContactId()+" - "+contact.getContactName());
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        appointment_customer_report_button.setDisable(true);
        appointment_customer_report_choicebox.setDisable(false);

        appointment_customer_report_choicebox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                appointment_contact_report_label.setVisible(false);
                appointment_customer_report_button.setDisable(false);
            }else{

                appointment_customer_report_button.setDisable(true);
                appointment_contact_report_label.setDisable(true);
            }
        });
    }

    private void setupHourMinuteTextfields() {
        appointment_start_hour_input_textfield.textProperty().addListener((obs, oldText, newText) -> {
            try{
                int hour = Integer.parseInt(newText);
                if(hour < 0 || hour > 23){
                    appointment_start_hour_input_textfield.requestFocus();
                }else{
                    if(newText.length() == 2){
                        if(hour > 12) {
                            appointment_start_pm_input_button.fire();
                            hour -= 12;
                        }
                        appointment_start_hour_input_textfield.setText(String.format("%02d", hour));
                        if(appointment_start_hour_input_textfield.isFocused()){
                            appointment_start_min_input_textfield.requestFocus();
                        }
                    }

                }
            }catch(NumberFormatException e){
                appointment_start_hour_input_textfield.setText("");
            }
        });

        appointment_start_hour_input_textfield.focusedProperty().addListener((obs, oldText, newText) -> {
            if(!newText){
                try{
                    int hour = Integer.parseInt(appointment_start_hour_input_textfield.getText());
                    if(hour < 0 || hour > 23){
                        appointment_start_hour_input_textfield.setText("");
                    }else{
                        if(appointment_start_hour_input_textfield.getText().length() == 2){
                            if(hour > 12){
                                appointment_start_pm_input_button.fire();
                                hour -= 12;
                            }

                        }
                        if(hour == 0){
                            appointment_start_hour_input_textfield.setText("12");
                            hour=12;
                            appointment_start_am_input_button.fire();
                        }
                        appointment_start_hour_input_textfield.setText(String.format("%02d", hour));
                    }
                }catch(NumberFormatException e){
                    appointment_start_hour_input_textfield.setText("");
                }
            }
        });

        appointment_end_hour_input_textfield.textProperty().addListener((obs, oldText, newText) -> {
            try{
                int hour = Integer.parseInt(newText);
                if(hour < 0 || hour > 23){
                    appointment_end_hour_input_textfield.requestFocus();
                }else{
                    if(newText.length() == 2){
                        if(hour > 12) {
                            appointment_end_pm_input_button.fire();
                            hour -= 12;
                        }
                        appointment_end_hour_input_textfield.setText(String.format("%02d", hour));
                        if(appointment_end_hour_input_textfield.isFocused()){
                            appointment_end_min_input_textfield.requestFocus();
                        }
                    }

                }
            }catch(NumberFormatException e){
                appointment_start_hour_input_textfield.setText("");
            }
        });

        appointment_end_hour_input_textfield.focusedProperty().addListener((obs, oldText, newText) -> {
            if(!newText){
                try{
                    int hour = Integer.parseInt(appointment_end_hour_input_textfield.getText());
                    if(hour < 0 || hour > 23){
                        appointment_end_hour_input_textfield.setText("");
                    }else{
                        if(appointment_end_hour_input_textfield.getText().length() == 2){
                            if(hour > 12){
                                appointment_end_pm_input_button.fire();
                                hour -= 12;
                            }

                        }
                        if(hour == 0){
                            appointment_end_hour_input_textfield.setText("12");
                            hour=12;
                            appointment_end_am_input_button.fire();
                        }
                        appointment_end_hour_input_textfield.setText(String.format("%02d", hour));
                    }
                }catch(NumberFormatException e){
                    appointment_end_hour_input_textfield.setText("");
                }
            }
        });

        appointment_start_min_input_textfield.focusedProperty().addListener((obs, losingFocus, gainingFocus) -> {
            if(losingFocus){
                try{
                    int min = Integer.parseInt(appointment_start_min_input_textfield.getText());
                    if(min < 0 || min > 59){
                        appointment_start_min_input_textfield.setText("");
                    }else{
                        appointment_start_min_input_textfield.setText(String.format("%02d", min));
                    }
                }catch(NumberFormatException e){
                    appointment_start_min_input_textfield.setText("");
                }
            }
        });

        appointment_end_min_input_textfield.focusedProperty().addListener((obs, losingFocus, gainingFocus) -> {
            if(losingFocus){
                try{
                    int min = Integer.parseInt(appointment_end_min_input_textfield.getText());
                    if(min < 0 || min > 59){
                        appointment_end_min_input_textfield.setText("");
                    }else{
                        appointment_end_min_input_textfield.setText(String.format("%02d", min));
                    }
                }catch(NumberFormatException e){
                    appointment_end_min_input_textfield.setText("");
                }
            }
        });







    }

    private void generateRandomAll(int amount) {
        for (int i = 0; i < amount; i++) {
            addRandomContact();
        }
        
        for (int i = 0; i < amount; i++) {
            addRandomCustomer();
        }
        for (int i = 0; i < amount; i++) {
            addRandomAppointment();
        }

    }

    private void addRandomContact() {
        String fullName = generateRandomFullName();
        String email = "";
        ObservableList<String> domainNames = FXCollections.observableArrayList();

        //generate a list of random domain names
        domainNames.addAll("gmail.com", "yahoo.com", "hotmail.com", "aol.com", "msn.com", "comcast.net", "live.com", "sbcglobal.net", "verizon.net", "cox.net", "att.net", "me.com", "mac.com", "earthlink.net", "optonline.net", "charter.net", "shaw.ca", "yahoo.ca", "googlemail.com", "mail.com", "qq.com", "naver.com", "hanmail.net", "daum.net", "nate.com", "yahoo.co.jp", "yahoo.co.kr", "yahoo.co.id", "yahoo.co.in", "yahoo.com.sg", "yahoo.com.ph", "163.com", "yeah.net", "126.com", "21cn.com", "aliyun.com", "foxmail.com");

        //generate a random email address using first.last@domain
        email = fullName.replace(" ", ".") + "@" + domainNames.get((int)(Math.random() * domainNames.size()));

        Contact contact = new Contact(-1, fullName, email);
        ContactDAOImpl.addContact(contact);

    }

    private String generateRandomFullName(){
        String firstName = "";
        String lastName = "";
        ObservableList<String> firstNames = FXCollections.observableArrayList();
        ObservableList<String> lastNames = FXCollections.observableArrayList();

        firstNames.addAll("Dillon", "EV", "Michael", "Kana", "Shola", "Josh", "Erin", "Danaka", "Weezy", "Connor", "Travis", "Matthew", "Kristian", "Justin", "Matteo", "Daddi Dre", "Britney", "TK", "Pou", "Vic", "Tichelle", "Kaitlyn");
        lastNames.addAll("Shepherd", "Cabrinha", "Briggs", "Adesanwo", "Kotrba", "Berkey", "Andrews", "Nguyen-Murphy", "beefx", "Watt", "Banks", "Dixon", "McCray", "Evans");
        firstName = firstNames.get((int)(Math.random() * firstNames.size()));
        lastName = lastNames.get((int)(Math.random() * lastNames.size()));
        return ""+firstName + " " + lastName;
    }

    private void setupDatePickers() {
        //disable textbox on start and end datepickers
        appointment_start_input_datepicker.getEditor().setDisable(true);
        appointment_end_input_datepicker.getEditor().setDisable(true);
        appointment_start_input_datepicker.getEditor().setStyle("-fx-opacity: 1;");
        appointment_end_input_datepicker.getEditor().setStyle("-fx-opacity: 1;");
    }

    private void updateAppointmentTableView() {
        appointment_id_column.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appointment_title_column.setCellValueFactory(new PropertyValueFactory<>("title"));
        appointment_description_column.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointment_location_column.setCellValueFactory(new PropertyValueFactory<>("location"));
        appointment_contact_column.setCellValueFactory(new PropertyValueFactory<>("contact"));
        appointment_type_column.setCellValueFactory(new PropertyValueFactory<>("type"));
        //appointment_start_column set cell factory to format date short
        appointment_start_column.setCellValueFactory(new PropertyValueFactory<>("start"));
        appointment_start_column.setCellFactory(column -> {
            return new TableCell<Appointment, LocalDateTime>() {
                @Override
                protected void updateItem(LocalDateTime item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {
                        // Format date to MM/DD/YYYY HH:MM AM/PM
                        setText(item.format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a")));
                    }
                }
            };
        });
        //appointment_end_column set cell factory to format date short
        appointment_end_column.setCellValueFactory(new PropertyValueFactory<>("end"));
        appointment_end_column.setCellFactory(column -> {
            return new TableCell<Appointment, LocalDateTime>() {
                @Override
                protected void updateItem(LocalDateTime item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a")));
                    }
                }
            };
        });



        appointment_start_column.setCellValueFactory(new PropertyValueFactory<>("start"));
        appointment_end_column.setCellValueFactory(new PropertyValueFactory<>("end"));

        appointment_customer_id_column.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        appointment_user_id_column.setCellValueFactory(new PropertyValueFactory<>("userId"));

        //set appointments tableview to allappointments sorted by id
        appointments_tableview.setItems(allAppointments.stream()
                .sorted(Comparator.comparing(Appointment::getAppointmentId))
                .collect(Collectors.toCollection(FXCollections::observableArrayList)));
        appointments_tableview.refresh();
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


        for(int i = 0 ; i < 60 ; i++) {
            String minute = String.valueOf(i);
            if(minute.length() == 1) {
                minute = "0" + minute;
            }
            allMinutes.add(minute);
        }

        for(int i = 0 ; i < 24 ; i++) {
            String hour = String.valueOf(i);
            if(hour.length() == 1) {
                hour = "0" + hour;
            }

            allHours.add(hour);
        }



        allContacts.addAll(this.allContacts.stream()
                .sorted(Comparator.comparing(Contact::getContactName))
                .map(Contact::getContactName).toList().stream().toList());
        allUsers.addAll(this.allUsers.stream()
                .sorted(Comparator.comparing(User::getUserName))
                .map(user -> user.getUserId() + " - " + user.getUserName()).toList().stream().toList());
        allCustomers.addAll(this.allCustomers.stream()
                .sorted(Comparator.comparing(Customer::getName))
                .map(customer -> customer.getCustomerId() + " - " + customer.getName()).toList().stream().toList());
        appointment_id_input_textfield.setText("");

        appointment_contact_input_combobox.setItems(allContacts);
        appointment_user_id_input_combobox.setItems(allUsers);
        //create a filtered list of all customers sorted by customer id

        appointment_customer_id_input_combobox.setItems(allCustomers);
        appointment_description_input_textfield.setText("");
        appointment_title_input_textfield.setText("");
        appointment_location_input_textfield.setText("");
        appointment_type_input_textfield.setText("");
        appointment_contact_input_combobox.getSelectionModel().selectFirst();
        appointment_user_id_input_combobox.getSelectionModel().selectFirst();
        appointment_customer_id_input_combobox.getSelectionModel().selectFirst();
        //reset the datepicker to default
        appointment_end_input_datepicker.setValue(LocalDate.now());
        appointment_start_input_datepicker.setValue(LocalDate.now());

        //start
        appointment_start_hour_input_textfield.setText("");
        appointment_start_min_input_textfield.setText("");
        appointment_start_am_input_button.getStyleClass().removeIf(s -> s.equals("selected"));
        appointment_start_pm_input_button.getStyleClass().removeIf(s -> s.equals("selected"));

        //end
        appointment_end_hour_input_textfield.setText("");
        appointment_end_min_input_textfield.setText("");
        appointment_end_am_input_button.getStyleClass().removeIf(s -> s.equals("selected"));
        appointment_end_pm_input_button.getStyleClass().removeIf(s -> s.equals("selected"));

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
        //set customer tableview to allcustomers sorted by customer id
        customer_tableview.setItems(allCustomers.stream()
                .sorted(Comparator.comparingInt(Customer::getCustomerId))
                .collect(Collectors.toCollection(FXCollections::observableArrayList)));
        customer_id_table_column.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customer_name_table_column.setCellValueFactory(new PropertyValueFactory<>("name"));
        customer_address_table_column.setCellValueFactory(new PropertyValueFactory<>("address"));
        customer_state_table_column.setCellValueFactory(new PropertyValueFactory<>("division"));
        customer_country_table_column.setCellValueFactory(new PropertyValueFactory<>("country"));
        customer_postal_code_table_column.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        customer_phone_table_column.setCellValueFactory(new PropertyValueFactory<>("phone"));
        customer_tableview.refresh();
    }

    private void setupNewCustomer() {
        setCustomerFieldVisibility(true);
        clearCustomerInputForm();
        resetCountryStateValues();
        add_modify_customer_title_label.setText("Add Customer");
        customerOpenForModification = null;
        customer_id_textfield.setText(AUTO_GENERATED_TEXT);
        customer_save_button_input.setText("Save New");
        customer_name_textfield.requestFocus();
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

        if(isVisible){
            //set the add_modify_appointment_pane border to blue
            add_modify_customer_pane.setStyle("-fx-border-color: #aaf; -fx-border-style: solid solid solid solid;");
        }else{
            //set the add_modify_appointment_pane border to #ccc
            add_modify_customer_pane.setStyle("-fx-border-color: #ccc; -fx-border-style: solid solid solid none;");
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
        customer_name_textfield.requestFocus();
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
            updateAppointmentInputCustomerIdComboBox();
        }
    }

    private void updateAppointmentInputCustomerIdComboBox() {
        String selectedCustomerId = appointment_customer_id_input_combobox.getSelectionModel().getSelectedItem().toString();
        //set appointment customer id combobox to empty
        appointment_customer_id_input_combobox.setItems(FXCollections.observableArrayList());
        appointment_customer_id_input_combobox.getItems().add("Customer ID...");
        for (Customer customer : allCustomers) {
            appointment_customer_id_input_combobox.getItems().add(customer.getCustomerId()+" - "+customer.getName());
        }
        //if the selected customer id is still in the list, select it
        if (appointment_customer_id_input_combobox.getItems().contains(selectedCustomerId)) {
            appointment_customer_id_input_combobox.getSelectionModel().select(selectedCustomerId);
        }else{
            loadAppointmentInputDefaults();
            setAppointmentFieldVisibility(false);
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
        ObservableList<Appointment> appointmentsWithCustomer = allAppointments.filtered(appointment -> appointment.getCustomerId() == selectedCustomer.getCustomerId())
                .stream().sorted(Comparator.comparing(Appointment::getStart))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        String confirmationMessage = "Are you sure you want to delete " + selectedCustomer.getName() + "? (ID: "+selectedCustomer.getCustomerId()+")\n\n";
        if (appointmentsWithCustomer.size() > 0) {
            confirmationMessage += "This customer has the following " + appointmentsWithCustomer.size() + " appointment(s) associated with them, which will also be deleted:\n\n";
            for (Appointment appointment : appointmentsWithCustomer) {
                //add appointment id, and start/end time in short format to confirmation message
                confirmationMessage += "Appt. ID: " + appointment.getAppointmentId() + ", " + appointment.getStart().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)) + " - " + appointment.getEnd().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)) + "\n";


            }
        }
        if (Popups.confirmAction(confirmationMessage)) {



            CustomerDAOImpl.deleteCustomer(selectedCustomer);
            clearCustomerInputForm();
            customerOpenForModification = null;
            loadAllCustomersFromDatabase();
            setCustomerFieldVisibility(false);
            updateCustomerTableView();
            refreshAppointmentsTableview();
            updateAppointmentInputCustomerIdComboBox();
        }
    }

    private void refreshAppointmentsTableview() {
        loadAllAppointmentsFromDatabase();
        if(appointment_current_month_radio.isSelected()){
            updateAppointmentTableviewFilterMonthly();
        }else if(appointment_current_week_radio.isSelected()){
            updateAppointmentTableviewFilterWeekly();
        }else if(appointment_all_radio.isSelected()){
            updateAppointmentTableviewFilterAll();
        }
        appointments_tableview.refresh();
    }

    private void addRandomCustomer(){
        //generate a full name
        String fullName = generateRandomFullName();

        //generate a random list of street names
        String[] streetNames = {"Main", "Park", "Oak", "Pine", "Maple", "Cedar", "Elm", "Hickory", "Birch", "Willow", "Walnut", "Chestnut", "Spruce", "Palm", "Cypress", "Locust", "Cherry", "Juniper", "Sycamore", "Ash", "Dogwood", "Holly", "Magnolia", "Peach", "Pear", "Plum", "Poplar", "Redwood", "Sassafras", "Sycamore", "Walnut", "Willow", "Yew", "Acacia", "Alder", "Aspen", "Beech", "Birch", "Cedar", "Cottonwood", "Cypress", "Dogwood", "Elm", "Fir", "Hemlock", "Hickory", "Holly", "Juniper", "Larch", "Maple", "Oak", "Palm", "Pine", "Redwood", "Spruce", "Sycamore", "Walnut", "Willow", "Yew"};

        //generate a random list of street types
        String[] streetTypes = {"Avenue", "Boulevard", "Circle", "Court", "Drive", "Lane", "Parkway", "Place", "Road", "Square", "Street", "Terrace", "Trail", "Way"};

        //generate a random number between 1 and 9999
        int streetNumber = (int)(Math.random()*9999)+1;

        //generate a random city name
        String[] cityNames = {"New York", "Los Angeles", "Chicago", "Houston", "Philadelphia", "Phoenix", "San Antonio", "San Diego", "Dallas", "San Jose", "Austin", "Jacksonville", "San Francisco", "Indianapolis", "Columbus",
                "Fort Worth", "Charlotte", "Detroit", "El Paso", "Memphis", "Boston", "Seattle", "Denver", "Nashville", "Baltimore", "Louisville", "Milwaukee", "Portland", "Las Vegas", "Oklahoma City", "Albuquerque", "Tucson",
                "Fresno", "Sacramento", "Long Beach", "Kansas City", "Mesa", "Atlanta", "Virginia Beach", "Omaha", "Colorado Springs", "Raleigh", "Miami", "Oakland", "Minneapolis", "Tulsa", "Cleveland", "Wichita", "Arlington",
                "New Orleans", "Bakersfield", "Tampa", "Honolulu", "Aurora", "Anaheim", "Santa Ana", "St. Louis", "Riverside", "Corpus Christi", "Lexington", "Pittsburgh", "Anchorage", "Stockton", "Cincinnati", "Saint Paul",
                "Toledo", "Greensboro", "Newark", "Plano", "Henderson", "Lincoln", "Buffalo", "Jersey City", "Chula Vista", "Fort Wayne", "Orlando", "St. Petersburg", "Chandler", "Laredo", "Norfolk", "Durham", "Madison", "Lubbock",
                "Irvine", "Winston-Salem", "Glendale", "Garland", "Hialeah", "Reno", "Chesapeake", "Gilbert", "Baton Rouge", "Irving", "Scottsdale", "North Las Vegas", "Fremont", "Boise City", "Richmond", "San Bernardino", "Birmingham",
                "Spokane", "Rochester", "Des Moines", "Modesto", "Fayetteville", "Tacoma", "Oxnard", "Fontana", "Columbus", "Montgomery", "Moreno Valley", "Shreveport", "Aurora", "Yonkers", "Akron", "Huntington Beach"};

        //generate a string by combining the street number, street name,  street type, and city name
        String address = streetNumber + " " + streetNames[(int)(Math.random()*streetNames.length)] + " " + streetTypes[(int)(Math.random()*streetTypes.length)] + ", " + cityNames[(int)(Math.random()*cityNames.length)];

        //get a random division
        Division division = DivisionDAOImpl.getAllDivisions().get((int)(Math.random()*DivisionDAOImpl.getAllDivisions().size()));

        //generate a random phone number
        String phoneNumber = String.format("%03d", (int)(Math.random()*999)) + "-" + String.format("%03d", (int)(Math.random()*999)) + "-" + String.format("%04d", (int)(Math.random()*9999));

        //generate a random postal code
        String postalCode = String.format("%05d", (int)(Math.random()*99999));

        try {
            CustomerDAOImpl.addNewCustomer(new Customer(0, fullName, address, postalCode, phoneNumber, division.getDivisionName(), division.getCountryName()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private void addRandomAppointment(){
        ObservableList<Customer> custs = FXCollections.observableArrayList();
        ObservableList<User> users = FXCollections.observableArrayList();
        ObservableList<Contact> conts = FXCollections.observableArrayList();
        try {
            custs = CustomerDAOImpl.getAllCustomers();
            users = UserDAOImpl.getAllUsers();
            conts = ContactDAOImpl.getAllContacts();
        } catch (Exception exception) {
            exception.printStackTrace();
        }


        //generate a full name from firstNames and LastNames
        int newCustomerId = custs.get((int)(Math.random() * custs.size())).getCustomerId();
        int newUserId = users.get((int)(Math.random() * users.size())).getUserId();
        String newContact = conts.get((int)(Math.random() * conts.size())).getContactName();

        String[] titles = {"Sir", "Mam", "Mr.", "Mrs.", "Ms.", "Dr.", "Prof."};

        String[] newType = {"Consultation", "Follow Up", "New Patient", "Annual Checkup", "Physical", "Surgery", "X-Ray", "MRI", "CT Scan", "Ultrasound", "Blood Test", "Lab Work", "Dental Cleaning", "Dental Filling", "Dental Extraction"};

        //generate 15 random unique locations
        String[] newLocation = {"Room 1", "Room 2", "Room 3", "Room 4", "Room 5", "Room 6", "Room 7", "Room 8", "Room 9", "Room 10", "Room 11", "Room 12", "Room 13", "Room 14", "Room 15"};

        //generate 15 random unique additional notes
        String[] newDescription = {"Patient is allergic to penicillin", "Patient is allergic to sulfa", "Patient is allergic to codeine", "Patient is allergic to morphine", "Patient is allergic to latex", "Patient is allergic to peanuts", "Patient is allergic to shellfish", "Patient is allergic to dairy", "Patient is allergic to eggs", "Patient is allergic to soy", "Patient is allergic to wheat", "Patient is allergic to gluten", "Patient is allergic to nuts", "Patient is allergic to fish", "Patient is allergic to cats"};

        //generate a random time between 8am and 10pm est today
        LocalDateTime newStart = LocalDateTime.now().withHour((int)(Math.random() * 14 + 8)).withMinute((int)(Math.random() * 60)).withSecond(0).withNano(0);
        newStart = newStart.plusDays((int)(Math.random() * 200));
        LocalDateTime newEnd = newStart.plusMinutes(30);



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
        appointment_id_input_textfield.setText("auto-generated");
        add_modify_appointment_title.setText("Add Appointment");
        appointment_save_input_button.setText("Save Appointment");
        appointment_title_input_textfield.requestFocus();
    }

    public void onModifyAppointmentClicked(ActionEvent actionEvent) {
        Appointment selectedAppointment = (Appointment) appointments_tableview.getSelectionModel().getSelectedItem();
        if (selectedAppointment != null) {
            setAppointmentFieldVisibility(true);
            loadAppointmentInputDefaults();
            add_modify_appointment_title.setText("Modify Appointment");
            appointment_save_input_button.setText("Save Changes");
            appointment_id_input_textfield.setText(String.valueOf(selectedAppointment.getAppointmentId()));
            appointment_title_input_textfield.setText(selectedAppointment.getTitle());
            appointment_description_input_textfield.setText(selectedAppointment.getDescription());
            appointment_location_input_textfield.setText(selectedAppointment.getLocation());
            appointment_type_input_textfield.setText(selectedAppointment.getType());
            appointment_contact_input_combobox.getSelectionModel().select(selectedAppointment.getContact());
            appointment_start_input_datepicker.setValue(selectedAppointment.getStart().toLocalDate());
            appointment_end_input_datepicker.setValue(selectedAppointment.getEnd().toLocalDate());

            String startMinute = String.format("%02d", selectedAppointment.getStart().getMinute());
            String startHour = String.format("%02d", selectedAppointment.getStart().getHour());
            String endHour = String.format("%02d", selectedAppointment.getEnd().getHour());
            String endMinute = String.format("%02d", selectedAppointment.getEnd().getMinute());

            appointment_start_hour_input_textfield.setText(startHour);
            appointment_start_min_input_textfield.setText(startMinute);
            if(selectedAppointment.getStart().getHour()>=12){
                appointment_start_pm_input_button.fire();
            }else{
                if(selectedAppointment.getStart().getHour()==0) startHour = "12";
                appointment_start_am_input_button.fire();
            }

            appointment_end_hour_input_textfield.setText(endHour);
            appointment_end_min_input_textfield.setText(endMinute);
            if(selectedAppointment.getEnd().getHour()>=12){
                if(selectedAppointment.getEnd().getHour()==0) endHour = "12";
                appointment_end_pm_input_button.fire();
            }else{
                appointment_end_am_input_button.fire();
            }



            //make a string with customer id followed by hyphen then customer name
            String customerString = selectedAppointment.getCustomerId() + " - " + CustomerDAOImpl.getCustomerById(selectedAppointment.getCustomerId()).getName();
            appointment_customer_id_input_combobox.getSelectionModel().select(customerString);

            //make a string with user id followed by hyphen then user name
            String userString = selectedAppointment.getUserId() + " - " + UserDAOImpl.getUserById(selectedAppointment.getUserId()).getUserName();
            appointment_user_id_input_combobox.getSelectionModel().select(userString);

            appointment_title_input_textfield.requestFocus();

        }
    }

    private void setAppointmentFieldVisibility(boolean isVisible) {
        //set all of the appointment input fields to the opposite of isVisible
        add_modify_appointment_title.setDisable(!isVisible);
        appointment_id_input_label.setDisable(!isVisible);
        appointment_title_input_label.setDisable(!isVisible);
        appointment_title_input_textfield.setDisable(!isVisible);
        appointment_description_input_label.setDisable(!isVisible);
        appointment_description_input_textfield.setDisable(!isVisible);
        appointment_location_input_label.setDisable(!isVisible);
        appointment_location_input_textfield.setDisable(!isVisible);
        appointment_contact_input_combobox.setDisable(!isVisible);
        appointment_type_input_label.setDisable(!isVisible);
        appointment_type_input_textfield.setDisable(!isVisible);
        appointment_start_input_label.setDisable(!isVisible);
        appointment_start_input_datepicker.setDisable(!isVisible);
        appointment_end_input_datepicker.setDisable(!isVisible);
        appointment_end_input_label.setDisable(!isVisible);
        appointment_save_input_button.setDisable(!isVisible);
        appointment_customer_id_input_combobox.setDisable(!isVisible);
        appointment_user_id_input_combobox.setDisable(!isVisible);
        appointment_cancel_input_button.setDisable(!isVisible);

        //start
        appointment_start_hour_input_textfield.setDisable(!isVisible);
        appointment_start_min_input_textfield.setDisable(!isVisible);
        appointment_time_semicolon_label.setDisable(!isVisible);
        appointment_start_am_input_button.setDisable(!isVisible);
        appointment_start_pm_input_button.setDisable(!isVisible);

        //end
        appointment_end_hour_input_textfield.setDisable(!isVisible);
        appointment_end_min_input_textfield.setDisable(!isVisible);
        appointment_time_semicolon_label_2.setDisable(!isVisible);
        appointment_end_am_input_button.setDisable(!isVisible);
        appointment_end_pm_input_button.setDisable(!isVisible);





        if(isVisible){
            //set the add_modify_appointment_pane border to blue
            add_modify_appointment_pane.setStyle("-fx-border-color: blue ; -fx-border-style: solid solid solid solid");
        }else{
            //set the add_modify_appointment_pane border to #ccc
            add_modify_appointment_pane.setStyle("-fx-border-color: #ffa; -fx-border-style: solid solid solid none");
        }


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
        //get the date of last sunday at midnight
        LocalDateTime lastSunday = LocalDateTime.now().with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)).withHour(0).withMinute(0).withSecond(0).withNano(0);

        try{//get all appointments from allAppointments that are between lastSunday and nextSunday
            ObservableList<Appointment> filteredAppointments = FXCollections.observableArrayList();
            for (Appointment appointment : AppointmentDAOImpl.getAllAppointments()) {
                if (appointment.getStart().isAfter(lastSunday) && appointment.getStart().isBefore(lastSunday.plusDays(7))) {
                    filteredAppointments.add(appointment);
                }
            }
            //set appointments tableview to filteredAppointments sorted by appointment id
            appointments_tableview.setItems(filteredAppointments.stream()
                    .sorted(Comparator.comparing(Appointment::getAppointmentId))
                    .collect(Collectors.toCollection(FXCollections::observableArrayList)));
            appointments_tableview.refresh();
        }catch (Exception e){
            e.printStackTrace();
        }
    }





    public void onCurrentMonthRadioSelected(ActionEvent actionEvent) {
        updateAppointmentTableviewFilterMonthly();
    }

    private void updateAppointmentTableviewFilterMonthly() {
        //get the date of the first day of the current month at midnight
        LocalDateTime firstDayOfMonth = LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth()).withHour(0).withMinute(0).withSecond(0).withNano(0);

        try{ //get all appointments from allAppointments that are between firstDayOfMonth and lastDayOfMonth
            ObservableList<Appointment> filteredAppointments = FXCollections.observableArrayList();
            for (Appointment appointment : AppointmentDAOImpl.getAllAppointments()) {
                if (appointment.getStart().isAfter(firstDayOfMonth) && appointment.getStart().isBefore(firstDayOfMonth.plusMonths(1))) {
                    filteredAppointments.add(appointment);
                }
            }
            //set appointments tableview to filteredAppointments sorted by appointment id
            appointments_tableview.setItems(filteredAppointments.stream()
                    .sorted(Comparator.comparing(Appointment::getAppointmentId))
                    .collect(Collectors.toCollection(FXCollections::observableArrayList)));
            appointments_tableview.refresh();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void onAllRadioSelected(ActionEvent actionEvent) {
        updateAppointmentTableviewFilterAll();
    }

    private void updateAppointmentTableviewFilterAll() {
        try {
            //set appointments tableview to allAppointments sorted by appointment id
            appointments_tableview.setItems(AppointmentDAOImpl.getAllAppointments().stream()
                    .sorted(Comparator.comparing(Appointment::getAppointmentId))
                    .collect(Collectors.toCollection(FXCollections::observableArrayList)));
            appointments_tableview.refresh();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void onDeleteAppointmentClicked(ActionEvent actionEvent) {
        if(Popups.confirmAction("Are you sure you want to delete the selected appointment?")){
            Appointment selectedAppointment = (Appointment) appointments_tableview.getSelectionModel().getSelectedItem();
            if(appointment_id_input_textfield.getText() != "auto-generated" && selectedAppointment.getAppointmentId() == Integer.parseInt(appointment_id_input_textfield.getText())){
                loadAppointmentInputDefaults();
                setAppointmentFieldVisibility(false);
            }


            AppointmentDAOImpl.deleteAppointment(selectedAppointment);
            loadAllAppointmentsFromDatabase();
            //set the appointments tableview to all appointments sorted by appointment id
            try {
                appointments_tableview.setItems(AppointmentDAOImpl.getAllAppointments().stream()
                        .sorted(Comparator.comparing(Appointment::getAppointmentId))
                        .collect(Collectors.toCollection(FXCollections::observableArrayList)));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            appointments_tableview.refresh();
        };
    }


    public void saveAppointmentButtonClicked(ActionEvent actionEvent) {
        Appointment appointment = appointmentInputValid();
        if(appointment!=null){
            if(appointment.getAppointmentId()==-1 || appointment.getAppointmentId()==0){
                //if the appointment id is 0, then this is a new appointment
                AppointmentDAOImpl.addAppointment(appointment);
            }else{
                //if the appointment id is not 0, then this is an existing appointment
                AppointmentDAOImpl.updateAppointment(appointment);
            }
            loadAllAppointmentsFromDatabase();
            //set the appointments tableview to all appointments sorted by appointment id
            try {
                appointments_tableview.setItems(AppointmentDAOImpl.getAllAppointments().stream()
                        .sorted(Comparator.comparing(Appointment::getAppointmentId))
                        .collect(Collectors.toCollection(FXCollections::observableArrayList)));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            refreshAppointmentsTableview();
            loadAppointmentInputDefaults();
            setAppointmentFieldVisibility(false);
        }
    }


    private Appointment appointmentInputValid(){
        //get the values from the input fields
        String title = appointment_title_input_textfield.getText();
        String description = appointment_description_input_textfield.getText();
        String location = appointment_location_input_textfield.getText();
        String contact = appointment_contact_input_combobox.getSelectionModel().getSelectedItem().toString();
        String type = appointment_type_input_textfield.getText();
        LocalDate startDate = appointment_start_input_datepicker.getValue();
        LocalDate endDate = appointment_end_input_datepicker.getValue();
        String customerString = appointment_customer_id_input_combobox.getSelectionModel().getSelectedItem().toString();
        String userString = appointment_user_id_input_combobox.getSelectionModel().getSelectedItem().toString();

        String startHourString = appointment_start_hour_input_textfield.getText();
        String startMinuteString = appointment_start_min_input_textfield.getText();
        String endHourString = appointment_end_hour_input_textfield.getText();
        String endMinuteString = appointment_end_min_input_textfield.getText();


        //create error string
        String errorString = "";

        //if the appointment id textfield is not "auto-generated" then parse it into an int
        int appointmentId = -1;
        if(!appointment_id_input_textfield.getText().equals("auto-generated")){
            appointmentId = Integer.parseInt(appointment_id_input_textfield.getText().trim());
        }

        //add to error string if title is empty
        if(title.isEmpty()){
            errorString += "Title cannot be empty.\n";
        }

        //add to error string if description is empty
        if(description.isEmpty()){
            errorString += "Description cannot be empty.\n";
        }

        //add to error string if location is empty
        if(location.isEmpty()){
            errorString += "Location cannot be empty.\n";
        }

        //add to error string if contact is still default "Contact..."
        if(contact.equals("Contact...")){
            errorString += "Contact cannot be empty.\n";
        }

        //add to error string if type is empty
        if(type.isEmpty()){
            errorString += "Type cannot be empty.\n";
        }

        //add to error string if start date is empty
        if(startDate == null){
            errorString += "Must select start date.\n";
        }

        //add to error string if end date is empty
        if(endDate == null){
            errorString += "Must select end date.\n";
        }



        int customerId = 0;
        if(customerString!="Customer ID..."){
            //get the customer id from the customer string
            String[] customerStringArray = customerString.split(" - ");
            customerId = Integer.parseInt(customerStringArray[0]);

        }else{
            errorString += "Must select a customer id.\n";
        }

        int userId = 0;
        if(userString!="User ID..."){
            String[] userStringArray = userString.split(" - ");
            userId = Integer.parseInt(userStringArray[0]);
        }else{
            errorString += "Must select a user id.\n";
        }


        String startHourAmPm = "";
        String endHourAmPm = "";

        if(appointment_start_am_input_button.getStyleClass().contains("selected")){
            startHourAmPm = "am";
        }else if(appointment_start_pm_input_button.getStyleClass().contains("selected")){
            startHourAmPm = "pm";
        }

        if(appointment_end_am_input_button.getStyleClass().contains("selected")){
            endHourAmPm = "am";
        }else if(appointment_end_pm_input_button.getStyleClass().contains("selected")){
            endHourAmPm = "pm";
        }

        if(appointment_start_hour_input_textfield.getText().isEmpty()){
            errorString += "Must select a start hour.\n";
        }

        if(appointment_start_min_input_textfield.getText().isEmpty()){
            errorString += "Must select a start minute.\n";
        }

        if(appointment_end_hour_input_textfield.getText().isEmpty()){
            errorString += "Must select an end hour.\n";
        }

        if(appointment_end_min_input_textfield.getText().isEmpty()){
            errorString += "Must select an end minute.\n";
        }

        if(startHourAmPm.isEmpty()){
            errorString += "AM/PM must be selected for start time.\n";
        }

        if(endHourAmPm.isEmpty()){
            errorString += "AM/PM must be selected for end time.\n";
        }

        if(!startHourString.isEmpty() && !startMinuteString.isEmpty() && !endHourString.isEmpty() && !endHourString.isEmpty() &&   !startHourAmPm.isEmpty() && !endHourAmPm.isEmpty()){
            int startHour = Integer.parseInt(startHourString);
            int startMinute = Integer.parseInt(startMinuteString);
            int endHour = Integer.parseInt(endHourString);
            int endMinute = Integer.parseInt(endMinuteString);


            if(startHourAmPm.equals("pm")&&startHour!=12){
                startHour += 12;
            }

            if(startHour == 12 && startHourAmPm.equals("am")){
                startHour = 0;
            }

            if(endHourAmPm.equals("pm")&&endHour!=12){
                endHour += 12;
            }

            if(endHour == 12 && endHourAmPm.equals("am")){
                endHour = 0;
            }

            //if the error string is not empty, then there are errors
            ZonedDateTime start = ZonedDateTime.of(startDate, LocalTime.of(startHour, startMinute), ZonedDateTime.now().getZone());
            ZonedDateTime end = ZonedDateTime.of(endDate, LocalTime.of(endHour, endMinute), ZonedDateTime.now().getZone());

            //generate a zoneddatetime for 8am est
            ZonedDateTime eightAm = ZonedDateTime.of(start.toLocalDate(), LocalTime.of(8, 0), ZoneId.of("America/New_York"));
            ZonedDateTime tenPm = ZonedDateTime.of(end.toLocalDate(), LocalTime.of(22, 0), ZoneId.of("America/New_York"));

            //add to error string if start is after end
            if (start.isAfter(end)) {
                errorString += "Start time must be before end time\n";
            }

            //if start time is before eightAm or after tenPm, add to error string
            if (start.isBefore(eightAm) || start.isAfter(tenPm)) {
                errorString += "Start time must be between 8am and 10pm EST.\n";
            }

            //if end time is before eightAm or after tenPm, add to error string
            if (end.isBefore(eightAm) || end.isAfter(tenPm)) {
                errorString += "End time must be between 8am and 10pm EST.\n";
            }

            //if the start time and end time are the same time
            if (start.toLocalDateTime().equals(end.toLocalDateTime())) {
                errorString += "Start time and end time cannot be the same.\n";
            }

            //check if start or end time is during any other appointment start and end times
            for (Appointment appointment : allAppointments) {
                //if the appointment id is not 0, then this is an existing appointment
                if (appointmentId != 0) {
                    //if the appointment id is the same as the appointment id in the loop, then skip this appointment
                    if (appointmentId == appointment.getAppointmentId()) {
                        continue;
                    }
                }
                //if the customer id is the same as the customer id in the loop, then check if the start or end time is during any other appointment start and end times
                if (customerId == appointment.getCustomerId()) {
                    //if the start time is during any other appointment start and end times, add to error string
                    if (start.toLocalDateTime().isAfter(appointment.getStart()) && start.toLocalDateTime().isBefore(appointment.getEnd())) {
                        errorString += "Start time is during another appointment.\n";
                    }
                    //if the end time is during any other appointment start and end times, add to error string
                    if (end.toLocalDateTime().isAfter(appointment.getStart()) && end.toLocalDateTime().isBefore(appointment.getEnd())) {
                        errorString += "End time is during another appointment.\n";
                    }

                }

            }

            //if error string is empty then create the appointment
            if (errorString.isEmpty()) {
                //create the appointment
                Appointment appointment = new Appointment(appointmentId, title, description, location, type, contact, start.toLocalDateTime(), end.toLocalDateTime(), customerId, userId);
                return appointment;
            }
        }

        Popups.errorPopup("The following errors must be fixed before the appointment can be saved: \n"+errorString);
        return null;
    }


    public void onStartAmClicked(ActionEvent actionEvent) {
        Button src = (Button) actionEvent.getSource();
        src.getStyleClass().removeIf(s -> s.equals("selected"));
        src.getStyleClass().add("selected");
        appointment_start_pm_input_button.getStyleClass().removeIf(s -> s.equals("selected"));

    }

    public void onStartPmClicked(ActionEvent actionEvent) {
        Button src = (Button) actionEvent.getSource();
        src.getStyleClass().removeIf(s -> s.equals("selected"));
        src.getStyleClass().add("selected");
        appointment_start_am_input_button.getStyleClass().removeIf(s -> s.equals("selected"));
    }

    public void onEndAmClicked(ActionEvent actionEvent) {
        Button src = (Button) actionEvent.getSource();
        src.getStyleClass().removeIf(s -> s.equals("selected"));
        src.getStyleClass().add("selected");
        appointment_end_pm_input_button.getStyleClass().removeIf(s -> s.equals("selected"));
    }

    public void onEndPmClicked(ActionEvent actionEvent) {
        Button src = (Button) actionEvent.getSource();
        src.getStyleClass().removeIf(s -> s.equals("selected"));
        src.getStyleClass().add("selected");
        appointment_end_am_input_button.getStyleClass().removeIf(s -> s.equals("selected"));
    }

    public void onGetScheduleClicked(ActionEvent actionEvent) {
        try{
            String choice = ((String) appointment_customer_report_choicebox.getSelectionModel().getSelectedItem());
            String contactName = choice.split(" - ")[1];

            ObservableList<Appointment> matchingAppointments = AppointmentDAOImpl.getAllAppointments().stream()
                    .filter(appointment -> appointment.getContact().equals(contactName))
                    .sorted(Comparator.comparing(Appointment::getStart))
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
            String message = "";
            System.out.println("matchingAppointments = " + matchingAppointments);


            for (Appointment appointment : matchingAppointments) {
                String formattedStart = appointment.getStart().format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a"));
                String formattedEnd = appointment.getEnd().format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a"));
                message += "Appt ID: " + appointment.getAppointmentId() + " Title: " + appointment.getTitle() + " Start: " + formattedStart + " End: " + formattedEnd + " Customer ID:" + appointment.getCustomerId() + "\n";
            }

            if (message.equals("")) {
                Popups.showInformation("No appointments found for " + contactName);
            } else {
                Popups.showInformation("Appointments for " + contactName + ":\n" + message);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

