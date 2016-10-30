package at.fhj.swd.dbanw;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity public class User {
    @Id private Integer user_Id;
        private String name;
        private String email;
        private String password;

    public User(int user_Id, String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        setUserId(user_Id);
    }

    public User() {}

    public Integer getUserId() {
        return user_Id;
    }

    public void setUserId(Integer userId) {this.user_Id = userId;}

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
