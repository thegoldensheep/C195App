package DAO;

import Model.Contact;
import Utilities.SQLQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactDAOImpl {
    public static ObservableList<Contact> getAllContacts() throws SQLException, Exception {
        ObservableList<Contact> all_contacts = FXCollections.observableArrayList();
        String sql_query = "SELECT * FROM contacts";
        SQLQuery.makeQuery(sql_query);
        ResultSet result_set = SQLQuery.getResult();
        while(result_set.next()){
            int result_contact_id = result_set.getInt("Contact_ID");
            String result_contact_name = result_set.getString("Contact_Name");
            String result_email = result_set.getString("Email");
            Contact contact = new Contact(result_contact_id, result_contact_name, result_email);
            all_contacts.add(contact);
        }
        return all_contacts;
    }
}
