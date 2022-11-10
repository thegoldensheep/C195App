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
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class AppointmentDAOImpl {

    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    public static ObservableList<Appointment> getAllAppointments() {
        try{
            if (allAppointments.isEmpty()) {
                ObservableList<Appointment> all_appointments = FXCollections.observableArrayList();
                String sql_query = "SELECT appointments.Appointment_ID, appointments.Title, appointments.Description, appointments.Location, appointments.Type, appointments.Start " +
                        ", appointments.End, appointments.Customer_ID, appointments.User_ID, appointments.Contact_ID, contacts.Contact_Name " +
                        "FROM appointments " +
                        "inner join " +
                        "contacts " +
                        "ON appointments.Contact_ID = contacts.Contact_ID; ";
                SQLQuery.makeQuery(sql_query);
                ResultSet result_set = SQLQuery.getResult();
                while (result_set.next()) {
                    int result_appointment_id = result_set.getInt("Appointment_ID");
                    String result_appointment_title = result_set.getString("Title");
                    String result_appointment_description = result_set.getString("Description");
                    String result_appointment_location = result_set.getString("Location");
                    String result_appointment_type = result_set.getString("Type");
                    //convert the start hour from utc to users time
                    int userTimeZoneOffset = ZoneId.systemDefault().getRules().getOffset(LocalDateTime.now()).getTotalSeconds() / 3600;
                    Date result_appointment_start = result_set.getTimestamp("Start");
                    LocalDateTime utcStart = result_appointment_start.toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime();
                    LocalDateTime userStart = utcStart.plusHours(userTimeZoneOffset);
                    //convert the end hour from utc to users time
                    Date result_appointment_end = result_set.getTimestamp("End");
                    LocalDateTime utcEnd = result_appointment_end.toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime();
                    LocalDateTime userEnd = utcEnd.plusHours(userTimeZoneOffset);

                    int result_customer_id = result_set.getInt("Customer_ID");
                    int result_user_id = result_set.getInt("User_ID");
                    String result_contact = result_set.getString("Contact_Name");
                    Appointment appointment = new Appointment(result_appointment_id, result_appointment_title, result_appointment_description, result_appointment_location, result_appointment_type, result_contact, userStart, userEnd, result_customer_id, result_user_id);
                    all_appointments.add(appointment);
                }
                allAppointments = all_appointments;
            } else System.out.println("Appointments already loaded");
            return allAppointments;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static boolean deleteAppointment(Appointment appointment){
        String sql_query = "DELETE FROM Appointments WHERE Appointment_ID = "+appointment.getAppointmentId();
        SQLQuery.makeQuery(sql_query);
        ResultSet result_set = SQLQuery.getResult();

        allAppointments.remove(appointment);
        return true;
    }

    public static void addAppointment(Appointment newAppointment) {
        try {
            //get the contact id from the contact name
            int contactId = ContactDAOImpl.getContactId(newAppointment.getContact());
            //convert the start hour from users time to utc
            int userTimeZoneOffset = ZoneId.systemDefault().getRules().getOffset(LocalDateTime.now()).getTotalSeconds() / 3600;
            LocalDateTime utcStart = newAppointment.getStart().minusHours(userTimeZoneOffset);
            //convert the end hour from users time to utc
            LocalDateTime utcEnd = newAppointment.getEnd().minusHours(userTimeZoneOffset);

            String sql_query = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES ('" + newAppointment.getTitle() + "', '" + newAppointment.getDescription() + "', '" + newAppointment.getLocation() + "', '" + newAppointment.getType() + "', '" + utcStart + "', '" + utcEnd + "', " + newAppointment.getCustomerId() + ", " + newAppointment.getUserId() + ", " + contactId + ")";
            SQLQuery.makeQuery(sql_query);
            ResultSet result_set = SQLQuery.getResult();

            String sql_query2 = "SELECT last_insert_id() from appointments;";
            SQLQuery.makeQuery(sql_query2);
            ResultSet result_set2 = SQLQuery.getResult();
            result_set2.next();
            newAppointment.setAppointmentId(Integer.parseInt(result_set2.getString(1)));

            allAppointments.add(newAppointment);
            System.out.println(newAppointment.getAppointmentId());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void updateAppointment(Appointment appointment) {
        //get the contact id from the contact name
        int contactId = ContactDAOImpl.getContactId(appointment.getContact());
        //convert the start hour from users time to utc
        int userTimeZoneOffset = ZoneId.systemDefault().getRules().getOffset(LocalDateTime.now()).getTotalSeconds() / 3600;
        LocalDateTime utcStart = appointment.getStart().minusHours(userTimeZoneOffset);
        //convert the end hour from users time to utc
        LocalDateTime utcEnd = appointment.getEnd().minusHours(userTimeZoneOffset);

        String sql_query = "UPDATE appointments SET Title = '" + appointment.getTitle() + "', Description = '" + appointment.getDescription() + "', Location = '" + appointment.getLocation() + "', Type = '" + appointment.getType() + "', Start = '" + utcStart + "', End = '" + utcEnd + "', Customer_ID = " + appointment.getCustomerId() + ", User_ID = " + appointment.getUserId() + ", Contact_ID = " + contactId + " WHERE Appointment_ID = " + appointment.getAppointmentId();
        SQLQuery.makeQuery(sql_query);
        ResultSet result_set = SQLQuery.getResult();
        allAppointments.replaceAll(appt -> {
            if(appt.getAppointmentId() == appointment.getAppointmentId()) {
                return appointment;
            }
            else {
                return appt;
            }
        });
    }
}
