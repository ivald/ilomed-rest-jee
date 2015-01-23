package models.ws;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by ivald79 on 23/10/2014.
 */
@XmlRootElement(name="user")
public class UserWSNEntity {

    private String userName;

    private String password;

    private ContactWSNEntity contactWSNEntity;

    public UserWSNEntity() {
        super();
    }

    public String getUserName() {
        return userName;
    }

    @XmlElement
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    @XmlElement
    public void setPassword(String password) {
        this.password = password;
    }

    public ContactWSNEntity getContactWSNEntity() {
        return contactWSNEntity;
    }

    //override the name for the XML element
    @XmlElement(name = "contact")
    public void setContactWSNEntity(ContactWSNEntity contact) {
        this.contactWSNEntity = contact;
    }
}
