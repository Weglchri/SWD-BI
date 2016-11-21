package at.fhj.swd.BusinessIntelligence;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Created by sattlerb on 21/11/16.
 */
public class ProjectmanagerRepository
{
    public ProjectmanagerRepository(final EntityManager entityManager)
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


    public Projectmanager findByName(String searchProjectmanager)
    {
        TypedQuery<Projectmanager> query =  entityManager.createNamedQuery("findByName", Projectmanager.class);
        query.setParameter("name", searchProjectmanager);
        return query.getSingleResult();
    }
}
