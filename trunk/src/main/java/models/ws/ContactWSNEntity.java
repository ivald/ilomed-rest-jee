package models.ws;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by ivald79 on 23/10/2014.
 */
@XmlRootElement(name = "contact")
public class ContactWSNEntity {
    private String firstName;
    private String lastName;

    public ContactWSNEntity() {
        super();
    }

    public String getFirstName() {
        return firstName;
    }

    @XmlElement
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @XmlElement
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
