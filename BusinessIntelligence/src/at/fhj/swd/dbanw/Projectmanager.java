package at.fhj.swd.dbanw;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Projectmanager {
    private Integer userId;
    private String name;
    private String email;
    private String password;
    private Integer involvedIn;
    private String fkCompanyName;
    private String function;

    @Id
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "involved_in")
    public Integer getInvolvedIn() {
        return involvedIn;
    }

    public void setInvolvedIn(Integer involvedIn) {
        this.involvedIn = involvedIn;
    }

    @Basic
    @Column(name = "fk_company_name")
    public String getFkCompanyName() {
        return fkCompanyName;
    }

    public void setFkCompanyName(String fkCompanyName) {
        this.fkCompanyName = fkCompanyName;
    }

    @Basic
    @Column(name = "function")
    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Projectmanager that = (Projectmanager) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (involvedIn != null ? !involvedIn.equals(that.involvedIn) : that.involvedIn != null) return false;
        if (fkCompanyName != null ? !fkCompanyName.equals(that.fkCompanyName) : that.fkCompanyName != null)
            return false;
        if (function != null ? !function.equals(that.function) : that.function != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (involvedIn != null ? involvedIn.hashCode() : 0);
        result = 31 * result + (fkCompanyName != null ? fkCompanyName.hashCode() : 0);
        result = 31 * result + (function != null ? function.hashCode() : 0);
        return result;
    }
}
