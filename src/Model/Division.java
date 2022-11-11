package Model;

/**
 * Division class (model)
 * @author dillon shepherd dshep80@wgu.edu
 */
public class Division {
    private int divisionID;
    private int countryID;
    private String divisionName;
    private String countryName;

    /**
     * Division Constructor
     * @param divisionID the division id
     * @param countryID the country id
     * @param divisionName the division name
     * @param countryName the country name
     */
    public Division(int divisionID, int countryID, String divisionName, String countryName) {
        this.divisionID = divisionID;
        this.countryID = countryID;
        this.divisionName = divisionName;
        this.countryName = countryName;
    }

    /**
     * Get the division id
     * @return the division id
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * Set the division id
     * @param divisionID the division id
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**
     * Get the country id
     * @return the country id
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * Set the country id
     * @param countryID the country id
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**
     * Get the division name
     * @return the division name
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * Set the division name
     * @param divisionName the division name
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /**
     * Get the country name
     * @return the country name
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Set the country name
     * @param countryName the country name
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
