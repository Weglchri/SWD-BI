package at.fhj.swd.dbanw;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Offer {
    private Integer offerId;
    private Integer price;
    private Date creationDate;
    private Integer fkProjectId;
    private Integer fkUserId;

    @Id
    @Column(name = "offer_id")
    public Integer getOfferId() {
        return offerId;
    }

    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }

    @Basic
    @Column(name = "price")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Basic
    @Column(name = "creation_date")
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Basic
    @Column(name = "fk_project_id")
    public Integer getFkProjectId() {
        return fkProjectId;
    }

    public void setFkProjectId(Integer fkProjectId) {
        this.fkProjectId = fkProjectId;
    }

    @Basic
    @Column(name = "fk_user_id")
    public Integer getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(Integer fkUserId) {
        this.fkUserId = fkUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Offer offer = (Offer) o;

        if (offerId != null ? !offerId.equals(offer.offerId) : offer.offerId != null) return false;
        if (price != null ? !price.equals(offer.price) : offer.price != null) return false;
        if (creationDate != null ? !creationDate.equals(offer.creationDate) : offer.creationDate != null) return false;
        if (fkProjectId != null ? !fkProjectId.equals(offer.fkProjectId) : offer.fkProjectId != null) return false;
        if (fkUserId != null ? !fkUserId.equals(offer.fkUserId) : offer.fkUserId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = offerId != null ? offerId.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (fkProjectId != null ? fkProjectId.hashCode() : 0);
        result = 31 * result + (fkUserId != null ? fkUserId.hashCode() : 0);
        return result;
    }
}
