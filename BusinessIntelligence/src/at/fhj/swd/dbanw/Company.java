package at.fhj.swd.dbanw;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity public class Company {
   @Id private String companyName;
        private String fkAdress;
        private String branch;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFkAdress() {
        return fkAdress;
    }

    public void setFkAdress(String fkAdress) {
        this.fkAdress = fkAdress;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

}
