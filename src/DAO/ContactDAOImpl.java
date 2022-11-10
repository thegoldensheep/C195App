package DAO;

import Model.Contact;
import Utilities.SQLQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactDAOImpl {
    private static ObservableList<Contact> allContacts = FXCollections.observableArrayList();

    public static ObservableList<Contact> getAllContacts() {
        try{
            if (allContacts.isEmpty()) {
                ObservableList<Contact> all_contacts = FXCollections.observableArrayList();
                String sql_query = "SELECT * FROM contacts";
                SQLQuery.makeQuery(sql_query);
                ResultSet result_set = SQLQuery.getResult();
                while (result_set.next()) {
                    int result_contact_id = result_set.getInt("Contact_ID");
                    String result_contact_name = result_set.getString("Contact_Name");
                    String result_email = result_set.getString("Email");
                    Contact contact = new Contact(result_contact_id, result_contact_name, result_email);
                    all_contacts.add(contact);
                }
                allContacts = all_contacts;
            } else System.out.println("Contacts already loaded");
            return allContacts;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static int getContactId(String contact) {
        if(allContacts.isEmpty()){
            int contact_id = 0;
            try {
                String sql_query = "SELECT Contact_ID FROM contacts WHERE Contact_Name = '" + contact + "'";
                SQLQuery.makeQuery(sql_query);
                ResultSet result_set = SQLQuery.getResult();
                while (result_set.next()) {
                    contact_id = result_set.getInt("Contact_ID");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return contact_id;
        }else{
            for(Contact c : allContacts){
                if(c.getContactName().equals(contact)){
                    return c.getContactId();
                }
            }
            return 0;
        }
    }

    public static void addContact(Contact contact){
        try {
            String sql_query = "INSERT INTO contacts (Contact_Name, Email) VALUES ('" + contact.getContactName() + "', '" + contact.getEmail() + "')";
            SQLQuery.makeQuery(sql_query);
            ResultSet result_set = SQLQuery.getResult();

            String sql_query2 = "SELECT last_insert_id() from contacts;";
            SQLQuery.makeQuery(sql_query2);
            ResultSet result_set2 = SQLQuery.getResult();
            result_set2.next();
            contact.setContactId(Integer.parseInt(result_set2.getString(1)));

            allContacts.add(contact);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
