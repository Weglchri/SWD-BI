package at.fhj.swd.BusinessIntelligence;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="Offer", schema="public")
public class Offer {

    @Id private Integer offerId;
        private Integer price;
        private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    public User user_id;

    @ManyToOne
    @JoinColumn(name = "fk_project_id")
    public Project project_id;

    public Offer(int offerId, int price, Date creationDate, User user_id, Project project_id){
        setOfferId(offerId);
        setPrice(price);
        setCreationDate(creationDate);
        setFkProjectId(project_id);
        setFkUserId(user_id);
    }

    protected Offer(){}


    public Integer getOfferId() {
        return offerId;
    }

    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Project getFkProjectId() {
        return project_id;
    }

    public void setFkProjectId(Project fkProjectId) {
        this.project_id = project_id;
    }

    public User getFkUserId() {
        return user_id;
    }

    public void setFkUserId(User fkUserId) {
        this.user_id = user_id;
    }


}
