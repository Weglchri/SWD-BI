package at.fhj.swd.BusinessIntelligence;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="Projectmanager", schema="public")
@NamedQuery(name="findProjectmanagerByName", query="SELECT p FROM Projectmanager p where p.name = :name")
public class Projectmanager extends User{

    //Projectmanager Variables
    private int involved_In;
    private String function;

    @ManyToOne
    @JoinColumn(name = "fk_company_name")
    public Company company_name;

    @ManyToMany
    @JoinTable(
            name="Responsibility",
            joinColumns=@JoinColumn(name="fk_user_id", referencedColumnName="user_id"),
            inverseJoinColumns=@JoinColumn(name="fk_project_id", referencedColumnName="project_id"))
    private List<Project> projects = new ArrayList<Project>();

    protected Projectmanager() {}


    public Projectmanager(String name, String email, String password, Integer involved_In, String function, Company company_name) {
        super(name, email, password);
        setInvolvedIn(involved_In);
        setFunction(function);
        setCompanyName(company_name);
    }

    private void setCompanyName(Company company) {this.company_name = company;}

    public Company getCompanyName() {return company_name;}

    public int getInvolvedIn() {
        return involved_In;
    }

    private void setInvolvedIn(Integer involved_In) {
        this.involved_In = involved_In;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }


    public List<Project> getProjects() {return projects;}

    public void addProject(Project project) {
        projects.add(project);
        project.addProjectmanager(this);
    }

}