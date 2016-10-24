package at.fhj.swd.dbanw;


import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by sattlerb on 08/10/16.
 */

@Entity
public class User
{

    @Id
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private String email;
    private String password;

//    protected User(){}

    public User(int id, String name, String email, String password)
    {
        setName(name);
    }


}
