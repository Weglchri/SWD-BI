package at.fhj.swd.BusinessIntelligence;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Created by sattlerb on 21/11/16.
 */
public class FreelancerRepository
{
    public FreelancerRepository(final EntityManager entityManager)
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


    public Freelancer findByName(String searchFreelancer)
    {
        TypedQuery<Freelancer> query =  entityManager.createNamedQuery("findFreelancerByName", Freelancer.class);
        query.setParameter("name", searchFreelancer);
        return query.getSingleResult();
    }

}
