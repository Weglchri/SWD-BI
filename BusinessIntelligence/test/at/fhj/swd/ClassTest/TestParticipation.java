package at.fhj.swd.ClassTest;

import at.fhj.swd.BusinessIntelligence.Company;
import at.fhj.swd.BusinessIntelligence.Location;
import at.fhj.swd.BusinessIntelligence.Project;
import at.fhj.swd.BusinessIntelligence.Projectmanager;
import at.fhj.swd.Helper.JdbcHandler;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.*;

public class TestParticipation extends JdbcHandler
{
    private static Projectmanager testProjectmanager;
    private static Location testLocation;
    private static Company testCompany;
    private static Project testProject;


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
    public void create()
    {
        transaction.begin();

        testLocation = new Location(TestLocation.address, TestLocation.country, TestLocation.zip, TestLocation.city);
        assertNotNull(testLocation);
        manager.persist(testLocation);

        testCompany = new Company(TestCompany.company_name, TestCompany.branch, testLocation);
        assertNotNull(testCompany);
        manager.persist(testCompany);

        testProjectmanager = new Projectmanager(TestUser.name, TestUser.email, TestUser.password, TestProjectmanager.involved, TestProjectmanager.function, testCompany);
        assertNotNull(testProjectmanager);
        manager.persist(testProjectmanager);

        testProject = new Project(TestProject.capital, TestProject.date, TestProject.task, testCompany);
        assertNotNull(testProject);
        manager.persist(testProject);

        testProjectmanager.addProject(testProject);

        transaction.commit();

        assertTrue(testProjectmanager.getProjects().contains(testProject));
        assertTrue(testProject.getProjectmanagers().contains(testProjectmanager));

    }

    @Test
    public void remove() {

        transaction.begin();

        manager.remove(testProjectmanager);
        manager.remove(testProject);

        transaction.commit();

        testProject = manager.find(Project.class, testProject.getProjectId());
        testProjectmanager = manager.find(Projectmanager.class, testProjectmanager.getUserId());

        assertNull(testProjectmanager);
        assertNull(testProject);


    }

}

