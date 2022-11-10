package DAO;

import Model.Appointment;
import Model.Customer;
import Model.Division;
import Utilities.SQLQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class CustomerDAOImpl {

    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();


    public static ObservableList<Customer> getAllCustomers()  {
        try{
            if (allCustomers.isEmpty()) {
                ObservableList<Customer> all_customers = FXCollections.observableArrayList();
                String sql_query = "select c.Customer_ID, c.Customer_Name, c.Address, c.Postal_Code, c.Phone, division, country" +
                        " from customers as c" +
                        " inner join" +
                        " first_level_divisions as division" +
                        " on c.Division_ID = division.Division_ID" +
                        " inner join" +
                        " countries as country" +
                        " on division.Country_ID = country.Country_ID";
                SQLQuery.makeQuery(sql_query);
                ResultSet result_set = SQLQuery.getResult();
                while (result_set.next()) {
                    int result_customer_id = result_set.getInt("Customer_ID");
                    String result_customer_name = result_set.getString("Customer_Name");
                    String result_customer_address = result_set.getString("Address");
                    String result_customer_postal = result_set.getString("Postal_Code");
                    String result_customer_phone = result_set.getString("Phone");
                    String result_customer_division = result_set.getString("Division");
                    String result_customer_country = result_set.getString("Country");
                    all_customers.add(new Customer(result_customer_id, result_customer_name, result_customer_address, result_customer_postal, result_customer_phone, result_customer_division, result_customer_country));
                }
                allCustomers = all_customers;
            } else System.out.println("Customers already loaded");
            return allCustomers;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }


    public static void deleteCustomer(Customer selectedCustomer) {

        //must delete all customers appointments first
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        try {
            allAppointments = AppointmentDAOImpl.getAllAppointments();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        ObservableList<Appointment> toDelete = allAppointments.stream()
                .filter(appointment -> appointment.getCustomerId() == selectedCustomer.getCustomerId())
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        toDelete.forEach(appointment -> {
            try {
                AppointmentDAOImpl.deleteAppointment(appointment);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        String sql_query = "DELETE FROM Customers WHERE Customer_ID = "+selectedCustomer.getCustomerId();
        System.out.println(sql_query);
        SQLQuery.makeQuery(sql_query);
        ResultSet result_set = SQLQuery.getResult();
        allCustomers.remove(selectedCustomer);

    }

    public static void addNewCustomer(Customer customer) {

        try {
            int divisionId = -1;
            for (Division d : DivisionDAOImpl.getAllDivisions()) {
                if (d.getDivisionName().equals(customer.getDivision())) {
                    divisionId = d.getDivisionID();
                }
            }
            String sql_query = "INSERT INTO Customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES ('" + customer.getName() + "', '" + customer.getAddress() + "', '" + customer.getPostalCode() + "', '" + customer.getPhone() + "', " + divisionId + ")";
            SQLQuery.makeQuery(sql_query);
            ResultSet result_set = SQLQuery.getResult();

            String sql_query2 = "SELECT LAST_INSERT_ID();";
            SQLQuery.makeQuery(sql_query2);
            ResultSet result_set2 = SQLQuery.getResult();
            customer.setCustomerId(result_set2.getInt(0));
            allCustomers.add(customer);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public static void modifyCustomer(Customer customer) {
        int divisionId = -1;
        for(Division d : DivisionDAOImpl.getAllDivisions()){
            if(d.getDivisionName().equals(customer.getDivision())) { divisionId = d.getDivisionID(); }
        }
        String sql_query = "UPDATE Customers SET Customer_Name = '"+customer.getName()+"', Address = '"+customer.getAddress()+"', Postal_Code = '"+customer.getPostalCode()+"', Phone = '"+customer.getPhone()+"', Division_ID = "+divisionId+" WHERE Customer_Id = "+customer.getCustomerId()+"";
        SQLQuery.makeQuery(sql_query);
        ResultSet result_set = SQLQuery.getResult();
        allCustomers.replaceAll(cust -> {
            if(cust.getCustomerId() == customer.getCustomerId()) {
                return customer;
            }
            return cust;
        });
    }


}
