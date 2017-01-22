package at.fhj.swd.QueryTest;

import at.fhj.swd.BusinessIntelligence.Company;
import at.fhj.swd.BusinessIntelligence.Location;
import at.fhj.swd.BusinessIntelligence.Project;
import at.fhj.swd.BusinessIntelligence.Projectmanager;
import at.fhj.swd.BusinessIntelligenceRepositories.ProjectmanagerRepository;
import at.fhj.swd.Helper.JdbcHandler;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestProjectmanagerQuery extends JdbcHandler {

    private static Location testLocation;
    private static Company testCompany;
    private static Project testProject;
    private static Projectmanager testProjectmanager;

    static final Integer involved = 1;
    static final String function = "Personal";


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
    public void A_create() {
        testLocation = new Location(TestLocationQuery.address, TestLocationQuery.country, TestLocationQuery.zip, TestLocationQuery.city);
        assertNotNull(testLocation);

        testCompany = new Company(TestCompanyQuery.company_name2, TestCompanyQuery.branch, testLocation);
        assertNotNull(testCompany);

        testProject = new Project(TestProjectQuery.capital, TestProjectQuery.date, TestProjectQuery.task, testCompany);
        assertNotNull(testProject);

        testProjectmanager = new Projectmanager(TestUserQuery.name2, TestUserQuery.email2, TestUserQuery.password2, involved, function, testCompany);
        assertNotNull(testProjectmanager);

    }

    @Test
    public void B_repoTest() {
        ProjectmanagerRepository projectmanagerRepo = new ProjectmanagerRepository(manager);
        Projectmanager testProjectmanager1 = projectmanagerRepo.findByName(TestUserQuery.name2);

        assertEquals(testProjectmanager.getName(), testProjectmanager1.getName());
        assertEquals(testProjectmanager.getEmail(), testProjectmanager1.getEmail());
        assertEquals(testProjectmanager.getPassword(), testProjectmanager1.getPassword());
        assertEquals(testProjectmanager.getInvolvedIn(), testProjectmanager1.getInvolvedIn());
        assertEquals(testProjectmanager.getFunction(), testProjectmanager1.getFunction());

    }

}

