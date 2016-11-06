package at.fhj.swd.BusinessIntelligence;

import javax.persistence.*;

@Entity
@Table(name="Freelancer", schema="public")
public class Freelancer extends User {

    //Freelancer Variables
    private String profession;
    private String availability;
    private Integer hourly_wage;
    private String education;

    @OneToOne
    @JoinColumn(name="fk_address")
    private Location address;

    public Freelancer(String name, String email, String password, String dtype, String profession, String availability, Integer hourly_wage, String education, Location address){
        super(name, email, password, dtype);
        setProfession(profession);
        setAvailability(availability);
        setHourly_wage(hourly_wage);
        setEducation(education);
        setAddress(address);
    }

    public Freelancer(){}


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

    public Integer getHourly_wage() {
        return hourly_wage;
    }

    public void setHourly_wage(Integer hourly_wage) {
        this.hourly_wage = hourly_wage;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Location getAddress() {
        return address;
    }

    public void setAddress(Location address) {
        this.address = address;
    }
}
