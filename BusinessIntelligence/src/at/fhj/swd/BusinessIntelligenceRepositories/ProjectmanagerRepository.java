package at.fhj.swd.BusinessIntelligenceRepositories;

import at.fhj.swd.BusinessIntelligence.Project;
import at.fhj.swd.BusinessIntelligence.Projectmanager;
import at.fhj.swd.EntityCreator.EntityCreator;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

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

    public List<Project> findProjectManagerByProjectType(String task)
    {
        TypedQuery<Project> query  = entityManager.createNamedQuery("findProjectManagerByProjectType", Project.class);
        query.setParameter("task", task);
        return query.getResultList();
    }
}
