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


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestProjectQuery extends JdbcHandler{

    private static Location testLocation;
    private static Company testCompany;
    private static Project testProject;

    static final Date date = new Date();
    static final Integer capital = 1000;
    static final String task = "Turbinenherstellung";


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

        testCompany = new Company(TestCompanyQuery.company_name, TestCompanyQuery.branch, testLocation);
        assertNotNull(testCompany);

        testProject = new Project (capital, date, task, testCompany);
        assertNotNull(testProject);

    }

    @Test
    public void B_repoTest() {
        ProjectRepository projectRepo = new ProjectRepository(manager);
        Project testProject1 = projectRepo.findByTask(task);

        assertEquals(testProject.getCapital(), testProject1.getCapital());
        assertEquals(testProject.getTask(), testProject1.getTask());

    }
}
