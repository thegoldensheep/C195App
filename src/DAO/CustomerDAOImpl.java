package DAO;

import Model.Appointment;
import Model.Customer;
import Utilities.SQLQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAOImpl {

    public static ObservableList<Customer> getAllCustomers() throws SQLException, Exception {
        ObservableList<Customer> all_customers = FXCollections.observableArrayList();
        String sql_query = "SELECT * FROM customers";
        SQLQuery.makeQuery(sql_query);
        ResultSet result_set = SQLQuery.getResult();
        while(result_set.next()){
            int result_customer_id = result_set.getInt("Customer_ID");
            String result_customer_name = result_set.getString("Customer_Name");
            String result_customer_address = result_set.getString("Address");
            String result_customer_postal = result_set.getString("Postal_Code");
            String result_customer_phone = result_set.getString("Phone");
            all_customers.add(new Customer(result_customer_id, result_customer_name, result_customer_address, result_customer_postal, result_customer_phone));
        }
        return all_customers;
    }


    public static void deleteCustomer(Customer selectedCustomer) {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        try {
            allAppointments = AppointmentDAOImpl.getAllAppointments();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        for(Appointment apt : allAppointments){
            if(apt.getCustomerId() == selectedCustomer.getCustomerId()){
                AppointmentDAOImpl.deleteAppointment(apt);
            }
        }

        String sql_query = "DELETE FROM Customers WHERE Customer_ID = "+selectedCustomer.getCustomerId();
        System.out.println(sql_query);
        SQLQuery.makeQuery(sql_query);
        ResultSet result_set = SQLQuery.getResult();

    }

    public static void addNewCustomer(Customer customer) throws SQLException {
        String sql_query = "INSERT INTO Customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES ('"+customer.getName()+"', '"+customer.getAddress()+"', '"+customer.getPostalCode()+"', '"+customer.getPhone()+"', 1)";
        SQLQuery.makeQuery(sql_query);
        System.out.println(sql_query);
        ResultSet result_set = SQLQuery.getResult();

    }

    public static void modifyCustomer(Customer customer) {
        String sql_query = "UPDATE Customers SET Customer_Name = '"+customer.getName()+"', Address = '"+customer.getAddress()+"', Postal_Code = '"+customer.getPostalCode()+"', Phone = '"+customer.getPhone()+"' WHERE Customer_Id = "+customer.getCustomerId()+"";
        SQLQuery.makeQuery(sql_query);
        System.out.println(sql_query);
        ResultSet result_set = SQLQuery.getResult();
    }
}
