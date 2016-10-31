package at.fhj.swd.dbanw;

import javax.persistence.*;

@Entity
@Table(name="Projectmanager", schema="public")
public class Projectmanager extends User{

    //Projectmanager Variables
    private int involved_In;
    private String function;

    @ManyToOne
    @JoinColumn(name = "fk_company_name")
    public Company company_name;

    public Projectmanager() {

    }

    public Projectmanager(Integer user_id, String name, String email, String password, Integer involved, String task, Company company_name) {
        super(user_id, name, email, password);
        setInvolvedIn(involved_In);
        setFunction(function);
        setCompanyName(company_name);
    }


    public void setCompanyName(Company company) {this.company_name = company;}

    public Company getCompanyName() {return company_name;}

    public int getInvolvedIn() {
        return involved_In;
    }

    public void setInvolvedIn(Integer involvedIn) {
        this.involved_In = involvedIn;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}