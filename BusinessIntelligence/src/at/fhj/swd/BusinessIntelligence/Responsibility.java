package at.fhj.swd.BusinessIntelligence;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Responsibility", schema="public")
public class Responsibility {
    @Id private Integer fkProjectmanagerId;
        private Integer fkProjectId;

    protected Responsibility(){}


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
