package Model;

/**
 * Customer class (model)
 * @author dillon shepherd dshep80@wgu.edu
 */
public class Customer {
    private int customerId;
    private String name;
    private String address;
    private String postalCode;
    private String phone;
    private String division;
    private String country;

    /**
     * Customer Constructor
     * @param customerId the customer id
     * @param name the customer name
     * @param address the customer address
     * @param postalCode the customer postal code
     * @param phone the customer phone_number
     * @param division the customer division
     * @param country the customer country
     */
    public Customer(int customerId, String name, String address, String postalCode, String phone, String division, String country) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.division = division;
        this.country = country;
    }

    /**
     * Get the customer id
     * @return the customer id
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Set the customer id
     * @param customerId the customer id
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Get the customer name
     * @return the customer name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the customer name
     * @param name the customer name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the customer address
     * @return the customer address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set the customer address
     * @param address the customer address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get the customer postal code
     * @return the customer postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Set the customer postal code
     * @param postalCode the customer postal code
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Get the customer phone number
     * @return the customer phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Set the customer phone number
     * @param phone the customer phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Get the customer division
     * @return the customer division
     */
    public String getDivision() {
        return division;
    }

    /**
     * Set the customer division
     * @param division the customer division
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * Get the customer country
     * @return the customer country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set the customer country
     * @param country the customer country
     */
    public void setCountry(String country) {
        this.country = country;
    }
}
