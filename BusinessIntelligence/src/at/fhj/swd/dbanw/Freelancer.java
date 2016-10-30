package at.fhj.swd.dbanw;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity public class Freelancer extends User {
    @Id private Integer userId;
        private String name;
        private String email;
        private String password;
        private String profession;
        private String availability;
        private String fk_address;
        private Integer hourlyWage;
        private String education;


    public Freelancer(Integer userId, String name, String email, String password, String profession, String availability, Integer hourlyWage, String education){

        setProfession(profession);
        setAvailability(availability);
        setHourlyWage(hourlyWage);
        setEducation(education);

    }

    public Freelancer(){}

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getFkAddress() {
        return fk_address;
    }

    public void setFkAddress(String fkAdress) {
        this.fk_address = fkAdress;
    }

    public Integer getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(Integer hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

}
