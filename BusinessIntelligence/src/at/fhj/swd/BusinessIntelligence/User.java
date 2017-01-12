package at.fhj.swd.BusinessIntelligence;

import javax.persistence.*;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy= InheritanceType.JOINED)
@Table(name ="User", schema="public")
@NamedQuery(name="findByName", query="SELECT u FROM User u WHERE u.name = :name")
public class User {
    @Id @Column(name = "user_id") @GeneratedValue(strategy = GenerationType.IDENTITY)

        private Integer userId;
        private String name;
        private String email;
        private String password;

    public User(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
    }

    protected User() {}


    public Integer getUserId() {return userId; }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}
}
