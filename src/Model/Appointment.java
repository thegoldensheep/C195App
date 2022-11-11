package Model;


import java.time.LocalDateTime;

/**
 * Appointment class (model)
 * @author dillon shepherd dshep80@gmail.com
 */
public class Appointment {
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private String contact;
    private LocalDateTime start;
    private LocalDateTime end;
    private int customerId;
    private int userId;

    /**
     * Appointment constructor
     * @param appointmentId the appointment id unique
     * @param title the appointment title
     * @param description the appointment description
     * @param location the appointment location
     * @param type the appointment type
     * @param contact the appointment contact
     * @param start the appointment start
     * @param end the appointment end
     * @param customerId the appointment customer id
     * @param userId the appointment user id
     */
    public Appointment(int appointmentId, String title, String description, String location, String type, String contact, LocalDateTime start, LocalDateTime end, int customerId, int userId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.contact = contact;
        this.start = start;
        this.end = end;
        this.customerId = customerId;
        this.userId = userId;
    }

    /**
     * get appointment id
     * @return appointment id
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * set appointment id
     * @param appointmentId the appointment id
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * get appointment title
     * @return appointment title
     */
    public String getTitle() {
        return title;
    }

    /**
     * set appointment title
     * @param title the appointment title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * get appointment description
     * @return appointment description
     */
    public String getDescription() {
        return description;
    }

    /**
     * set appointment description
     * @param description the appointment description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * get appointment location
     * @return appointment location
     */
    public String getLocation() {
        return location;
    }

    /**
     * set appointment location
     * @param location the appointment location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * get appointment type
     * @return appointment type
     */
    public String getType() {
        return type;
    }

    /**
     * set appointment type
     * @param type the appointment type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * get appointment contact
     * @return appointment contact
     */
    public String getContact() {
        return contact;
    }

    /**
     * set appointment contact
     * @param contact the appointment contact
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * get appointment start
     * @return appointment start
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * set appointment start
     * @param start the appointment start
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * get appointment end
     * @return appointment end
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * set appointment end
     * @param end the appointment end
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**
     * get appointment customer id
     * @return appointment customer id
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * set appointment customer id
     * @param customerId the appointment customer id
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * get appointment user id
     * @return appointment user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * set appointment user id
     * @param userId the appointment user id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
}
