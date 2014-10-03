package models;

import services.impl.SequenceGeneratorServiceImpl;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by ivald79 on 24/09/2014.
 */
@Entity
@Table(name = "C_CONTACT")
@NamedQueries({@NamedQuery(name = ContactEntity.FIND_CONTACT_BY_ID,
        query = "Select o from ContactEntity o where o.id = :p1")})
@XmlRootElement(name = "ContactEntity")
@XmlAccessorType(XmlAccessType.FIELD)
public class ContactEntity extends BaseEntity {

    public static final String FIND_CONTACT_BY_ID = "ContactEntity.findContactById";

    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @OneToOne(mappedBy = "contactEntity", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID")
    private UserEntity userEntity;

    public ContactEntity() {
        super();
    }

    public ContactEntity(Class objClass) throws Exception {
        setId(SequenceGeneratorServiceImpl.getInstance().getNextSequenceId(objClass.getName()));
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

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
