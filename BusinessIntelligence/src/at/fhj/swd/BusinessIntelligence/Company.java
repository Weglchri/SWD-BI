package at.fhj.swd.BusinessIntelligence;

import javax.persistence.*;

@Entity
@Table(name="Company", schema="public")
@NamedQueries({
        @NamedQuery(name="findCompanyByName", query="SELECT c FROM Company c where c.company_name = :companyName"),
        @NamedQuery(name="findCompaniesByBranche", query="SELECT c FROM Company c where c.branch = :branch")}
)
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

    protected Company(){}


    public String getCompany() {
        return company_name;
    }

    private void setCompany(String company_name) {
        this.company_name = company_name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Location getAddress() {return address;}

    private void setAddress(Location address) {this.address = address;}

}
