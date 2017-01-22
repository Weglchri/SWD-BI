package at.fhj.swd.BusinessIntelligenceRepositories;

import at.fhj.swd.BusinessIntelligence.User;
import at.fhj.swd.EntityCreator.EntityCreator;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class UserRepository extends EntityCreator
{
    public UserRepository(final EntityManager entityManager)
    {
        super(entityManager);
    }

    public User findByName(String searchName)
    {
            TypedQuery<User> query =  entityManager.createNamedQuery("findByName", User.class);
            query.setParameter("name", searchName);
            return query.getSingleResult();
    }
}
