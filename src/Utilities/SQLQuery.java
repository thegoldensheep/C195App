package Utilities;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Locale;

public abstract class SQLQuery {
    private static String sql_query;
    private static Statement sql_statement;
    private static ResultSet sql_results_set;

    public static void makeQuery(String query){
        sql_query = query;
        try {
            sql_statement = JBDC.getConnection().createStatement();
            String query_lwr = sql_query.toLowerCase();
            if(query_lwr.startsWith("select")) { sql_results_set = sql_statement.executeQuery(sql_query); }

            if(query_lwr.startsWith("update")||query_lwr.startsWith("insert")||query_lwr.startsWith("delete")) {
                System.out.println(";lakjf;alksdjf;");
                sql_statement.executeUpdate(sql_query);
            }
        }catch(Exception e){
            System.out.println("ERROR:");
            e.printStackTrace();
        }
    }

    public static ResultSet getResult() { return sql_results_set; }
}
