package at.fhj.swd.QueryTest;

import at.fhj.swd.BusinessIntelligence.Company;
import at.fhj.swd.BusinessIntelligence.Location;
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
import static org.junit.Assert.assertNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestProjectmanagerQuery extends JdbcHandler {

    private static Location testAddress;
    private static Company testCompany;
    private static Projectmanager testProjectmanager;

    static final Integer involved = 1;
    static final String function = "Personal";


    @BeforeClass
    public static void setup() {
        JdbcHandler.build();
        JdbcHandler.init();
    }

    @AfterClass
    public static void teardown() {
        JdbcHandler.close();
        JdbcHandler.dropTables();
    }

    @Test
    public void A_create() {
        transaction.begin();

        testAddress = new Location(TestLocationQuery.address, TestLocationQuery.country, TestLocationQuery.zip, TestLocationQuery.city);
        assertNotNull(testAddress);
        manager.persist(testAddress);

        testCompany = new Company(TestCompanyQuery.company_name, TestCompanyQuery.branch, testAddress);
        assertNotNull(testCompany);
        manager.persist(testCompany);

        testProjectmanager = new Projectmanager(TestUserQuery.name, TestUserQuery.email, TestUserQuery.password, involved, function, testCompany);
        assertNotNull(testProjectmanager);
        manager.persist(testProjectmanager);

        transaction.commit();
    }

    @Test
    public void B_repoTest() {
        ProjectmanagerRepository projectmanagerRepo = new ProjectmanagerRepository(manager);
        Projectmanager testProjectmanager1 = projectmanagerRepo.findByName(TestUserQuery.name);

        assertEquals(testProjectmanager1.getName(), testProjectmanager.getName());
        assertEquals(testProjectmanager1.getEmail(), testProjectmanager.getEmail());
        assertEquals(testProjectmanager1.getPassword(), testProjectmanager.getPassword());
        assertEquals(testProjectmanager1.getUserId(), testProjectmanager.getUserId());
        assertEquals(testProjectmanager1.getProjects(), testProjectmanager.getProjects());
        assertEquals(testProjectmanager1.getInvolvedIn(), testProjectmanager.getInvolvedIn());
        assertEquals(testProjectmanager1.getCompanyName(), testProjectmanager.getCompanyName());
        assertEquals(testProjectmanager1.getFunction(), testProjectmanager.getFunction());
    }

    @Test
    public void C_remove() {
        transaction.begin();

        assertNotNull(testAddress);
        assertNotNull(testCompany);
        assertNotNull(testProjectmanager);

        manager.remove(testAddress);
        manager.remove(testCompany);
        manager.remove(testProjectmanager);

        transaction.commit();

        testProjectmanager = manager.find(Projectmanager.class, testProjectmanager.getUserId());
        assertNull(testProjectmanager);
    }

}
