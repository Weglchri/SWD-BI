package at.fhj.swd.BusinessIntelligence;

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

    public Projectmanager(String name, String email, String password, String dtype, Integer involved_In, String function, Company company_name) {
        super(name, email, password, dtype);
        setInvolvedIn(involved_In);
        setFunction(function);
        setCompanyName(company_name);
    }


    public void setCompanyName(Company company) {this.company_name = company;}

    public Company getCompanyName() {return company_name;}

    public int getInvolvedIn() {
        return involved_In;
    }

    public void setInvolvedIn(Integer involved_In) {
        this.involved_In = involved_In;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}