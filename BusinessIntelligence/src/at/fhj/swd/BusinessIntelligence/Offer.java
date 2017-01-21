package at.fhj.swd.BusinessIntelligence;

import javax.persistence.*;


@Entity
@Table(name="Offer", schema="public")
@NamedQuery(name="findByPrice", query="SELECT o FROM Offer o WHERE o.price = :price")

public class Offer {
    @Id @Column(name = "offer_id") @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer offerId;
        private Integer price;

    @Column(name="creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date creation_date;

    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    public User user;

    @ManyToOne
    @JoinColumn(name = "fk_project_id")
    public Project project;

    public Offer(int price,  java.util.Date creation_date, User user, Project project){
        this.creation_date = creation_date;
        setPrice(price);
        setProject(project);
        setUser(user);

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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
        project.addOffer(this);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        user.addOffer(this);
    }

}
