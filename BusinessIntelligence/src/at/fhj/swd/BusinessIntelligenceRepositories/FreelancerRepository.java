package at.fhj.swd.BusinessIntelligenceRepositories;

import at.fhj.swd.BusinessIntelligence.Freelancer;
import at.fhj.swd.BusinessIntelligence.Offer;
import at.fhj.swd.EntityCreator.EntityCreator;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class FreelancerRepository extends EntityCreator
{
    public FreelancerRepository(final EntityManager entityManager)
    {
        super(entityManager);
    }

    public Freelancer findByName(String searchFreelancer)
    {
        TypedQuery<Freelancer> query =  entityManager.createNamedQuery("findFreelancerByName", Freelancer.class);
        query.setParameter("name", searchFreelancer);
        return query.getSingleResult();
    }

    public List<Offer> getAllOffersByFreelancer(String freelancer)
    {
        TypedQuery<Offer> query = entityManager.createNamedQuery("findAllOffersByFreelancer", Offer.class);
        query.setParameter("worker", freelancer);
        return query.getResultList();
    }
}
