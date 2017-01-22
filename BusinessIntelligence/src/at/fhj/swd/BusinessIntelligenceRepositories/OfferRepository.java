package at.fhj.swd.BusinessIntelligenceRepositories;

import at.fhj.swd.BusinessIntelligence.Offer;
import at.fhj.swd.EntityCreator.EntityCreator;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


public class OfferRepository extends EntityCreator
{
    public OfferRepository(final EntityManager entityManager)
    {
        super( entityManager );
    }

    public Offer findByName(Integer price)
    {
        TypedQuery<Offer> query =  entityManager.createNamedQuery("findByPrice", Offer.class);
        query.setParameter("price", price);
        return query.getSingleResult();
    }
}
