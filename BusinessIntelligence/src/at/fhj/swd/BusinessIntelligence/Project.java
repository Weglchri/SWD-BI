package at.fhj.swd.BusinessIntelligence;

import javax.persistence.*;
import java.sql.Date;
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

    @ManyToMany
    @JoinTable(name="Responsibility", joinColumns=@JoinColumn(name="fk_project_id", referencedColumnName="project_id"), inverseJoinColumns=@JoinColumn(name="fk_user_id", referencedColumnName="user_id"))
    private List<User> users;


    public Project(int capital, java.util.Date creation_date, String task, Company company_name) {
        //setCreationDate(creationDate);
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
/*
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
*/
    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Company getCompanyName() {return company_name;}

    public void setCompanyName(Company company_name) {this.company_name = company_name;}
/*
    public List<User> getUsers() {return users;}

    public void addUsers(User user) {
        users.add(user);
    }
*/
}
