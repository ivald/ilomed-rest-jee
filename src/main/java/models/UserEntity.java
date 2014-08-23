package models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by ivald79 on 10/06/2014.
 */
@Entity
@Table(name = "S_USER")
@NamedQueries({@NamedQuery(name = UserEntity.FIND_BY_USERNAME_PASSWORD,
        query = "Select o from UserEntity o where o.userName = :p1")})
@XmlRootElement(name = "USER_ENTITY_ELEMENT")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserEntity extends BaseEntity {

    public final static String FIND_BY_USERNAME_PASSWORD = "UserEntity.findByUsernamePassword";

    @Column(name = "username")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "contact_id")
    private Long contactID;

    public UserEntity() {
        super();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public Long getContactId() {
        return contactID;
    }

    public void setContactId(Long contactId) {
        this.contactID = contactId;
    }

    public Boolean isPasswordEqual(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(password, this.password);
    }
}
