package at.fhj.swd.BusinessIntelligence;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Freelancer", schema="public")
@NamedQuery(name="findFreelancerByName", query="SELECT f from Freelancer f  WHERE f.name = :name")
public class Freelancer extends User {

    private String profession;
    private String availability;
    private Integer hourly_wage;
    private String education;

    @OneToOne
    @JoinColumn(name="fk_address")
    private Location location;


    public Freelancer(String name, String email, String password, String profession, String availability, Integer hourly_wage, String education, Location location){
        super(name, email, password);
        setProfession(profession);
        setAvailability(availability);
        setHourly_wage(hourly_wage);
        setEducation(education);
        setLocation(location);
    }

    protected Freelancer(){}


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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
        location.setFreelancer(this);
    }


}
