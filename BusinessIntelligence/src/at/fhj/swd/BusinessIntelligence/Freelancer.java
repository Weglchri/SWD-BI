package at.fhj.swd.BusinessIntelligence;

import javax.persistence.*;

@Entity
@Table(name="Freelancer", schema="public")
@NamedQuery(name="findFreelancerByName", query="SELECT f from Freelancer f  WHERE f.name = :name")
public class Freelancer extends User {

    //inner join User u on f.name=u.name
    //Freelancer Variables
    private String profession;
    private String availability;
    private Integer hourly_wage;
    private String education;

    @OneToOne
    @JoinColumn(name="fk_address")
    private Location address;

    public Freelancer(String name, String email, String password, String profession, String availability, Integer hourly_wage, String education, Location address){
        super(name, email, password);
        setProfession(profession);
        setAvailability(availability);
        setHourly_wage(hourly_wage);
        setEducation(education);
        setAddress(address);
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

    private void setAvailability(String availability) {
        this.availability = availability;
    }

    public Integer getHourly_wage() {
        return hourly_wage;
    }

    private void setHourly_wage(Integer hourly_wage) {
        this.hourly_wage = hourly_wage;
    }

    public String getEducation() {
        return education;
    }

    private void setEducation(String education) {
        this.education = education;
    }

    public Location getAddress() {
        return address;
    }

    private void setAddress(Location address) {
        this.address = address;
    }
}
