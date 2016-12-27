package at.fhj.swd.ClassTest;

import at.fhj.swd.BusinessIntelligence.Company;
import at.fhj.swd.BusinessIntelligence.Location;
import at.fhj.swd.BusinessIntelligence.Project;
import at.fhj.swd.BusinessIntelligence.Projectmanager;
import at.fhj.swd.Helper.Handler;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestProject extends Handler{

    private static Location testAddress;
    private static Company testCompany;
    private static Projectmanager testProjectmanager;
    private static Project testProject;


    @BeforeClass
    public static void setup() {
        Handler.build();
        Handler.init();
    }

    @AfterClass
    public static void teardown() {
        Handler.close();
        Handler.destroy();
    }

    @Test
    public void create()
    {
        transaction.begin();

        testAddress = new Location(address, country, zip, city);
        assertNotNull(testAddress);
        manager.persist(testAddress);

        testCompany = new Company(company_name, branch, testAddress);
        assertNotNull(testCompany);
        manager.persist(testCompany);

        testProjectmanager = new Projectmanager(name, email, password, involved, function, testCompany);
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
