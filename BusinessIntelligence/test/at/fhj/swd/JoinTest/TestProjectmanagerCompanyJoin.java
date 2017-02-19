package at.fhj.swd.JoinTest;

import at.fhj.swd.BusinessIntelligence.Projectmanager;
import at.fhj.swd.BusinessIntelligenceRepositories.CompanyRepository;
import at.fhj.swd.Helper.JdbcHandler;
import at.fhj.swd.QueryTest.TestCompanyQuery;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestProjectmanagerCompanyJoin extends JdbcHandler
{
    @BeforeClass
    public static void setup() {
        JdbcHandler.build();
        JdbcHandler.insert();
        JdbcHandler.init();
    }

    @AfterClass
    public static void teardown() {
        JdbcHandler.close();
        JdbcHandler.dropTables();
    }

    @Test
    public void getAllProjectmanagersOfCompany() {
        CompanyRepository companyRepo = new CompanyRepository(manager);
        List<Projectmanager> projectManagers = companyRepo.findAllProjectManagersOfCompany(TestCompanyQuery.company_name);

        assertEquals(2, projectManagers.size());

        for (Projectmanager m : projectManagers)
        {
            assertEquals(TestCompanyQuery.company_name, m.getCompany().getCompanyName());
        }
    }
}
