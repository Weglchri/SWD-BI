package at.fhj.swd.dbanw;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="Offer", schema="public")
public class Offer {
    @Id private Integer offerId;
        private Integer price;
        private Date creationDate;
        private Integer fkProjectId;
        private Integer fkUserId;

    public Offer(int offerId, int price, Date creationDate){
        setOfferId(offerId);
        setPrice(price);
        setCreationDate(creationDate);
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

    public Integer getFkProjectId() {
        return fkProjectId;
    }

    public void setFkProjectId(Integer fkProjectId) {
        this.fkProjectId = fkProjectId;
    }

    public Integer getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(Integer fkUserId) {
        this.fkUserId = fkUserId;
    }


}
