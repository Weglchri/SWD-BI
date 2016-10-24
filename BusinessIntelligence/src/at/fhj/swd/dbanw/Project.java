package at.fhj.swd.dbanw;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Project {
    private Integer projectId;
    private Integer capital;
    private Date creationDate;
    private String task;
    private String fkAdress;

    @Id
    @Column(name = "project_id")
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "capital")
    public Integer getCapital() {
        return capital;
    }

    public void setCapital(Integer capital) {
        this.capital = capital;
    }

    @Basic
    @Column(name = "creation_date")
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Basic
    @Column(name = "task")
    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @Id
    @Column(name = "fk_adress")
    public String getFkAdress() {
        return fkAdress;
    }

    public void setFkAdress(String fkAdress) {
        this.fkAdress = fkAdress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (projectId != null ? !projectId.equals(project.projectId) : project.projectId != null) return false;
        if (capital != null ? !capital.equals(project.capital) : project.capital != null) return false;
        if (creationDate != null ? !creationDate.equals(project.creationDate) : project.creationDate != null)
            return false;
        if (task != null ? !task.equals(project.task) : project.task != null) return false;
        if (fkAdress != null ? !fkAdress.equals(project.fkAdress) : project.fkAdress != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = projectId != null ? projectId.hashCode() : 0;
        result = 31 * result + (capital != null ? capital.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (task != null ? task.hashCode() : 0);
        result = 31 * result + (fkAdress != null ? fkAdress.hashCode() : 0);
        return result;
    }
}
