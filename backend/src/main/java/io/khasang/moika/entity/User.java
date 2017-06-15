package io.khasang.moika.entity;

import io.khasang.moika.validator.common.NotNullLength;
import io.khasang.moika.validator.user.UserLoginUnique;
import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@UserLoginUnique
@Table(name = "users")
public class User extends ABaseMoikaEntity implements Serializable {

    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @NotNullLength(min = 3, max = 16)
    @NaturalId(mutable = true)
    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @NotBlank
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "r_user_roles",
            joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "id_role"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_user", "id_role"})
    )
    private Set<Role> roles = new HashSet<>();

    @OneToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
    @JoinColumn(name="id_person")
    protected Person person ;

    private boolean enabled;



    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = trim(login);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", roles=" + roles +
                ", enabled=" + enabled +
                ", " + person.toString() + '\'' +
                ", email='" + person.getEmail() + '\'' +
                '}';
    }
}