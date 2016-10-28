package at.fhj.swd.dbanw;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity public class Projectmanager {
   @Id private Integer userId;
        private String name;
        private String email;
        private String password;
        private Integer involvedIn;
        private String function;
        private String fkCompanyName;

    public Projectmanager(int userId, String name,  String email, String password, int involvedIn, String function ){

    }

    public Projectmanager(){}

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

    public Integer getInvolvedIn() {
        return involvedIn;
    }

    public void setInvolvedIn(Integer involvedIn) {
        this.involvedIn = involvedIn;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getFkCompanyName() {
        return fkCompanyName;
    }

    public void setFkCompanyName(String fkCompanyName) {
        this.fkCompanyName = fkCompanyName;
    }

}
