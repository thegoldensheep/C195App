package DAO;

import Model.User;
import Utilities.SQLQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl {
    public static User getUser(String user_name) throws SQLException, Exception{
        String sql_query = "select * FROM users WHERE User_Name = '"+ user_name + "'";
        SQLQuery.makeQuery(sql_query);
        ResultSet results = SQLQuery.getResult();
        while(results.next()){
            int result_user_id = results.getInt("User_ID");
            String result_user_name = results.getString("User_Name");
            String result_password = results.getString("Password");
            return new User(result_user_id, result_user_name, result_password);
        }
        return null;
    }

    public static ObservableList<User> getAllUsers() throws SQLException, Exception {
        ObservableList<User> all_users = FXCollections.observableArrayList();
        String sql_query = "SELECT * FROM users";
        SQLQuery.makeQuery(sql_query);
        ResultSet result_set = SQLQuery.getResult();
        while(result_set.next()){
            int result_user_id = result_set.getInt("User_ID");
            String result_user_name = result_set.getString("User_Name");
            String result_password = result_set.getString("Password");
            all_users.add(new User(result_user_id, result_user_name, result_password));
        }
        return all_users;
    }


    public static User getUserById(int userId) {
        User user = null;
        try {
            String sql_query = "SELECT * FROM users WHERE User_ID = " + userId;
            SQLQuery.makeQuery(sql_query);
            ResultSet result_set = SQLQuery.getResult();
            while (result_set.next()) {
                int result_user_id = result_set.getInt("User_ID");
                String result_user_name = result_set.getString("User_Name");
                String result_password = result_set.getString("Password");
                user = new User(result_user_id, result_user_name, result_password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
