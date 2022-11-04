package DAO;

import Model.Appointment;
import Model.Customer;
import Utilities.SQLQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

public class AppointmentDAOImpl {

    public static ObservableList<Appointment> getAllAppointments() throws SQLException, Exception {
        ObservableList<Appointment> all_appointments = FXCollections.observableArrayList();
        String sql_query = "SELECT appointments.Appointment_ID, appointments.Title, appointments.Description, appointments.Location, appointments.Type, appointments.Start " +
                ", appointments.End, appointments.Customer_ID, appointments.User_ID, contacts.Contact_Name " +
                "FROM appointments " +
                "inner join " +
                "contacts " +
                "ON appointments.Contact_ID = contacts.Contact_ID; ";
        SQLQuery.makeQuery(sql_query);
        ResultSet result_set = SQLQuery.getResult();
        while(result_set.next()){
            int result_appointment_id = result_set.getInt("Appointment_ID");
            String result_appointment_title = result_set.getString("Title");
            String result_appointment_description = result_set.getString("Description");
            String result_appointment_location = result_set.getString("Location");
            String result_appointment_type = result_set.getString("Type");
            LocalDateTime result_appointment_start = result_set.getTimestamp("Start").toLocalDateTime();
            LocalDateTime result_appointment_end = result_set.getTimestamp("End").toLocalDateTime();
            int result_customer_id = result_set.getInt("Customer_ID");
            int result_user_id = result_set.getInt("User_ID");
            String result_contact = result_set.getString("Contact_Name");
            Appointment appointment = new Appointment(result_appointment_id, result_appointment_title, result_appointment_description, result_appointment_location, result_appointment_type, result_contact, result_appointment_start, result_appointment_end, result_customer_id, result_user_id);
            all_appointments.add(appointment);
        }
        return all_appointments;
    }

    public static boolean deleteAppointment(Appointment appointment){
        String sql_query = "DELETE FROM Appointments WHERE Appointment_ID = "+appointment.getAppointmentId();
        SQLQuery.makeQuery(sql_query);
        ResultSet result_set = SQLQuery.getResult();
        return true;
    }

    public static void addAppointment(Appointment newAppointment) {
        //get the contact id from the contact name
        int contactId = ContactDAOImpl.getContactId(newAppointment.getContact());
        String sql_query = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES ('" + newAppointment.getTitle() + "', '" + newAppointment.getDescription() + "', '" + newAppointment.getLocation() + "', '" + newAppointment.getType() + "', '" + newAppointment.getStart() + "', '" + newAppointment.getEnd() + "', " + newAppointment.getCustomerId() + ", " + newAppointment.getUserId() + ", " + contactId + ")";
        SQLQuery.makeQuery(sql_query);
        ResultSet result_set = SQLQuery.getResult();
    }
}
