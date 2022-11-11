package Utilities;


import java.sql.Connection;
import java.sql.DriverManager;

/**
 * JBDCClass: This class is used to connect to the database.
 * @author largely based off of the DB Connection class provided by Mark Kinkhead, with some
 * utilization of the sql query example provided as well.
 */
public abstract class JBDC {
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String userName = "sqlUser";
    private static String password = "Passw0rd!";
    private static Connection connection;

    /**
     * This method is used to open the connection to the database.
     * @return Connection
     */
    public static Connection openConnection()
    {
        try {
            Class.forName(driver); // Locate Driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
            System.out.println("Connection successful!");
        }
        catch(Exception e)
        {
            //System.out.println("Error:" + e.getMessage());
            e.printStackTrace();
        }

        return connection;
    }

    /**
     * This method is used to close the connection to the database.
     * @return the connection
     */
    public static Connection getConnection(){
        return connection;
    }

    /**
     * This method is used to close the connection to the database.
     */
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        }
        catch(Exception e)
        {
            //System.out.println("Error:" + e.getMessage());
            e.printStackTrace();
        }
    }




}
