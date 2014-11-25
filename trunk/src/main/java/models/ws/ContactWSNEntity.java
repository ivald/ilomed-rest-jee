package models.ws;

/**
 * Created by ivald79 on 23/10/2014.
 */
public class ContactWSNEntity {
    private String firstName;
    private String lastName;

    public ContactWSNEntity() {
        super();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
