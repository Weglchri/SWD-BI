package at.fhj.swd.BusinessIntelligence;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Company", schema="public")
@NamedQueries({
        @NamedQuery(name="findCompanyByName", query="SELECT c FROM Company c where c.company_name = :companyName"),
        @NamedQuery(name="findCompaniesByBranche", query="SELECT c FROM Company c where c.branch = :branch"),
        @NamedQuery(name="findAllProjectmanagersOfCompany", query="SELECT p.name FROM Projectmanager p JOIN p.company c WHERE c.company_name = :name")}
)
public class Company {

    @Id @Column(name="company_name")
    private String company_name;
    private String branch;

    @OneToOne
    @JoinColumn(name="fk_address")
    private Location location;

    @OneToMany (mappedBy="company")
    private List<Projectmanager> projectmanagers = new ArrayList<Projectmanager>();

    @OneToMany (mappedBy="company")
    private List<Project> projects = new ArrayList<Project>();

    public Company(String company_name, String branch, Location location){
        setCompanyName(company_name);
        setBranch(branch);
        setLocation(location);
    }

    protected Company(){}


    public String getCompanyName() {
        return company_name;
    }

    public void setCompanyName(String company_name) {
        this.company_name = company_name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Location getLocation() {return location;}

    public void setLocation(Location location) {
        this.location = location;
        location.setCompany(this);
    }

    public List<Projectmanager> getProjectmanagers() { return projectmanagers; }

    protected void addProjectmanager(Projectmanager projectmanager) {
        projectmanagers.add(projectmanager);
    }

    public List<Project> getProjects() {
        return projects;
    }

    protected void addProject(Project project) {
        projects.add(project);
    }
}
