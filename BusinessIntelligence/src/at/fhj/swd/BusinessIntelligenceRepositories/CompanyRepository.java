package at.fhj.swd.BusinessIntelligenceRepositories;

import at.fhj.swd.BusinessIntelligence.Company;
import at.fhj.swd.BusinessIntelligence.Projectmanager;
import at.fhj.swd.EntityCreator.EntityCreator;
import org.eclipse.persistence.internal.jpa.JPAQuery;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CompanyRepository extends EntityCreator
{
    public CompanyRepository(final EntityManager entityManager)
    {
        super(entityManager);
    }

    public Company findByName(String searchCompany)
    {
        TypedQuery<Company> query =  entityManager.createNamedQuery("findCompanyByName", Company.class);
        query.setParameter("companyName", searchCompany);
        return query.getSingleResult();
    }

    public List<Company> findByBranch(String searchBranch)
    {
        TypedQuery<Company> query =  entityManager.createNamedQuery("findCompaniesByBranche", Company.class);
        query.setParameter("branch", searchBranch);
        return query.getResultList();
    }

    public List<Projectmanager> findAllProjectManagersOfCompany(String companyName)
    {
        TypedQuery<Projectmanager> query = entityManager.createNamedQuery("findAllProjectmanagersOfCompany", Projectmanager.class);
        query.setParameter("name", companyName);
        return query.getResultList();
    }
}
