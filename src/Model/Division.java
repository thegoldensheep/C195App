package Model;

public class Division {
    private int divisionID;
    private int countryID;
    private String divisionName;
    private String countryName;

    public Division(int divisionID, int countryID, String divisionName, String countryName) {
        this.divisionID = divisionID;
        this.countryID = countryID;
        this.divisionName = divisionName;
        this.countryName = countryName;
    }

    public int getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
