package at.fhj.swd.dbanw;

import javax.persistence.*;

@Entity
@Table(schema = "public")
public class Company {

    @Id @Column(name="company_name") private String companyName;
    @OneToOne
    @JoinColumn(name="fk_address")
    private Location address;
    private String branch;

    public Company(String companyName, String branch, Location address){
        setCompany(companyName);
        setBranch(branch);
        setAddress(address);
    }

    public Company(){}

    public String getCompany() {
        return companyName;
    }

    public void setCompany(String companyName) {
        this.companyName = companyName;
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
