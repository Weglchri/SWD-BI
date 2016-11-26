package at.fhj.swd.BusinessIntelligence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by sattlerb on 26/11/16.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestProjectQuery
{
    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "BusinessIntelligence";

    //data for Company
    static final String company_name = "Andritz AG";
    static final String branch = "Maschinenbau";

    //data for Location
    static final String address = "Andritzer Reichsstraße 1";
    static final String country = "Austria";
    static final Integer zip = 8041;
    static final String city = "Andritz";


    //data for Project
    static final Integer capital = 1000;
    Date date = new Date();
    static final String task = "Turbinenherstellung";
    static final String taskMerge = "Database-Design";

    //date for tests
    private static Location testAddress;
    private static Company testCompany;
    private static Project testProject;


    @BeforeClass
    public static void setup() {
        factory = Persistence.createEntityManagerFactory(persistenceUnitName);
        assertNotNull(factory);
        manager = factory.createEntityManager();
        assertNotNull(manager);
        transaction = manager.getTransaction();
    }

    @AfterClass
    public static void teardown() {
        if (manager == null) return;
        manager.close();
        factory.close();
    }

    @Test
    public void A_create()
    {
        transaction.begin();

        this.testAddress = new Location(address, country, zip, city);
        assertNotNull(testAddress);
        manager.persist(testAddress);

        this.testCompany = new Company(company_name, branch, testAddress);
        assertNotNull(testCompany);
        manager.persist(testCompany);

        this.testProject = new Project (capital, date, task, testCompany);
        assertNotNull(testProject);
        manager.persist(testProject);

        transaction.commit();
    }

    @Test
    public void B_repoTest()
    {
        ProjectRepository projectRepo = new ProjectRepository(manager);
        Project testProjectRepo = projectRepo.findByTask(task);

        assertEquals(testProjectRepo.getCompanyName(), testProject.getCompanyName());
        assertEquals(testProjectRepo.getCapital(), testProject.getCapital());
        assertEquals(testProjectRepo.getProjectId(), testProject.getProjectId());
        assertEquals(testProjectRepo.getTask(), testProjectRepo.getTask());
    }
    @Test
    public void C_remove()
    {
        transaction.begin();

        assertNotNull(testProject);
        assertNotNull(testCompany);
        assertNotNull(testAddress);
        manager.remove(testProject);
        manager.remove(testCompany);
        manager.remove(testAddress);

        transaction.commit();
        this.testProject = manager.find(Project.class, testProject.getProjectId());
        this.testCompany = manager.find(Company.class, testCompany.getCompany());
        this.testAddress = manager.find(Location.class, testAddress.getAddress());
        assertNull(testProject);
        assertNull(testCompany);
        assertNull(testAddress);
    }

}
