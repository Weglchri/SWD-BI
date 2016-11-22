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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by sattlerb on 21/11/16.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestProjectmanagerQuery
{

    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "BusinessIntelligence";

    //data for Company
    static final String company_name = "Stahl Incorporation AG";
    static final String branch = "Stahlbau";

    //data for Location
    static final String address = "Alte Poststraße 122/16";
    static final String country = "Austria";
    static final Integer zip = 8020;
    static final String city = "Graz";

    //data for User
    static final int id = 1;
    static final String name = "Björn Sattler";
    static final String email = "bjoern.sattler@edu.fh-joanneum.at";
    static final String password = "*******";

    //data for Projectmanager
    static final Integer involved = 1;
    static final String function = "Personal";
    static final String functionMerge = "Zulieferung";


    //date for tests
    private static Location testAddress;
    private static Company testCompany;
    private static Projectmanager testProjectmanager;



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

        this.testProjectmanager = new Projectmanager(name, email, password, involved, function, testCompany);
        assertNotNull(testProjectmanager);
        manager.persist(testProjectmanager);

        transaction.commit();
    }


    @Test
    public void B_repoTest()
    {
        ProjectmanagerRepository projectmanagerRepo = new ProjectmanagerRepository(manager);
        Projectmanager projectmanager1 = projectmanagerRepo.findByName(name);

        assertEquals(projectmanager1.getName(), testProjectmanager.getName());
        assertEquals(projectmanager1.getEmail(), testProjectmanager.getEmail());
        assertEquals(projectmanager1.getPassword(), testProjectmanager.getPassword());
        assertEquals(projectmanager1.getUserId(), testProjectmanager.getUserId());
        assertEquals(projectmanager1.getProjects(), testProjectmanager.getProjects());
        assertEquals(projectmanager1.getInvolvedIn(), testProjectmanager.getInvolvedIn());
        assertEquals(projectmanager1.getCompanyName(), testProjectmanager.getCompanyName());
        assertEquals(projectmanager1.getFunction(), testProjectmanager.getFunction());
    }
    @Test
    public void C_remove()
    {
        transaction.begin();

        assertNotNull(testAddress);
        assertNotNull(testCompany);
        assertNotNull(testProjectmanager);

        manager.remove(testAddress);
        manager.remove(testCompany);
        manager.remove(testProjectmanager);

        transaction.commit();

        this.testProjectmanager = manager.find(Projectmanager.class, testProjectmanager.getUserId());
        assertNull(testProjectmanager);
    }

}
