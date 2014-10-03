package models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import services.impl.SequenceGeneratorServiceImpl;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by ivald79 on 10/06/2014.
 */
@Entity
@Table(name = "S_USER")
@NamedQueries({
        @NamedQuery(name = UserEntity.FIND_BY_USERNAME,
                    query = "Select o from UserEntity o where o.userName = :p1")})
@XmlRootElement(name = "UserEntity")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserEntity extends BaseEntity {

    public final static String FIND_BY_USERNAME = "UserEntity.findByUsername";

    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "PASSWORD")
    private String password;
    @OneToOne
    @JoinColumn(name = "CONTACT_ID")
    private ContactEntity contactEntity;

    public UserEntity() {
        super();
    }

    public UserEntity(Class objClass) throws Exception {
        setId(SequenceGeneratorServiceImpl.getInstance().getNextSequenceId(objClass.getName()));
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

    public ContactEntity getContactEntity() {
        return contactEntity;
    }

    public void setContactEntity(ContactEntity contact) {
        this.contactEntity = contact;
    }

    public Boolean isPasswordEqual(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(password, this.password);
    }
}
