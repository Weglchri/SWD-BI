package at.fhj.swd.BusinessIntelligence;

import javax.persistence.*;

@Entity
@Table(name="Location", schema="public")
@NamedQueries({@NamedQuery(name="findLocationByCountry", query="SELECT l FROM Location l WHERE l.country = :country"),
              @NamedQuery(name="findAll", query="SELECT l FROM Location l")}
)
public class Location {

    @Id private String address;
        private String country;
        private Integer zip;
        private String city;

    public Location(String address, String country, int zip, String city) {
        setAddress(address);
        setCountry(country);
        setZip(zip);
        setCity(city);
    }

    protected Location() {}


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
