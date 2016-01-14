package models;

import be.objectify.deadbolt.core.models.*;
import be.objectify.deadbolt.core.models.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.Hibernate;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Anton Chernov on 12/26/2015.
 */

@Entity
public class User implements Subject {
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="user_role",
    joinColumns =
            {@JoinColumn(name="user_id")},
    inverseJoinColumns =
            {@JoinColumn(name="role_id")})
    public List<UserRole> userRoleList;

    @Override
    public List<? extends Role> getRoles() {
        return userRoleList;
    }

    @Override
    public List<? extends Permission> getPermissions() {
        return null;
    }


    @Override
    public String getIdentifier() {
        return null;
    }


    @Override
    public String toString() {
        return username;
    }
}
