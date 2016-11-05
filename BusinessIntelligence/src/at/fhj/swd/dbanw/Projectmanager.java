package at.fhj.swd.dbanw;

import javax.persistence.*;

@Entity
@Table(name="Projectmanager", schema="public")
public class Projectmanager extends User{


    @SequenceGenerator (name = "UserIdSequence", sequenceName = "User_Sequences", allocationSize = 1)
    @Id @GeneratedValue (generator="UserIdSequence")
    private int id;

    //Projectmanager Variables
    @Column(name="Involved_In") private int involvedIn;
    private String function;

    @ManyToOne
    @JoinColumn(name = "fk_company_name")
    public Company company_name;

    protected Projectmanager() {}

    public Projectmanager(String name, String email, String password, String dtype, Integer involved_In, String function, Company company_name) {
        super(name, email, password, dtype);
        setInvolvedIn(involved_In);
        setFunction(function);
        setCompanyName(company_name);
    }


    public void setCompanyName(Company company) {this.company_name = company;}

    public Company getCompanyName() {return company_name;}

    public int getInvolvedIn() {
        return involvedIn;
    }

    public void setInvolvedIn(Integer involved_In) {
        this.involvedIn = involved_In;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}