package Model;

/**
 * Contact class (model)
 * @author dillon shepherd dshep80@wgu.edu
 */
public class Contact {
    private int contactId;
    private String contactName;
    private String email;

    /**
     * Constructor
     * @param contactId the contact id unique
     * @param contactName the contact name
     * @param email the contact email
     */
    public Contact(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     * Get the contact id
     * @return the contact id
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Set the contact id
     * @param contactId the contact id
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * Get the contact name
     * @return the contact name
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Set the contact name
     * @param contactName the contact name
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Get the contact email
     * @return the contact email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the contact email
     * @param email the contact email
     */
    public void setEmail(String email) {
        this.email = email;
    }


}
