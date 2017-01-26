package at.fhj.swd.JoinTest;

import at.fhj.swd.BusinessIntelligence.*;
import at.fhj.swd.BusinessIntelligence.Project;
import at.fhj.swd.BusinessIntelligenceRepositories.ProjectmanagerRepository;
import at.fhj.swd.Helper.JdbcHandler;
import at.fhj.swd.QueryTest.TestCompanyQuery;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by sattlerb on 24.01.2017.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestProjectmanagerJoinProjectQuery extends JdbcHandler
{
    private static Location testLocation;
    private static Company testCompany;
    private static Company testCompany1;
    private static Company testCompany2;
    private static Project testProject;
    private static Project testProject1;
    private static Project testProject2;
    private static Projectmanager testProjectmanager;

    private static final int expectedInvolvedIn = 3;


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

//    @Test
//    public void A_create() {
//        testLocation = new Location(TestLocationQuery.address, TestLocationQuery.country, TestLocationQuery.zip, TestLocationQuery.city);
//        assertNotNull(testLocation);
//
//        testCompany = new Company(TestCompanyQuery.company_name, TestCompanyQuery.branch, testLocation);
//        assertNotNull(testCompany);
//
//        testCompany1 = new Company(TestCompanyQuery.company_name1, TestCompanyQuery.branch, testLocation);
//        assertNotNull(testCompany);
//
//        testCompany2 = new Company(TestCompanyQuery.company_name2, TestCompanyQuery.branch, testLocation);
//        assertNotNull(testCompany);
//
//        testProject = new Project(TestProjectQuery.capital, TestProjectQuery.date, TestProjectQuery.task, testCompany);
//        assertNotNull(testProject);
//
//        testProject1 = new Project(TestProjectQuery.capital + 1000, TestProjectQuery.date, TestProjectQuery.task, testCompany1);
//        assertNotNull(testProject1);
//
//        testProject2 = new Project(TestProjectQuery.capital + 2000, TestProjectQuery.date, TestProjectQuery.task, testCompany2);
//        assertNotNull(testProject2);
//
//        testProjectmanager = new Projectmanager(TestUserQuery.name2, TestUserQuery.email2, TestUserQuery.password2, expectedInvolvedIn, function, testCompany);
//        assertNotNull(testProjectmanager);
//    }

    @Test
    public void B_repoTest() {
        ProjectmanagerRepository projectmanagerRepo = new ProjectmanagerRepository(manager);
        List<Project> projects = projectmanagerRepo.findAllProjectsOfProjectmanager("Someone");

        assertEquals(expectedInvolvedIn, projects.size());
    }
}
