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

    private static Location testLocation;
    private static Location testLocation1;
    private static Location testLocation2;
    private static Company testCompany;
    private static Company testCompany1;
    private static Company testCompany2;

    static final String company_name = "Stahl Incorporation";
    static final String branch = "Stahlbau";

    static final String company_name1 = "Orange GmbH";
    static final String branch1 = "Bergbau";

    static final String company_name2 = "Diamond Mine Coorporation GmbH";
    static final String branch2 = "Bergbau";


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
    public void A_createObjects() {

        testLocation = new Location(TestLocationQuery.address, TestLocationQuery.country, TestLocationQuery.zip, TestLocationQuery.city);
        assertNotNull(testLocation);

        testLocation1 = new Location(TestLocationQuery.address1, TestLocationQuery.country1, TestLocationQuery.zip1, TestLocationQuery.city1);
        assertNotNull(testLocation1);

        testLocation2 = new Location(TestLocationQuery.address2, TestLocationQuery.country2, TestLocationQuery.zip2, TestLocationQuery.city2);
        assertNotNull(testLocation2);

        testCompany = new Company(company_name, branch, testLocation);
        assertNotNull(testCompany);

        testCompany1 = new Company(company_name1, branch1, testLocation1);
        assertNotNull(testCompany1);

        testCompany2 = new Company(company_name2, branch2, testLocation2);
        assertNotNull(testCompany2);

    }

    @Test
    public void B_repoTest() {
        CompanyRepository companyRepo = new CompanyRepository(manager);
        Company testCompany1 = companyRepo.findByName(company_name);
        Company testCompany2 = companyRepo.findByName(company_name1);
        Company testCompany3 = companyRepo.findByName(company_name2);

        assertEquals(testCompany1.getCompany(), testCompany1.getCompany());
        assertEquals(testCompany1.getBranch(), testCompany1.getBranch());
        assertEquals(testCompany1.getAddress().getAddress(), testCompany1.getAddress().getAddress());

        assertEquals(testCompany2.getCompany(), testCompany2.getCompany());
        assertEquals(testCompany2.getBranch(), testCompany2.getBranch());
        assertEquals(testCompany2.getAddress().getAddress(), testCompany2.getAddress().getAddress());

        assertEquals(testCompany3.getCompany(), testCompany3.getCompany());
        assertEquals(testCompany3.getBranch(), testCompany3.getBranch());
        assertEquals(testCompany3.getAddress().getAddress(), testCompany3.getAddress().getAddress());


    }

    @Test
    public void C_repoTestGetBranche() {
        CompanyRepository companyRepo = new CompanyRepository(manager);
        List<Company> companyBranchSearch = companyRepo.findByBranch(branch);
        List<Company> companyBranchSearch1 = companyRepo.findByBranch(branch1);

        assertEquals(1, companyBranchSearch.size());
        assertEquals( testCompany.getBranch() ,companyBranchSearch.get(0).getBranch());

        assertEquals(2, companyBranchSearch1.size());
        assertEquals( testCompany1.getBranch() ,companyBranchSearch1.get(0).getBranch());
        assertEquals( testCompany2.getBranch() ,companyBranchSearch1.get(1).getBranch());

    }


}
