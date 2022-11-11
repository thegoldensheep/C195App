package Model;

/**
 * Division class (model)
 * @author dillon shepherd dshep80@wgu.edu
 */
public class User {
    /**
     * get logged in user
     * @return
     */
    public static String getCurrentlyLoggedInUser() {
        return currentlyLoggedInUser;
    }

    /**
     * set logged in user
     * @param currentlyLoggedInUser
     */
    public static void setCurrentlyLoggedInUser(String currentlyLoggedInUser) {
        User.currentlyLoggedInUser = currentlyLoggedInUser;
    }

    static private String currentlyLoggedInUser = "";
    private int user_id;
    private String user_name;
    private String password;

    /**
     * User constructor
     * @param user_id the user_id to set
     * @param user_name the user_name to set
     * @param password the password to set
     */
    public User(int user_id, String user_name, String password) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.password = password;
    }

    /**
     * Get the user id
     * @return the user id
     */
    public int getUserId() {
        return user_id;
    }

    /**
     * Set the user id
     * @param user_id the user id
     */
    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    /**
     * Get the user name
     * @return the user name
     */
    public String getUserName() {
        return user_name;
    }

    /**
     * Set the user name
     * @param user_name the user name
     */
    public void setUserName(String user_name) {
        this.user_name = user_name;
    }

    /**
     * Get the user password
     * @return the user password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the user password
     * @param password the user password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
