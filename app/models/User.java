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
    public Long id;

    @Constraints.Required
    public Long company_id;

    @Constraints.Required
    public Long address_id;

    @Constraints.Required
    public String username;

    @Constraints.Required
    public String name;

    @Constraints.Required
    public String surname;

    @JsonIgnore
    @Constraints.Required
    public String password;


    @Constraints.Required
    public String patronymic;

    @Constraints.Required
    public String email;

    @Constraints.Required
    @Temporal(TemporalType.TIMESTAMP)
    public Date birthday;

}
