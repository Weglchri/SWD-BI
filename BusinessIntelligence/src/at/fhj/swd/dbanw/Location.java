package at.fhj.swd.dbanw;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity public class Location {
    @Id private String address;
        private String country;
        private Integer zip;
        private String city;

    public Location(String adress, String country, int zip, String city) {
        setAdress(address);
        setCountry(country);
        setZip(zip);
        setCity(city);
    }

    public Location() {
    }

    public String getAdress() {
        return address;
    }

    public void setAdress(String address) {
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
