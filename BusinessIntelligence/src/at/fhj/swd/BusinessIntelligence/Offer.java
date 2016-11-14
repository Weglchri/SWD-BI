package at.fhj.swd.BusinessIntelligence;

import javax.persistence.*;


@Entity
@Table(name="Offer", schema="public")
public class Offer {
    @Id @Column(name = "offer_id") @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer offerId;
        private Integer price;

    @Column(name="creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date creation_date;

    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    public User user_id;

    @ManyToOne
    @JoinColumn(name = "fk_project_id")
    public Project project_id;

    public Offer(int price,  java.util.Date creation_date, User user_id, Project project_id){
        this.creation_date = creation_date;
        setPrice(price);
        setFkProjectId(project_id);
        setFkUserId(user_id);
    }

    protected Offer(){}

    public Integer getOfferId() {
        return offerId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Project getFkProjectId() {
        return project_id;
    }

    public void setFkProjectId(Project project_id) {
        this.project_id = project_id;
    }

    public User getFkUserId() {
        return user_id;
    }

    public void setFkUserId(User user_id) {
        this.user_id = user_id;
    }


}
