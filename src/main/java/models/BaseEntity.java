package models;

import constant.CommonConstant;
import services.impl.SequenceGeneratorServiceImpl;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ivald79 on 02/07/2014.
 */
@MappedSuperclass
public class BaseEntity<E> implements Serializable {
    private static final long serialVersionUID = 568379222048217476L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) throws Exception {
       this.id = id;
    }
}
