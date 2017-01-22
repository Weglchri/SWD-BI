package at.fhj.swd.BusinessIntelligenceRepositories;

import at.fhj.swd.BusinessIntelligence.Projectmanager;
import at.fhj.swd.EntityCreator.EntityCreator;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class ProjectmanagerRepository extends EntityCreator
{
    public ProjectmanagerRepository(final EntityManager entityManager)
    {
        super( entityManager );
    }

    public Projectmanager findByName(String searchProjectmanager)
    {
        TypedQuery<Projectmanager> query =  entityManager.createNamedQuery("findProjectmanagerByName", Projectmanager.class);
        query.setParameter("name", searchProjectmanager);
        return query.getSingleResult();
    }
}
