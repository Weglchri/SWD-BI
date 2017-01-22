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
    private Freelancer freelancer;

    @ManyToOne
    @JoinColumn(name = "fk_project_id")
    private Project project;

    public Offer(int price,  java.util.Date creation_date, Freelancer freelancer, Project project){
        this.creation_date = creation_date;
        setPrice(price);
        setProject(project);
        setFreelancer(freelancer);

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

    public Freelancer getFreelancer() {
    return freelancer;
}

    public void setFreelancer(Freelancer freelancer) {
        this.freelancer = freelancer;
        freelancer.addOffer(this);
    }

}
