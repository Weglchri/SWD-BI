package at.fhj.swd.BusinessIntelligence;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by sattlerb on 21/11/16.
 */
public class CompanyRepository
{
    public CompanyRepository(final EntityManager entityManager)
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
}
