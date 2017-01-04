package at.fhj.swd.QueryTest;

import at.fhj.swd.BusinessIntelligence.Company;
import at.fhj.swd.BusinessIntelligence.Location;
import at.fhj.swd.BusinessIntelligence.Project;
import at.fhj.swd.BusinessIntelligenceRepositories.ProjectRepository;
import at.fhj.swd.Helper.JdbcHandler;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestProjectQuery extends JdbcHandler{

    private static Location testAddress;
    private static Company testCompany;
    private static Project testProject;

    Date date = new Date();
    static final Integer capital = 1000;
    static final String task = "Turbinenherstellung";


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

        testCompany = new Company(TestCompanyQuery.company_name, TestCompanyQuery.branch, testAddress);
        assertNotNull(testCompany);
        manager.persist(testCompany);

        testProject = new Project (capital, date, task, testCompany);
        assertNotNull(testProject);
        manager.persist(testProject);

        transaction.commit();
    }

    @Test
    public void B_repoTest() {
        ProjectRepository projectRepo = new ProjectRepository(manager);
        Project testProjectRepo = projectRepo.findByTask(task);

        assertEquals(testProjectRepo.getCompanyName(), testProject.getCompanyName());
        assertEquals(testProjectRepo.getCapital(), testProject.getCapital());
        assertEquals(testProjectRepo.getProjectId(), testProject.getProjectId());
        assertEquals(testProjectRepo.getTask(), testProjectRepo.getTask());
    }

    @Test
    public void C_remove() {
        transaction.begin();

        assertNotNull(testProject);
        assertNotNull(testCompany);
        assertNotNull(testAddress);
        manager.remove(testProject);
        manager.remove(testCompany);
        manager.remove(testAddress);

        transaction.commit();
        testProject = manager.find(Project.class, testProject.getProjectId());
        testCompany = manager.find(Company.class, testCompany.getCompany());
        testAddress = manager.find(Location.class, testAddress.getAddress());
        assertNull(testProject);
        assertNull(testCompany);
        assertNull(testAddress);
    }

}
