package at.fhj.swd.dbanw;

import sun.jvm.hotspot.memory.Generation;

import javax.persistence.*;

@Entity
@Inheritance(strategy= InheritanceType.JOINED)
@Table(schema="public")
public class User {
    @Id @Column(name = "user_id") @GeneratedValue(strategy = GenerationType.IDENTITY) private Integer userId;
        private String name;
        private String email;
        private String password;

    public User(int userId, String name, String email, String password) {
        setUserId(userId);
        setName(name);
        setEmail(email);
        setPassword(password);
    }

    public User() {}

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {this.userId = userId;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {return password;}

    public void setPassword(String password) {
        this.password = password;
    }
}
