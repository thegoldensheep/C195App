package Utilities;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Locale;

/**
 * SQLQuery class
 * Contains static methods to query the database
 * @author largely provided by Mark Kinkhead, with some utilization of his DB Connection class as well.
 */
public abstract class SQLQuery {
    private static String sql_query;
    private static Statement sql_statement;
    private static ResultSet sql_results_set;

    /**
     * Makes a query to the database
     * @param query the sql query
     */
    public static void makeQuery(String query){
        System.out.println(query);
        sql_query = query;
        try {
            sql_statement = JBDC.getConnection().createStatement();
            String query_lwr = sql_query.toLowerCase();
            if(query_lwr.startsWith("select")) { sql_results_set = sql_statement.executeQuery(sql_query); }

            if(query_lwr.startsWith("update")||query_lwr.startsWith("insert")||query_lwr.startsWith("delete")) {
                sql_statement.executeUpdate(sql_query);
            }
        }catch(Exception e){
            System.out.println("ERROR:");
            e.printStackTrace();
        }
    }

    /**
     * return the result set
     * @return the result set
     */
    public static ResultSet getResult() { return sql_results_set; }
}
