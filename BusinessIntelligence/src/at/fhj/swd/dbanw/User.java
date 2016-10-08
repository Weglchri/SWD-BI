package at.fhj.swd.dbanw;


/**
 * Created by sattlerb on 08/10/16.
 */

@Entity public class User
{

    @Id private int id;
    private String name;
    private String email;
    private String password;

//    protected User(){}

    public User(int id, String name, String email, String password, int id1)
    {

    }

}
