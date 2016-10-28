package at.fhj.swd.dbanw;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity public class Project {
   @Id private Integer projectId;
        private Integer capital;
        private Date creationDate;
        private String task;
        private String fkAdress;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getCapital() {
        return capital;
    }

    public void setCapital(Integer capital) {
        this.capital = capital;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getFkAdress() {
        return fkAdress;
    }

    public void setFkAdress(String fkAdress) {
        this.fkAdress = fkAdress;
    }

}
