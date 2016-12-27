package at.fhj.swd.BusinessIntelligence;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class ProjectRepository
{
    public ProjectRepository(final EntityManager entityManager)
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

    public Project findByTask(String searchName)
    {
        TypedQuery<Project> query =  entityManager.createNamedQuery("findByTask", Project.class);
        query.setParameter("task", searchName);
        return query.getSingleResult();
    }
}
