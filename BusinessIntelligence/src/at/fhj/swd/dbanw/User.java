package at.fhj.swd.dbanw;

import sun.jvm.hotspot.memory.Generation;

import javax.persistence.*;

@Entity
@Inheritance(strategy= InheritanceType.JOINED)
@Table(name ="User", schema="public")
public class User
{
    @SequenceGenerator (name = "UserIdGenerator",
            sequenceName = "user_sequence",
            allocationSize = 1)

    @Id @Column(name = "user_id") @GeneratedValue(generator="UserIdGenerator")
        private int userId;
        private String name;
        private String email;
        private String password;
        private String dtype;

    public User(String name, String email, String password, String dtype) {
        setName(name);
        setEmail(email);
        setPassword(password);
        setDtype(dtype);
    }

    protected User() {}


    public int getUserId() {
        return userId;
    }

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

    public void setPassword(String password) {this.password = password;}

    public String getDtype() {return dtype;}

    public void setDtype(String dtype) {this.dtype = dtype;}

}
