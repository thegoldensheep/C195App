package DAO;

import Model.Customer;
import Model.User;
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



}
