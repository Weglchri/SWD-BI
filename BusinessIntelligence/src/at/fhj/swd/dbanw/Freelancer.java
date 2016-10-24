package at.fhj.swd.dbanw;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Freelancer {
    private Integer userId;
    private String name;
    private String email;
    private String password;
    private String profession;
    private String availability;
    private String fkAdress;
    private Integer hourlyWage;
    private String education;

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
    @Column(name = "profession")
    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Basic
    @Column(name = "availability")
    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @Basic
    @Column(name = "fk_adress")
    public String getFkAdress() {
        return fkAdress;
    }

    public void setFkAdress(String fkAdress) {
        this.fkAdress = fkAdress;
    }

    @Basic
    @Column(name = "hourly_wage")
    public Integer getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(Integer hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    @Basic
    @Column(name = "education")
    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Freelancer that = (Freelancer) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (profession != null ? !profession.equals(that.profession) : that.profession != null) return false;
        if (availability != null ? !availability.equals(that.availability) : that.availability != null) return false;
        if (fkAdress != null ? !fkAdress.equals(that.fkAdress) : that.fkAdress != null) return false;
        if (hourlyWage != null ? !hourlyWage.equals(that.hourlyWage) : that.hourlyWage != null) return false;
        if (education != null ? !education.equals(that.education) : that.education != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (profession != null ? profession.hashCode() : 0);
        result = 31 * result + (availability != null ? availability.hashCode() : 0);
        result = 31 * result + (fkAdress != null ? fkAdress.hashCode() : 0);
        result = 31 * result + (hourlyWage != null ? hourlyWage.hashCode() : 0);
        result = 31 * result + (education != null ? education.hashCode() : 0);
        return result;
    }
}
