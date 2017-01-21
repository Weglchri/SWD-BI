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
    private static Projectmanager projectmanager1;
    private static Location testLocation;
    private static Company testCompany;
    private static Project projectWebsite;


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

        projectmanager1 = new Projectmanager(TestUser.name, TestUser.email, TestUser.password, TestProjectmanager.involved, TestProjectmanager.function, testCompany);
        assertNotNull(projectmanager1);
        manager.persist(projectmanager1);

        projectWebsite = new Project(TestProject.capital, TestProject.date, TestProject.task, testCompany);
        assertNotNull(projectWebsite);
        manager.persist(projectWebsite);

        projectmanager1.addProject(projectWebsite);

        transaction.commit();

        assertTrue(projectmanager1.getProjects().contains(projectWebsite));
        assertTrue(projectWebsite.getProjectmanagers().contains(projectmanager1));

    }

    @Test
    public void remove() {

        transaction.begin();

        manager.remove(projectmanager1);
        manager.remove(projectWebsite);

        transaction.commit();

        projectWebsite = manager.find(Project.class, projectWebsite.getProjectId());
        projectmanager1 = manager.find(Projectmanager.class, projectmanager1.getUserId());

        assertNull(projectmanager1);
        assertNull(projectWebsite);


    }

}

