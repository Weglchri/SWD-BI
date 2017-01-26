package at.fhj.swd.BusinessIntelligenceRepositories;

import at.fhj.swd.BusinessIntelligence.Company;
import at.fhj.swd.BusinessIntelligence.Location;
import at.fhj.swd.EntityCreator.EntityCreator;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class LocationRepository extends EntityCreator
{
    public LocationRepository(final EntityManager entityManager)
    {
        super(entityManager);
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

    public Company findCompanyByLocation(String address)
    {
        TypedQuery<Company> query = entityManager.createNamedQuery("findCompanyByLocation", Company.class);
        query.setParameter("address", address);
        return query.getSingleResult();
    }
}
