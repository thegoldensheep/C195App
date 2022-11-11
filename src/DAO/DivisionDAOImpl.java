package DAO;

import Model.Customer;
import Model.Division;
import Utilities.SQLQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DivisionDAOImpl class that controls the access to the Division objects
 * @author Dillon Shepherd dshep80@wgu.edu
 */
public class DivisionDAOImpl {
    private static ObservableList<Division> allDivisions = FXCollections.observableArrayList();

    /**
     * Gets all divisions from database or stored
     * @return all divisions observable list
     */
    public static ObservableList<Division> getAllDivisions(){

        if(allDivisions.isEmpty()){
            String sql_query = "select first_level_divisions.Division_ID, first_level_divisions.Country_ID, first_level_divisions.Division, countries.Country " +
                    "from first_level_divisions as first_level_divisions " +
                    "inner join " +
                    "countries as countries " +
                    "on first_level_divisions.Country_ID = countries.Country_ID; ";
            SQLQuery.makeQuery(sql_query);
            ResultSet result_set = SQLQuery.getResult();
            try {
                while (result_set.next()) {
                    int divisionId = result_set.getInt("Division_ID");
                    int countryId = result_set.getInt("Country_ID");
                    String division = result_set.getString("Division");
                    String countryName = result_set.getString("Country");
                    allDivisions.add(new Division(divisionId, countryId, division, countryName));

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Divisions already loaded");
        }
        return allDivisions;
    }


}
