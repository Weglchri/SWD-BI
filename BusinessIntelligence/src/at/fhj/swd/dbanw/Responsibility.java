package at.fhj.swd.dbanw;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Responsibility {
    @Id private Integer fkProjectmanagerId;
    private Integer fkProjectId;

    @Id
    @Column(name = "fk_projectmanager_id")
    public Integer getFkProjectmanagerId() {
        return fkProjectmanagerId;
    }

    public void setFkProjectmanagerId(Integer fkProjectmanagerId) {
        this.fkProjectmanagerId = fkProjectmanagerId;
    }

    @Id
    @Column(name = "fk_project_id")
    public Integer getFkProjectId() {
        return fkProjectId;
    }

    public void setFkProjectId(Integer fkProjectId) {
        this.fkProjectId = fkProjectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Responsibility that = (Responsibility) o;

        if (fkProjectmanagerId != null ? !fkProjectmanagerId.equals(that.fkProjectmanagerId) : that.fkProjectmanagerId != null)
            return false;
        if (fkProjectId != null ? !fkProjectId.equals(that.fkProjectId) : that.fkProjectId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fkProjectmanagerId != null ? fkProjectmanagerId.hashCode() : 0;
        result = 31 * result + (fkProjectId != null ? fkProjectId.hashCode() : 0);
        return result;
    }
}
