package at.fhj.swd.QueryTest;

import at.fhj.swd.BusinessIntelligence.Company;
import at.fhj.swd.BusinessIntelligenceRepositories.CompanyRepository;
import at.fhj.swd.BusinessIntelligence.Location;
import at.fhj.swd.Helper.JdbcHandler;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCompanyQuery extends JdbcHandler {

    private static Location testAddress;
    private static Location testAddress1;
    private static Company testCompany;
    private static Company testCompany1;
    private static List<Company> companyBranchSearch;

    static final String company_name = "Stahl Incorporation";
    static final String branch = "Stahlbau";

    static final String company_name1 = "Orange Incorporation GmbH";
    static final String branch1 = "Bergbau";


    @BeforeClass
    public static void setup() {
        JdbcHandler.build();
        JdbcHandler.init();
    }

    @AfterClass
    public static void teardown() {
        JdbcHandler.close();
        JdbcHandler.destroy();
    }

    @Test
    public void A_create() {
        transaction.begin();

        testAddress = new Location(TestLocationQuery.address, TestLocationQuery.country, TestLocationQuery.zip, TestLocationQuery.city);
        assertNotNull(testAddress);
        manager.persist(testAddress);

        testAddress1 = new Location(TestLocationQuery.address1, TestLocationQuery.country1, TestLocationQuery.zip1, TestLocationQuery.city1);
        assertNotNull(testAddress1);
        manager.persist(testAddress1);

        testCompany = new Company(company_name, branch, testAddress);
        assertNotNull(testCompany);
        manager.persist(testCompany);

        testCompany1 = new Company(company_name1, branch1, testAddress1);
        assertNotNull(testCompany1);
        manager.persist(testCompany1);

        transaction.commit();
    }

    @Test
    public void B_repoTest() {
        CompanyRepository companyRepo = new CompanyRepository(manager);
        CompanyRepository companyRepo1 = new CompanyRepository(manager);

        Company companySearch1 = companyRepo.findByName(company_name);
        Company companySearch2 = companyRepo1.findByName(company_name1);

        assertEquals(companySearch1.getBranch(), testCompany.getBranch());
        assertEquals(companySearch1.getCompany(), testCompany.getCompany());
        assertEquals(companySearch1.getAddress(), testCompany.getAddress());

        assertEquals(companySearch2.getBranch(), testCompany1.getBranch());
        assertEquals(companySearch2.getCompany(), testCompany1.getCompany());
        assertEquals(companySearch2.getAddress(), testCompany1.getAddress());
    }

    @Test
    public void C_repoTestGetBranche() {
        CompanyRepository companyRepo = new CompanyRepository(manager);
        companyBranchSearch = companyRepo.findByBranch(branch);

        assertEquals(2, companyBranchSearch.size());
    }

    @Test
    public void D_remove() {
        transaction.begin();

        Company testCompany = manager.find(Company.class, company_name);
        Company testCompany1 = manager.find(Company.class, company_name1);
        assertNotNull(testCompany);
        assertNotNull(testCompany1);

        Location testAddress = manager.find(Location.class, TestLocationQuery.address);
        Location testAddress1 = manager.find(Location.class, TestLocationQuery.address1);
        assertNotNull(testAddress);
        assertNotNull(testAddress1);

        manager.remove(testCompany);
        manager.remove(testCompany1);
        manager.remove(testAddress);
        manager.remove(testAddress1);

        transaction.commit();

        testAddress = manager.find(Location.class, TestLocationQuery.address);
        testAddress1 = manager.find(Location.class, TestLocationQuery.address1);
        assertEquals(null, testAddress);
        assertEquals(null, testAddress1);

        testCompany = manager.find(Company.class, company_name);
        testCompany1 = manager.find(Company.class, company_name1);
        assertNull(testAddress);
        assertNull(testAddress1);
        assertNull(testCompany);
        assertNull(testCompany1);
    }

}
