package at.fhj.swd.BusinessIntelligence;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class UserRepository
{
    public UserRepository(final EntityManager entityManager)
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

    public User findByName(String searchName)
    {
            TypedQuery<User> query =  entityManager.createNamedQuery("findByName", User.class);
            query.setParameter("name", searchName);
            return query.getSingleResult();
    }
}
