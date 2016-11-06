package at.fhj.swd.BusinessIntelligence;

import javax.persistence.*;

@Entity
@Table(name="Company", schema="public")
public class Company {

    @Id @Column(name="company_name")
    private String company_name;
    private String branch;

    @OneToOne
    @JoinColumn(name="fk_address")
    private Location address;


    public Company(String company_name, String branch, Location address){
        setCompany(company_name);
        setBranch(branch);
        setAddress(address);
    }

    public Company(){}


    public String getCompany() {
        return company_name;
    }

    public void setCompany(String company_name) {
        this.company_name = company_name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Location getAddress() {return address;}

    public void setAddress(Location address) {this.address = address;}

}
