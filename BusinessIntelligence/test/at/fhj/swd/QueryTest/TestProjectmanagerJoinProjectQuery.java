package at.fhj.swd.QueryTest;

import at.fhj.swd.BusinessIntelligence.*;
import at.fhj.swd.BusinessIntelligenceRepositories.ProjectRepository;
import at.fhj.swd.Helper.JdbcHandler;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by sattlerb on 24.01.2017.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestProjectmanagerJoinProjectQuery extends JdbcHandler
{
    private static Location testLocation;
    private static Company testCompany;
    private static Project testProject;
    private static Project testProject1;
    private static Project testProject2;
    private static Projectmanager testProjectmanager;

    static final Integer involved = 3;
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

        testProject1 = new Project(TestProjectQuery.capital + 1000, TestProjectQuery.date, TestProjectQuery.task, testCompany);
        assertNotNull(testProject);

        testProject2 = new Project(TestProjectQuery.capital + 2000, TestProjectQuery.date, TestProjectQuery.task, testCompany);
        assertNotNull(testProject);

        testProjectmanager = new Projectmanager(TestUserQuery.name2, TestUserQuery.email2, TestUserQuery.password2, involved, function, testCompany);
        assertNotNull(testProjectmanager);
    }

    @Test
    public void B_repoTest() {
        ProjectRepository projectRepo = new ProjectRepository(manager);
        List<Project> projects = projectRepo.findProjectManagerByProjectType(TestProjectQuery.task);

        assertEquals(3, projects.size());
    }
}
