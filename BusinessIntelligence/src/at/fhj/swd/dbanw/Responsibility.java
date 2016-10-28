package at.fhj.swd.dbanw;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity public class Responsibility {
    @Id private Integer fkProjectmanagerId;
    @Id private Integer fkProjectId;

    public Responsibility(){}

    public Integer getFkProjectmanagerId() {
        return fkProjectmanagerId;
    }

    public void setFkProjectmanagerId(Integer fkProjectmanagerId) {
        this.fkProjectmanagerId = fkProjectmanagerId;
    }

    public Integer getFkProjectId() {
        return fkProjectId;
    }

    public void setFkProjectId(Integer fkProjectId) {
        this.fkProjectId = fkProjectId;
    }

}
