package at.fhj.swd.BusinessIntelligenceRepositories;

import at.fhj.swd.BusinessIntelligence.Location;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class LocationRepository
{
    public LocationRepository(final EntityManager entityManager)
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


    public List<Location> findByCountry(String searchCountry)
    {
        TypedQuery<Location> query =  entityManager.createNamedQuery("findLocationByCountry", Location.class);
        query.setParameter("country", searchCountry);
        return query.getResultList();
    }

    public Location findByAddress(String searchAddress)
    {
        TypedQuery query =  entityManager.createNamedQuery("findLocationByAddress", Location.class);
        query.setParameter("address", searchAddress);
        return (Location) query.getSingleResult();
    }

    public List<Location> findAll()
    {
        TypedQuery<Location> query =  entityManager.createNamedQuery("findAll", Location.class);
        return query.getResultList();
    }
}
