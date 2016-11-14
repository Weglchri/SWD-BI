package at.fhj.swd.BusinessIntelligence;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Project", schema="public")
public class Project {
    @Id @Column(name = "project_id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer project_id;
    private Integer capital;
    private String task;

    @Column(name="creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date creation_date;

    @ManyToOne
    @JoinColumn(name = "fk_company_name")
    public Company company_name;

    //Problems with Insertion in Responsibility(Join Table)
    @ManyToMany(mappedBy="projects")
    private List<Projectmanager> projectmanagers = new ArrayList<Projectmanager>();

    public Project(int capital, java.util.Date creation_date, String task, Company company_name) {
        this.creation_date = creation_date;
        setCapital(capital);
        setTask(task);
        setCompanyName(company_name);
    }

    protected Project() {}


    public Integer getProjectId() {
        return project_id;
    }

    public Integer getCapital() {
        return capital;
    }

    public void setCapital(Integer capital) {
        this.capital = capital;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Company getCompanyName() {return company_name;}

    public void setCompanyName(Company company_name) {this.company_name = company_name;}

    public List<Projectmanager> getProjectmanagers() {return projectmanagers;}

    public void addProjectmanager(Projectmanager projectmanager) {
        projectmanagers.add(projectmanager);
    }
}
