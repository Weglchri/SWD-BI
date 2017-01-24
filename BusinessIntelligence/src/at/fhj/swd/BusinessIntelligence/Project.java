package at.fhj.swd.BusinessIntelligence;

import javax.persistence.*;
import java.sql.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Project", schema="public")
@NamedQueries({ @NamedQuery(name="findByTask", query="SELECT p FROM Project p WHERE p.task = :task"),
                @NamedQuery(name="findByProjectType", query="SELECT NEW at.fhj.swd.BusinessIntelligence.ConciseProjectmanager(m) FROM Project p JOIN p.projectmanagers WHERE p.task = :projectType")})

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
    private Company company;

    @OneToMany(mappedBy = "project")
    private List<Offer> offers = new ArrayList<>();

    @ManyToMany(mappedBy="projects")
    private List<Projectmanager> projectmanagers = new ArrayList<Projectmanager>();

    public Project(int capital, java.util.Date creation_date, String task, Company company) {
        this.creation_date = creation_date;
        setCapital(capital);
        setTask(task);
        setCompany(company);
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

    public Company getCompany() {return company;}

    public void setCompany(Company company) {
        this.company = company;
        company.addProject(this);
    }

    public List<Projectmanager> getProjectmanagers() {return projectmanagers;}

    protected void addProjectmanager(Projectmanager projectmanager) { projectmanagers.add(projectmanager);}

    public List<Offer> getOffers() {
        return offers;
    }

    protected void addOffer(Offer offer) {
        offers.add(offer);
    }


}

