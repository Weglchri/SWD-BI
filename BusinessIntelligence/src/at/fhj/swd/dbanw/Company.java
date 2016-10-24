package at.fhj.swd.dbanw;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Company {
    private String companyName;
    private String fkAdress;
    private String branch;

    @Id
    @Column(name = "company_name")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Id
    @Column(name = "fk_adress")
    public String getFkAdress() {
        return fkAdress;
    }

    public void setFkAdress(String fkAdress) {
        this.fkAdress = fkAdress;
    }

    @Basic
    @Column(name = "branch")
    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (companyName != null ? !companyName.equals(company.companyName) : company.companyName != null) return false;
        if (fkAdress != null ? !fkAdress.equals(company.fkAdress) : company.fkAdress != null) return false;
        if (branch != null ? !branch.equals(company.branch) : company.branch != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = companyName != null ? companyName.hashCode() : 0;
        result = 31 * result + (fkAdress != null ? fkAdress.hashCode() : 0);
        result = 31 * result + (branch != null ? branch.hashCode() : 0);
        return result;
    }
}
