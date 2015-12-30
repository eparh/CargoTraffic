package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Anton Chernov on 12/26/2015.
 */

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Constraints.Required
    public Long id;

    @Column(name="company_id")
    public Long companyId;

    @Column(name="address_id")
    public Long addressId;

    @Constraints.Required
    public String username;

    public String name;

    @Constraints.Required
    public String surname;

    @JsonIgnore
    @Constraints.Required
    public String password;

    public String patronymic;

    public String email;

    @Temporal(TemporalType.TIMESTAMP)
    public Date birthday;

}
