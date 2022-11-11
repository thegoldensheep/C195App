package Controller;

import DAO.*;
import Model.*;
import Utilities.FileIOUtil;
import Utilities.Popups;
import Utilities.ScreenLoader;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Controller for the UserForm.fxml
 */
public class UserFormController implements Initializable {
    @FXML
    private Label upcoming_appointments_label;
    @FXML
    private Label appointment_time_semicolon_label;
    @FXML
    private TextField appointment_start_hour_input_textfield;
    @FXML
    private TextField appointment_start_min_input_textfield;
    @FXML
    private Button appointment_start_am_input_button;
    @FXML
    private Button appointment_start_pm_input_button;
    @FXML
    private TextField appointment_end_hour_input_textfield;
    @FXML
    private Label appointment_time_semicolon_label_2;
    @FXML
    private TextField appointment_end_min_input_textfield;
    @FXML
    private Button appointment_end_am_input_button;
    @FXML
    private Button appointment_end_pm_input_button;
    @FXML
    private Button customer_show_appointments_button;
    @FXML
    private Button appointment_customer_report_button;
    @FXML
    private ChoiceBox appointment_customer_report_choicebox;
    @FXML
    private Label appointment_contact_report_label;
    @FXML
    private TableColumn appointment_start_column_time;
    @FXML
    private TableColumn appointment_end_column_time;
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

    private String AUTO_GENERATED_TEXT = "auto-generated";

    /**
     * Initializes the controller class.
     *
     * @param url the url
     * @param resourceBundle the resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        setupShowAppointmentsButton();
        showAppointmentsWithin15Minutes();
    }

    /**
     * Shows all appointments within 15 minutes of login
     */
    private void showAppointmentsWithin15Minutes() {
        ObservableList<Appointment> appointments = AppointmentDAOImpl.getAllAppointments().stream()
                .filter(a -> a.getStart().isAfter(LocalDateTime.now()) && a.getStart().isBefore(LocalDateTime.now().plusMinutes(15)))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        if (appointments.size() > 0) {
            VBox vbox = new VBox();
            vbox.getChildren().add(new Label("Appointment(s) within 15 minutes:"));
            vbox.getChildren().add(getTableViewFromAppointments(appointments));
            Popups.showInformation(vbox);
            upcoming_appointments_label.setText(""+appointments.size()+" appointment(s) within 15 minutes");
        }else{
            upcoming_appointments_label.setText("No appointments within 15 minutes");
        }
    }

    /**
     * Sets up the show appointments button. Disables it initially.
     */
    private void setupShowAppointmentsButton() {
        customer_show_appointments_button.setDisable(true);
    }

    /**
     * !!!THIS METHOD FILLS THE REQUIREMENT OF CUSTOM REPORT
     * LAMBDA EXPRESSIONS!!! filters customers by customer id
     * Shows appointments for selected customer
     *
     * @param event
     */
    @FXML
    private void onShowAppointmentsButtonClicked(ActionEvent event) {
        Customer selectedCustomer = (Customer) customer_tableview.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null) {
            return;
        }
        ObservableList<Appointment> appts = AppointmentDAOImpl.getAllAppointments().stream()
                .filter(a -> a.getCustomerId() == selectedCustomer.getCustomerId())
                .sorted(Comparator.comparing(Appointment::getStart))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        if (appts.isEmpty()) {
            Popups.showInformation(new Label("No appointments found for "+ selectedCustomer.getName()));
        } else {
            VBox vbox = new VBox(10);
            vbox.getChildren().add(new Label("Appointments for " + selectedCustomer.getName()+":\n"));
            vbox.getChildren().add(getTableViewFromAppointments(appts));
            Popups.showInformation(vbox);
        }
    }

    /**
     * This is a helper method created to quickly create a tableview for report and confirmation popups
     * @param appts an observable list of appointments to show in the tableview
     * @return
     */
    private TableView<Appointment> getTableViewFromAppointments(ObservableList<Appointment> appts) {
        TableView<Appointment> table = new TableView<>();
        table.setEditable(false);

        //set the call values for the tableview. defines a factory for date and times so we can display separetly
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setItems(appts.stream().sorted(Comparator.comparing(Appointment::getStart)).collect(Collectors.toCollection(FXCollections::observableArrayList)));
        TableColumn<Appointment, String> apptIdCol = new TableColumn<>("Appt ID");
        apptIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        TableColumn<Appointment, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        TableColumn<Appointment, String> descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        TableColumn<Appointment, String> locationCol = new TableColumn<>("Location");
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        TableColumn<Appointment, String> contactCol = new TableColumn<>("Contact");
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        TableColumn<Appointment, LocalDateTime> startCol = new TableColumn<>("Start Date");
        startCol.setCellFactory(column -> {
            return new TableCell<Appointment, LocalDateTime>() {
                @Override
                protected void updateItem(LocalDateTime item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {
                        // Format date to MM/DD/YYYY HH:MM AM/PM
                        setText(item.format(DateTimeFormatter.ofPattern("MM/dd/YYYY")));
                    }
                }
            };
        });
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        TableColumn<Appointment, LocalDateTime> startTimeCol = new TableColumn<>("Start Time");
        startTimeCol.setCellFactory(column -> {
            return new TableCell<Appointment, LocalDateTime>() {
                @Override
                protected void updateItem(LocalDateTime item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {
                        // Format date to MM/DD/YYYY HH:MM AM/PM
                        setText(item.format(DateTimeFormatter.ofPattern("hh:mm a")));
                    }
                }
            };
        });
        startTimeCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        TableColumn<Appointment, LocalDateTime> endCol = new TableColumn<>("End Date");
        endCol.setCellFactory(column -> {
            return new TableCell<Appointment, LocalDateTime>() {
                @Override
                protected void updateItem(LocalDateTime item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {
                        // Format date to MM/DD/YYYY HH:MM AM/PM
                        setText(item.format(DateTimeFormatter.ofPattern("MM/dd/YYYY")));
                    }
                }
            };
        });
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        TableColumn<Appointment, LocalDateTime> endColTime = new TableColumn<>("End Time");
        endColTime.setCellFactory(column -> {
            return new TableCell<Appointment, LocalDateTime>() {
                @Override
                protected void updateItem(LocalDateTime item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {
                        // Format date to MM/DD/YYYY HH:MM AM/PM
                        setText(item.format(DateTimeFormatter.ofPattern("hh:mm a")));
                    }
                }
            };
        });
        endColTime.setCellValueFactory(new PropertyValueFactory<>("end"));
        TableColumn<Appointment, String> customerIdCol = new TableColumn<>("Customer ID");
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));

        //disable sorting for created views
        apptIdCol.setSortable(false);
        titleCol.setSortable(false);
        descriptionCol.setSortable(false);
        locationCol.setSortable(false);
        contactCol.setSortable(false);
        startCol.setSortable(false);
        startTimeCol.setSortable(false);
        endColTime.setSortable(false);
        endCol.setSortable(false);
        table.getColumns().addAll(apptIdCol, titleCol, descriptionCol, locationCol, contactCol, startCol, startTimeCol, endCol, endColTime, customerIdCol);
        table.setStyle("-fx-font-size: 12px; -fx-font-weight:normal;");
        return table;
    }

    /**
     * LAMBDA EXPRESSION: To setup selecteditemproperty listeners
     * This method sets up the contacts report choicebox and fills with contacts. It also sets up a listener
     * for the selection change to enable/disable the button and show/hide the label
     */
    private void setupContactsReport() {
        ObservableList<Contact> contacts = FXCollections.observableArrayList();
        contacts = ContactDAOImpl.getAllContacts().stream()
                .sorted(Comparator.comparing(Contact::getContactName))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        for (Contact contact : contacts) {
            appointment_customer_report_choicebox.getItems().add(""+contact.getContactId()+" - "+contact.getContactName());
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

    /**
     * LAMBDA EXPRESSION: To setup selecteditemproperty and textproperty listeners
     * This method sets up the listeners for the hour and minute textfields. It also sets up focus listeners
     * to revert bad values that dont fit "hh:mm" format
     */
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

    /**
     * This generates random contacts, customers, and appointments for testing purposes
     *
     * @param amount the amount of customers to generate. 3x amount = appointents generated and
     *               1/8 amount = contacts generated
     */
    private void generateRandomAll(int amount) {
        for (int i = 0; i < amount/8; i++) {
            addRandomContact();
        }
        
        for (int i = 0; i < amount; i++) {
            addRandomCustomer();
        }
        for (int i = 0; i < amount*5; i++) {
            addRandomAppointment();
        }

    }

    /**
     * This generates a random contact for testing purposes
     */
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

    /**
     * This generates a random full name from a list of my friends
     * @return full name in string form
     */
    private String generateRandomFullName(){
        String firstName = "";
        String lastName = "";
        ObservableList<String> firstNames = FXCollections.observableArrayList();
        ObservableList<String> lastNames = FXCollections.observableArrayList();

        firstNames.addAll("Dillon", "EV", "Michael", "Kana", "Shola", "Josh", "Erin", "Danaka", "Weezy", "Connor", "Travis", "Matthew", "Kristian", "Justin", "Matteo", "Daddi Dre", "Britney", "TK", "Pou", "Vic", "Tichelle", "Kaitlyn", "Ma Sue", "Titan", "Dakota", "Brianna", "Kimberly", "Sage", "Sierra", "Davy", "Devin", "Chianne", "Adam", "Elisha");
        lastNames.addAll("Shepherd", "Cabrinha", "Briggs", "Adesanwo", "Kotrba", "Berkey", "Andrews", "Nguyen-Murphy", "beefx", "Watt", "Banks", "Dixon", "McCray", "Evans", "Trask", "Alserta", "Perry", "Spears", "Potter");
        firstName = firstNames.get((int)(Math.random() * firstNames.size()));
        lastName = lastNames.get((int)(Math.random() * lastNames.size()));
        return ""+firstName + " " + lastName;
    }

    /**
     * This sets up the datepickers initially to be disabled and have an opacity of 1. This opacity is set at 1
     * to make the datepickers' text field not greyed out due text portion being disabled from editing.
     */
    private void setupDatePickers() {
        //disable textbox on start and end datepickers
        appointment_start_input_datepicker.getEditor().setDisable(true);
        appointment_end_input_datepicker.getEditor().setDisable(true);
        appointment_start_input_datepicker.getEditor().setStyle("-fx-opacity: 1;");
        appointment_end_input_datepicker.getEditor().setStyle("-fx-opacity: 1;");
    }

    /**
     * This method sets up the appointment tableview and defines the property value factories and cell value factories
     * for the tableviews. I extract the date and time separately from the appointment for easier display readability
     */
    private void updateAppointmentTableView() {
        ObservableList<Appointment> allAppointments = AppointmentDAOImpl.getAllAppointments();

        //set the appointment property value factories
        appointment_id_column.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appointment_title_column.setCellValueFactory(new PropertyValueFactory<>("title"));
        appointment_description_column.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointment_location_column.setCellValueFactory(new PropertyValueFactory<>("location"));
        appointment_contact_column.setCellValueFactory(new PropertyValueFactory<>("contact"));
        appointment_type_column.setCellValueFactory(new PropertyValueFactory<>("type"));

        //appointment_start_column set cell factory to format date short
        appointment_start_column.setCellValueFactory(new PropertyValueFactory<>("Start Date"));
        appointment_start_column.setCellFactory(column -> {
            return new TableCell<Appointment, LocalDateTime>() {
                @Override
                protected void updateItem(LocalDateTime item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.format(DateTimeFormatter.ofPattern("MM/dd/YYYY")));
                    }
                }
            };
        });
        appointment_start_column_time.setCellValueFactory(new PropertyValueFactory<>("Start"));
        appointment_start_column_time.setCellFactory(column -> {
            return new TableCell<Appointment, LocalDateTime>() {
                @Override
                protected void updateItem(LocalDateTime item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.format(DateTimeFormatter.ofPattern("hh:mm a")));
                    }
                }
            };
        });

        //appointment_end_column set cell factory to format date short
        appointment_end_column.setCellValueFactory(new PropertyValueFactory<>("End"));
        appointment_end_column.setCellFactory(column -> {
            return new TableCell<Appointment, LocalDateTime>() {
                @Override
                protected void updateItem(LocalDateTime item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.format(DateTimeFormatter.ofPattern("MM/dd/YYYY")));
                    }
                }
            };
        });
        appointment_end_column_time.setCellValueFactory(new PropertyValueFactory<>("End"));
        appointment_end_column_time.setCellFactory(column -> {
            return new TableCell<Appointment, LocalDateTime>() {
                @Override
                protected void updateItem(LocalDateTime item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.format(DateTimeFormatter.ofPattern("hh:mm a")));
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
                .sorted(Comparator.comparing(Appointment::getStart))
                .collect(Collectors.toCollection(FXCollections::observableArrayList)));
        appointments_tableview.refresh();
    }

    /**
     * LAMBDA EXPRESSION: to setup selecteditempropety for appointment tableview
     * this method sets up the appointment tableview listeners and sets the modify and delete buttons to be enable
     * /disabled based on if an appointment is selected or not.
     */
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

    /**
     * LAMBDA EXPRESSION: to map country names by country name to distinct list
     * This method resets the country and state values to the default "Country..." and "State/Providence...". Will
     * load from the database if not setup yet.
     */
    private void resetCountryStateValues() {
        if(customer_input_country_combobox.getSelectionModel().getSelectedItem() != null) {
            customer_input_country_combobox.getSelectionModel().selectFirst();
            customer_input_state_combobox.getSelectionModel().selectFirst();
            customer_input_state_combobox.setDisable(true);
        }else {
            ObservableList<String> allCountries = FXCollections.observableArrayList();
            ObservableList<Division> allDivisions = DivisionDAOImpl.getAllDivisions();
            allCountries.add("Country...");
            allCountries.addAll((allDivisions.stream().map(Division::getCountryName)).distinct().toList());
            ObservableList<String> allStates = FXCollections.observableArrayList();
            allStates.add("State/Providence...");
            customer_input_country_combobox.setItems(allCountries);
            customer_input_state_combobox.setItems(allStates);
            customer_input_state_combobox.getSelectionModel().selectFirst();
            customer_input_country_combobox.getSelectionModel().selectFirst();
        }
    }

    /**
     * LAMBDA EXPRESSION: to map contacts, users and customers by name
     * Loads the Add/Modify appointment input pane to the default values
     */
    private void loadAppointmentInputDefaults() {
        ObservableList<String> contacts = FXCollections.observableArrayList();
        contacts.add("Contact...");
        ObservableList<String> users = FXCollections.observableArrayList();
        users.add("User ID...");
        ObservableList<String> customers = FXCollections.observableArrayList();
        customers.add("Customer ID...");

        contacts.addAll(ContactDAOImpl.getAllContacts().stream()
            .sorted(Comparator.comparing(Contact::getContactName))
            .map(Contact::getContactName)
            .collect(Collectors.toCollection(FXCollections::observableArrayList)));
        users.addAll(UserDAOImpl.getAllUsers().stream()
                .sorted(Comparator.comparing(User::getUserName))
                .map(user -> user.getUserId() + " - " + user.getUserName())
                .collect(Collectors.toCollection(FXCollections::observableArrayList)));
        customers.addAll(CustomerDAOImpl.getAllCustomers().stream()
                .sorted(Comparator.comparing(Customer::getName))
                .map(customer -> customer.getCustomerId() + " - " + customer.getName())
                .collect(Collectors.toCollection(FXCollections::observableArrayList)));


        appointment_contact_input_combobox.setItems(contacts);
        appointment_id_input_textfield.setText("");
        appointment_user_id_input_combobox.setItems(users);
        //create a filtered list of all customers sorted by customer id

        appointment_customer_id_input_combobox.setItems(customers);
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

    /**
     * LAMBDA EXPRESSION: to setup selected item property listener
     * This method sets up the customer tableview on selected item change properties to enable/disable the modify,
     * delete, and show appointment buttons.
     */
    private void setupCustomerTableviewListener() {
        customer_tableview.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
            if (customer_tableview.getSelectionModel().getSelectedItem() != null) {
                modify_customer_button.setDisable(false);
                delete_customer_button.setDisable(false);
                customer_show_appointments_button.setDisable(false);
            } else {
                modify_customer_button.setDisable(true);
                delete_customer_button.setDisable(true);
                customer_show_appointments_button.setDisable(true);
            }
        });
    }

    /**
     * LAMBDA EXPRESSION: to setup selected item property listener
     * This method sets up the listeners for change in country and state/providence and sets each
     */
    private void setupCountryComboboxListener() {
        ObservableList<Division> allDivisions = DivisionDAOImpl.getAllDivisions();
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

    /**
     * Update Customer Table View to set the cell value factories. This is done to allow for the tableview to display
     */
    private void updateCustomerTableView() {
        ObservableList<Customer> allCustomers = null;
        try {
            allCustomers = CustomerDAOImpl.getAllCustomers();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    /**
     * This method sets the title label and button for the customer add/modify screen to make clear this is an add
     * and not a modify customer
     */
    private void setupNewCustomer() {
        setCustomerFieldVisibility(true);
        clearCustomerInputForm();
        resetCountryStateValues();
        add_modify_customer_title_label.setText("Add Customer");
        customer_id_textfield.setText(AUTO_GENERATED_TEXT);
        customer_save_button_input.setText("Save New");
        customer_name_textfield.requestFocus();
    }

    /**
     * this is a helper method to enable/disable all fields in the add/modify customer pane
     * @param isVisible whether the fields should be visible or not (disabled)
     */
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
            add_modify_customer_pane.setStyle("-fx-border-color: #33f; -fx-border-style: solid solid solid solid;");
        }else{
            //set the add_modify_appointment_pane border to #ccc
            add_modify_customer_pane.setStyle("-fx-border-color: #ccc; -fx-border-style: solid solid solid none;");
        }

    }

    /**
     * Clears the customer input form to default values
     */
    private void clearCustomerInputForm() {
        customer_name_textfield.clear();
        customer_id_textfield.clear();
        customer_address_textfield.clear();
        customer_postal_textfield.clear();
        customer_phone_textfield.clear();
        resetCountryStateValues();
    }

    /**
     * Setup the customer add/modify to make clear user is modifying this customer
     * @param customer to be updated
     */
    private void setupModifyCustomer(Customer customer) {
        setCustomerFieldVisibility(true);
        resetCountryStateValues();
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

    /**
     * This is the method called when the save customer button is clicked. This handles both add and modify scenarios.
     * @param actionEvent The event that triggered this method
     */
    @FXML
    private void customerSaveButtonClicked(ActionEvent actionEvent) {
        if (customerAddFormValid()) {
            Customer customer = null;
            if (customer_id_textfield.getText().equals(AUTO_GENERATED_TEXT)) {
                //New customer
                customer = new Customer(-1, customer_name_textfield.getText(),
                        customer_address_textfield.getText(), customer_postal_textfield.getText(),
                        customer_phone_textfield.getText(), customer_input_state_combobox.getSelectionModel().getSelectedItem().toString(), customer_input_country_combobox.getSelectionModel().getSelectedItem().toString());
                CustomerDAOImpl.addNewCustomer(customer);
                clearCustomerInputForm();
                setCustomerFieldVisibility(false);
                updateCustomerTableView();
            } else {
                //Modify Customer
                customer = new Customer(Integer.parseInt(customer_id_textfield.getText()), customer_name_textfield.getText(),
                        customer_address_textfield.getText(), customer_postal_textfield.getText(),
                        customer_phone_textfield.getText(), customer_input_state_combobox.getSelectionModel().getSelectedItem().toString(), customer_input_country_combobox.getSelectionModel().getSelectedItem().toString());
                CustomerDAOImpl.modifyCustomer(customer);
                clearCustomerInputForm();
                setCustomerFieldVisibility(false);
                updateCustomerTableView();
            }
            updateAppointmentInputCustomerIdComboBox();
        }
    }

    /**
     * Set the default for the customer id combobox in the add/modify appointment pane
     */
    private void updateAppointmentInputCustomerIdComboBox() {
        ObservableList<Customer> allCustomers = null;
        try {
            allCustomers = CustomerDAOImpl.getAllCustomers();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    /**
     * This method popups any errors and returns false if the customer form is invalid
     * @return
     */
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

    /**
     * This method is called when the cancel button is clicked on the customer input form
     * @param actionEvent The event that triggered this method
     *
     * @param actionEvent The event that triggered this method
     */
    @FXML
    private void customerCancelButtonClicked(ActionEvent actionEvent) {
        clearCustomerInputForm();
        resetCountryStateValues();
        setCustomerFieldVisibility(false);
    }

    /**
     * This method is called when modify customer button is clicked
     * @param actionEvent The event that triggered this method
     */
    @FXML
    private void modifyCustomerButtonClicked(ActionEvent actionEvent) {
        setupModifyCustomer((Customer) customer_tableview.getSelectionModel().getSelectedItem());
    }

    /**
     * This method is called when the add customer button is clicked
     * @param actionEvent The event that triggered this method
     */
    @FXML
    private void addCustomerButtonClicked(ActionEvent actionEvent) {
        setupNewCustomer();
    }

    /**
     * LAMBDA EXPRESSION: filters appointments by customerid == selectedcustomer id
     * This method is called when the delete customer button is clicked. It will show a popup asking to confirm with
     * any appointments associated with the customer listed for deletion
     * @param actionEvent The event that triggered this method
     */
    @FXML
    private void deleteCustomerButtonClicked(ActionEvent actionEvent) {
        ObservableList<Appointment> allAppointments = null;
        try {
            allAppointments = AppointmentDAOImpl.getAllAppointments();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Customer selectedCustomer = (Customer) customer_tableview.getSelectionModel().getSelectedItem();
        ObservableList<Appointment> appointmentsWithCustomer = allAppointments.filtered(appointment -> appointment.getCustomerId() == selectedCustomer.getCustomerId())
                .stream().sorted(Comparator.comparing(Appointment::getStart))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        String confirmationMessage = "Are you sure you want to delete " + selectedCustomer.getName() + "? (ID: "+selectedCustomer.getCustomerId()+")\n";
        Node node = new Label(confirmationMessage);
        ((Label) node).setStyle("-fx-font-size: 14px;-fx-font-weight: bold;-fx-text-fill:red;");
        if (appointmentsWithCustomer.size() > 0) {
            VBox vbox = new VBox(10);
            vbox.getChildren().add(node);
            vbox.getChildren().add(new Label("This customer has the following appointments which will be deleted:"));
            vbox.getChildren().add(getTableViewFromAppointments(appointmentsWithCustomer));
            node = vbox;
        }
        if (Popups.confirmAction(node)) {

            CustomerDAOImpl.deleteCustomer(selectedCustomer);
            clearCustomerInputForm();
            setCustomerFieldVisibility(false);
            updateCustomerTableView();
            refreshAppointmentsTableview();
            updateAppointmentInputCustomerIdComboBox();
        }
    }

    /**
     * This method is called to refresh the appointments tableview with the current appointments
     */
    private void refreshAppointmentsTableview() {
        if(appointment_current_month_radio.isSelected()){
            updateAppointmentTableviewFilterMonthly();
        }else if(appointment_current_week_radio.isSelected()){
            updateAppointmentTableviewFilterWeekly();
        }else if(appointment_all_radio.isSelected()){
            updateAppointmentTableviewFilterAll();
        }
        appointments_tableview.refresh();
    }

    /**
     * This method is called to add a random customer to the database. It provides random street names, street types, and
     * a random street number
     */
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

        CustomerDAOImpl.addNewCustomer(new Customer(0, fullName, address, postalCode, phoneNumber, division.getDivisionName(), division.getCountryName()));

    }

    /**
     * This method creates a random appointment by generating a random customer, user, title, type, location, and description
     */
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
        newStart = newStart.plusDays((int)(Math.random() * 250));
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

    /**
     * This method is called when the add appointment is clicked. It makes the add appointment pane not disabled
     * and sets the id text to auto generated, and sets some buttons that make it clear we are adding a new customer
     * @param actionEvent the event that triggered this method
     */
    @FXML
    private void onAddAppointmentClicked(ActionEvent actionEvent) {
        setAppointmentFieldVisibility(true);
        loadAppointmentInputDefaults();
        appointment_id_input_textfield.setText("auto-generated");
        add_modify_appointment_title.setText("Add Appointment");
        appointment_save_input_button.setText("Save Appointment");
        appointment_title_input_textfield.requestFocus();
    }

    /**
     * LAMBDA EXPRESSION: appointment customer id filter
     * This method is called when the modify appointment is clicked. It makes the add appointment pane not disabled
     * and sets the fields to the selected appointment's values. Makes it clear we are modifying appointment by changing
     * text for buttons and titles
     * @param actionEvent the event that triggered this method
     */
    @FXML
    private void onModifyAppointmentClicked(ActionEvent actionEvent) {
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

            //format start and end minute and hour textfields
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
            String customerString = selectedAppointment.getCustomerId() + " - " + CustomerDAOImpl.getAllCustomers().stream()
                    .filter(customer -> customer.getCustomerId() == selectedAppointment.getCustomerId())
                    .findFirst().get().getName();
            appointment_customer_id_input_combobox.getSelectionModel().select(customerString);

            //make a string with user id followed by hyphen then user name
            String userString = selectedAppointment.getUserId() + " - " + UserDAOImpl.getUserById(selectedAppointment.getUserId()).getUserName();
            appointment_user_id_input_combobox.getSelectionModel().select(userString);

            appointment_title_input_textfield.requestFocus();

        }
    }

    /**
     * This method sets the visibility (disabled/enabled in this context) to the provided boolean. It sets the style
     * to blue border if the fields are enabled.
     * @param isVisible
     */
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

    /**
     * This method is called when the cancel appointment button is clicked. It loads the add/modify appointment pain
     * back to its default value and disables it.
     * @param actionEvent the event that triggered this method
     */
    @FXML
    private void onCancelAppointmentClicked(ActionEvent actionEvent) {
        loadAppointmentInputDefaults();
        setAppointmentFieldVisibility(false);
    }

    /**
     * This method is called when current week radio is clicked. It updates the current appointments to weekly view
     * @param actionEvent
     */
    @FXML
    private void onCurrentWeekRadioSelected(ActionEvent actionEvent) {
        //set the tableview to show appointments only from the current week
        updateAppointmentTableviewFilterWeekly();


    }

    /**
     * This is the method called when the radio button is clicked
     */
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

    /**
     * This is the handler for the month radio selected event
     * @param actionEvent the event that triggered this method
     */
    @FXML
    private void onCurrentMonthRadioSelected(ActionEvent actionEvent) {
        updateAppointmentTableviewFilterMonthly();
    }

    /**
     * This is the method called when the month radio is clicked
     */
    private void updateAppointmentTableviewFilterMonthly() {
        //get the date of the first day of the current month at midnight
        LocalDateTime firstDayOfMonth = LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth()).withHour(0).withMinute(0).withSecond(0).withNano(0);

        //get all appointments from allAppointments that are between firstDayOfMonth and lastDayOfMonth
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
    }

    /**
     * This is the handler for the all radio selected event
     * @param actionEvent the event that triggered this method
     */
    @FXML
    private void onAllRadioSelected(ActionEvent actionEvent) {
        updateAppointmentTableviewFilterAll();
    }

    /**
     * This is the method called when the all radio is clicked
     */
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

    /**
     * This is the method called when the delete appointment button is clicked. It will first validate all fields
     * and then saves validation of date/time/conflicts for last.
     * @param actionEvent the event that triggered this method
     */
    @FXML
    private void onDeleteAppointmentClicked(ActionEvent actionEvent) {
        Appointment selectedAppointment = (Appointment) appointments_tableview.getSelectionModel().getSelectedItem();
        VBox vbox = new VBox();
        vbox.getChildren().add(new Label("Are you sure you want to delete this appointment?"));
        ObservableList<Appointment> selAptList = FXCollections.observableArrayList();
        selAptList.add(selectedAppointment);
        vbox.getChildren().add(getTableViewFromAppointments(selAptList));
        if(Popups.confirmAction(vbox)){

            try {
                if (appointment_id_input_textfield.getText() != "auto-generated" && selectedAppointment.getAppointmentId() == Integer.parseInt(appointment_id_input_textfield.getText())) {
                    loadAppointmentInputDefaults();
                    setAppointmentFieldVisibility(false);
                }
            }catch(NumberFormatException e){
                //this is ok, this is just a check
            }



            AppointmentDAOImpl.deleteAppointment(selectedAppointment);
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

    /**
     * This is the handler for the save appointment button. It handles both new and existing appointments
     * @param actionEvent the event that triggered this method
     */
    @FXML
    private void saveAppointmentButtonClicked(ActionEvent actionEvent) {
        Appointment appointment = appointmentInputValid();
        if(appointment!=null){
            if(appointment.getAppointmentId()==-1 || appointment.getAppointmentId()==0){
                //if the appointment id is 0, then this is a new appointment
                AppointmentDAOImpl.addAppointment(appointment);
            }else{
                //if the appointment id is not 0, then this is an existing appointment
                AppointmentDAOImpl.updateAppointment(appointment);
            }
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

    /**
     * This method could be refactored. But as it works now it returns an appointment if valid and null if not. It will
     * show any errors to the user, including any conflicting appointments in a tableview
     * @return created appointment or null if invalid
     */
    private Appointment appointmentInputValid(){
        ObservableList<Appointment> allAppointments = null;
        try {
            allAppointments = AppointmentDAOImpl.getAllAppointments();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

        ObservableList<Appointment> conflictingAppointments = FXCollections.observableArrayList();


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
                if(appointment.getAppointmentId() == appointmentId) continue;
                ZonedDateTime appointmentStart = appointment.getStart().atZone(ZonedDateTime.now().getZone());
                ZonedDateTime appointmentEnd = appointment.getEnd().atZone(ZonedDateTime.now().getZone());
                if(end.isAfter(appointmentStart) && (end.isBefore(appointmentEnd)||end.isEqual(appointmentEnd))
                        || start.isAfter(appointmentStart) && (start.isBefore(appointmentEnd)||start.isEqual(appointmentEnd))
                        || start.isBefore(appointmentStart) && (end.isAfter(appointmentEnd)||end.isEqual(appointmentEnd))){
                    conflictingAppointments.add(appointment);
                }
            }

            if(!conflictingAppointments.isEmpty()){
                errorString += "Appointment conflicts with the following appointments:\n";
            }



            //if error string is empty then create the appointment
            if (errorString.isEmpty()) {
                //create the appointment
                Appointment appointment = new Appointment(appointmentId, title, description, location, type, contact, start.toLocalDateTime(), end.toLocalDateTime(), customerId, userId);
                return appointment;
            }
        }

        VBox vbox = new VBox(10);
        vbox.getChildren().add(new Label(errorString));
        vbox.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        if(!conflictingAppointments.isEmpty()){
            vbox.getChildren().add(getTableViewFromAppointments(conflictingAppointments));
        }
        Popups.showInformation(vbox);
        return null;
    }


    /**
     * This is a handler for the start am button being clicked
     * @param actionEvent the action event
     */
    @FXML
    private void onStartAmClicked(ActionEvent actionEvent) {
        Button src = (Button) actionEvent.getSource();
        src.getStyleClass().removeIf(s -> s.equals("selected"));
        src.getStyleClass().add("selected");
        appointment_start_pm_input_button.getStyleClass().removeIf(s -> s.equals("selected"));

    }

    /**
     * This is a handler for the start pm button being clicked
     * @param actionEvent the action event that triggered this handler
     */
    @FXML
    private void onStartPmClicked(ActionEvent actionEvent) {
        Button src = (Button) actionEvent.getSource();
        src.getStyleClass().removeIf(s -> s.equals("selected"));
        src.getStyleClass().add("selected");
        appointment_start_am_input_button.getStyleClass().removeIf(s -> s.equals("selected"));
    }

    /**
     * This is a handler for the end am button being clicked
     * @param actionEvent the action event that triggered this handler
     *
     * @param actionEvent the action event that triggered this handler
     */
    @FXML
    private void onEndAmClicked(ActionEvent actionEvent) {
        Button src = (Button) actionEvent.getSource();
        src.getStyleClass().removeIf(s -> s.equals("selected"));
        src.getStyleClass().add("selected");
        appointment_end_pm_input_button.getStyleClass().removeIf(s -> s.equals("selected"));
    }

    /**
     * This is a handler for the end pm button being clicked
     * @param actionEvent the action event that triggered this handler

     */
    @FXML
    private void onEndPmClicked(ActionEvent actionEvent) {
        Button src = (Button) actionEvent.getSource();
        src.getStyleClass().removeIf(s -> s.equals("selected"));
        src.getStyleClass().add("selected");
        appointment_end_am_input_button.getStyleClass().removeIf(s -> s.equals("selected"));
    }

    /**
     * LAMBDA EXPRESSION: to get all matching appointments utilizing a filter on a stream
     * This is a handler for the save button being clicked
     * @param actionEvent the action event that triggered this handler
     */
    @FXML
    private void onGetScheduleClicked(ActionEvent actionEvent) {
        String choice = ((String) appointment_customer_report_choicebox.getSelectionModel().getSelectedItem());
        String contactName = choice.split(" - ")[1];

        ObservableList<Appointment> matchingAppointments = AppointmentDAOImpl.getAllAppointments().stream()
                .filter(appointment -> appointment.getContact().equals(contactName))
                .sorted(Comparator.comparing(Appointment::getStart))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        if (matchingAppointments.isEmpty()) {
            Popups.showInformation(new Label("No appointments on the schedule for contact " + contactName));
        } else {
            VBox vbox = new VBox(10);
            vbox.getChildren().add(new Label("Schedule for " + contactName + ":\n"));
            vbox.getChildren().add(getTableViewFromAppointments(matchingAppointments));
            Popups.showInformation(vbox);
        }
    }

    /**
     * This is a handler for the save button being clicked. It will generate two lists for count by month and
     * count by type using a hashmap and then display the results in a popup
     * @param actionEvent the action event that triggered this handler
     */
    @FXML
    private void onTypeMonthReportButtonClicked(ActionEvent actionEvent) {

        ObservableList<Appointment> appts = AppointmentDAOImpl.getAllAppointments().stream()
                .sorted(Comparator.comparing(Appointment::getStart))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        Map<LocalDate, Integer> monthsMap = new HashMap<LocalDate, Integer>();
        Map<String, Integer> typeMap = new HashMap<String, Integer>();

        for (Appointment appointment : appts) {
            LocalDate month = appointment.getStart().toLocalDate().withDayOfMonth(1);
            if (monthsMap.containsKey(month)) {
                monthsMap.put(month, monthsMap.get(month) + 1);
            } else {
                monthsMap.put(month, 1);
            }

            String type = appointment.getType();
            if (typeMap.containsKey(type)) {
                typeMap.put(type, typeMap.get(type) + 1);
            } else {
                typeMap.put(type, 1);
            }
        }


        //create a tableview from the map
        TableView<Map.Entry<LocalDate, Integer>> tableView = new TableView<>();
        tableView.setStyle("-fx-font-size: 12px; -fx-font-weight: normal;");
        TableColumn<Map.Entry<LocalDate, Integer>, String> monthColumn = new TableColumn<>("Month");
        TableColumn<Map.Entry<LocalDate, Integer>, Integer> countColumn = new TableColumn<>("Count");
        monthColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getKey().format(DateTimeFormatter.ofPattern("MMMM yyyy"))));
        countColumn.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getValue()).asObject());
        tableView.getColumns().addAll(monthColumn, countColumn);
        tableView.setItems(FXCollections.observableArrayList(monthsMap.entrySet()));
        HBox hbox = new HBox(20);


        VBox vbox = new VBox(10);
        vbox.getChildren().add(new Label("# of Appointments by Month:\n"));
        vbox.getChildren().add(tableView);
        hbox.getChildren().add(vbox);

        //create a tableview from the type map
        VBox vbox2 = new VBox(10);
        vbox2.getChildren().add(new Label("# of Appointments by Type:\n"));
        TableView<Map.Entry<String, Integer>> typeTableView = new TableView<>();
        typeTableView.setStyle("-fx-font-size: 12px; -fx-font-weight: normal;");
        TableColumn<Map.Entry<String, Integer>, String> typeColumn = new TableColumn<>("Type");
        TableColumn<Map.Entry<String, Integer>, Integer> typeCountColumn = new TableColumn<>("Count");
        typeColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getKey()));
        typeCountColumn.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue().getValue()).asObject());
        typeTableView.getColumns().addAll(typeColumn, typeCountColumn);
        typeTableView.setItems(FXCollections.observableArrayList(typeMap.entrySet()));
        vbox2.getChildren().add(typeTableView);
        hbox.getChildren().add(vbox2);

        Popups.showInformation(hbox);

    }

    /**
     * This is a handler for the logout button being clicked. Logs user out after confirmation.
     * @param actionEvent the action event that triggered this handler
     */
    @FXML
    private void logoutClicked(ActionEvent actionEvent) {
        if (Popups.confirmAction(new Label("Are you sure you want to logout?"))) {
            ScreenLoader.loadScreen(this, actionEvent, "/View/login_form.fxml");
            FileIOUtil.writeToFile("login_activity.txt", ""+User.getCurrentlyLoggedInUser()+" logged out at "+ZonedDateTime.now()+"\n",false);
            User.setCurrentlyLoggedInUser("");
        }
    }
}

