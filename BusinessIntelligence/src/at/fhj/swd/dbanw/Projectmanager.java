package at.fhj.swd.dbanw;

import javax.persistence.*;

@Entity
public class Projectmanager {

    @Id @Column(name = "user_Id")
    private Integer user_Id;
    private Integer involved_In;
    private String function;
    private String name;
    private String email;
    private String password;

    @ManyToOne
    @JoinColumn(name = "fk_company_name")
    public Company company_name;


    public Projectmanager(Integer user_Id, String name, String email, String password, Integer involved_In, String function, Company company_name) {
        setUserId(user_Id);
        setInvolvedIn(involved_In);
        setFunction(function);
        setCompanyName(company_name);
        setName(name);
        setEmail(email);
        setPassword(password);
    }

    public Projectmanager() {}

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

    public void setCompanyName(Company company) {this.company_name = company;}

    public Company getCompany() {return company_name;}

    public Integer getInvolvedIn() {
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

    public Company getCompany_name() {
        return company_name;
    }

    public void setCompany_name(Company company_name) {
        this.company_name = company_name;
    }

    public Integer getUserId() {
        return user_Id;
    }

    public void setUserId(Integer user_Id) {
        this.user_Id = user_Id;
    }
}