package DAO;

import Model.User;
import Utilities.SQLQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl {
    private static ObservableList<User> allUsers = FXCollections.observableArrayList();

    public static User getUser(String user_name) {
        try{
        if(allUsers.isEmpty()){

                String sql_query = "select * FROM users WHERE User_Name = '" + user_name + "'";
                SQLQuery.makeQuery(sql_query);
                ResultSet results = SQLQuery.getResult();
                while (results.next()) {
                    int result_user_id = results.getInt("User_ID");
                    String result_user_name = results.getString("User_Name");
                    String result_password = results.getString("Password");
                    return new User(result_user_id, result_user_name, result_password);
                }
            }else{
                for (User user : allUsers) {
                    if (user.getUserName().equals(user_name)) {
                        return user;
                    }
                }
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static ObservableList<User> getAllUsers()  {
        try{
            if (allUsers.isEmpty()) {
                ObservableList<User> all_users = FXCollections.observableArrayList();
                String sql_query = "SELECT * FROM users";
                SQLQuery.makeQuery(sql_query);
                ResultSet result_set = SQLQuery.getResult();
                while (result_set.next()) {
                    int result_user_id = result_set.getInt("User_ID");
                    String result_user_name = result_set.getString("User_Name");
                    String result_password = result_set.getString("Password");
                    all_users.add(new User(result_user_id, result_user_name, result_password));
                }
                allUsers = all_users;
            } else System.out.println("Users already loaded");
            return allUsers;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public static User getUserById(int userId) {
        if(allUsers.isEmpty()){
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
        }else{
            for(User user : allUsers){
                if(user.getUserId() == userId){
                    return user;
                }
            }
            return null;
        }
    }

    public static void addUser(User user){
        try{
            String sql_query = "INSERT INTO users (User_Name, Password) VALUES ('" + user.getUserName() + "', '" + user.getPassword() + "')";
            SQLQuery.makeQuery(sql_query);

            String sql_query2 = "SELECT last_insert_id() from users;";
            SQLQuery.makeQuery(sql_query2);
            ResultSet result_set2 = SQLQuery.getResult();
            result_set2.next();
            user.setUserId(Integer.parseInt(result_set2.getString(1)));
            allUsers.add(user);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
