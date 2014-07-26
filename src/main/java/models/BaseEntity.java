package models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by ivald79 on 02/07/2014.
 */
@MappedSuperclass
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 568379222048217476L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
