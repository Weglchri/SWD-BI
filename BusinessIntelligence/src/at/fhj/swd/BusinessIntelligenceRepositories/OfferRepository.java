package at.fhj.swd.BusinessIntelligenceRepositories;

import at.fhj.swd.BusinessIntelligence.Freelancer;
import at.fhj.swd.BusinessIntelligence.Offer;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Created by sattlerb on 21/11/16.
 */
public class OfferRepository
{
    public OfferRepository(final EntityManager entityManager)
    {
        setEntityManager( entityManager );
    }

    private EntityManager entityManager;
    public void setEntityManager(final EntityManager entityManager)
    {
        if(entityManager == null)
            throw new IllegalArgumentException("EntityManager is invalid!");

        this.entityManager = entityManager;
    }


    public Offer findByName(Integer price)
    {
        //results in our case a single result
        TypedQuery<Offer> query =  entityManager.createNamedQuery("findByPrice", Offer.class);
        query.setParameter("price", price);
        return query.getSingleResult();
    }
}
