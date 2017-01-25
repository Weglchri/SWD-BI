package at.fhj.swd.BusinessIntelligenceRepositories;

import at.fhj.swd.BusinessIntelligence.Project;
import at.fhj.swd.EntityCreator.EntityCreator;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProjectRepository extends EntityCreator
{
    public ProjectRepository(final EntityManager entityManager)
    {
        super( entityManager );
    }

    public Project findByTask(String searchName)
    {
        TypedQuery<Project> query =  entityManager.createNamedQuery("findByTask", Project.class);
        query.setParameter("task", searchName);
        return query.getSingleResult();
    }

    public List<Project> findProjectManagerByProjectType(String projectType)
    {
        TypedQuery<Project> query  = entityManager.createNamedQuery("findByProjectType", Project.class);
        query.setParameter("projectType", projectType);
        return query.getResultList();
    }
}
