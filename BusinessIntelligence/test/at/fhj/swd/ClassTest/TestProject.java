package at.fhj.swd.ClassTest;

import at.fhj.swd.BusinessIntelligence.Company;
import at.fhj.swd.BusinessIntelligence.Location;
import at.fhj.swd.BusinessIntelligence.Project;
import at.fhj.swd.BusinessIntelligence.Projectmanager;
import at.fhj.swd.Helper.JdbcHandler;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestProject extends JdbcHandler {

    private static Location testAddress;
    private static Company testCompany;
    private static Projectmanager testProjectmanager;
    private static Project testProject;

    static final Integer capital = 1000;
    static final String task = "Website-Programming";
    static final String newTask = "Database-Design";
    static final Date date = new Date();


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

        testAddress = new Location(TestLocation.address, TestLocation.country, TestLocation.zip, TestLocation.city);
        assertNotNull(testAddress);
        manager.persist(testAddress);

        testCompany = new Company(TestCompany.company_name, TestCompany.branch, testAddress);
        assertNotNull(testCompany);
        manager.persist(testCompany);

        testProjectmanager = new Projectmanager(TestUser.name, TestUser.email, TestUser.password, TestProjectmanager.involved, TestProjectmanager.function, testCompany);
        assertNotNull(testProjectmanager);
        manager.persist(testProjectmanager);

        testProject = new Project (capital, date, task, testCompany);
        assertNotNull(testProject);
        manager.persist(testProject);

        testProjectmanager.addProject(testProject);

        transaction.commit();

    }

    @Test
    public void modify() {
        transaction.begin();

        assertNotNull(testAddress);
        assertNotNull(testCompany);
        assertNotNull(testProjectmanager);
        assertNotNull(testProject);

        Project merge = manager.merge(testProject);
        merge.setTask(newTask);

        transaction.commit();

        testProject = manager.find(Project.class, testProject.getProjectId());
        assertEquals(newTask, testProject.getTask());
    }

    @Test
    public void remove() {
        transaction.begin();

        manager.remove(testProjectmanager);
        manager.remove(testCompany);
        manager.remove(testAddress);
        manager.remove(testProject);

        transaction.commit();

        testAddress = manager.find(Location.class, testAddress.getAddress());
        testCompany= manager.find(Company.class, testCompany.getCompany());
        testProjectmanager = manager.find(Projectmanager.class, testProjectmanager.getUserId());
        testProject = manager.find(Project.class, testProject.getProjectId());

        assertNull(testAddress);
        assertNull(testCompany);
        assertNull(testProjectmanager);
        assertNull(testProject);

    }

}
