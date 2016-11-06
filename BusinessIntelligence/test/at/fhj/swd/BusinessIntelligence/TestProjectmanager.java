package at.fhj.swd.BusinessIntelligence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestProjectmanager {


    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "BusinessIntelligence";

    //data for Company
    static final String company_name = "Stahl Incorporation";
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
    static final String dtype = "Projectmanager";

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
    public void create()
    {
        transaction.begin();

        this.testAddress = new Location(address, country, zip, city);
        assertNotNull(testAddress);
        manager.persist(testAddress);

        this.testCompany = new Company(company_name, branch, testAddress);
        assertNotNull(testCompany);
        manager.persist(testCompany);

        this.testProjectmanager = new Projectmanager(name, email, password, dtype, involved, function, testCompany);
        assertNotNull(testProjectmanager);
        manager.persist(testProjectmanager);

        transaction.commit();

    }

    @Test
    public void modify() {
        transaction.begin();

        assertNotNull(testAddress);
        assertNotNull(testCompany);
        assertNotNull(testProjectmanager);

        Projectmanager merge = manager.merge(testProjectmanager);
        merge.setFunction(functionMerge);

        transaction.commit();

        testProjectmanager = manager.find(Projectmanager.class, testProjectmanager.getUserId());
        assertEquals(functionMerge, testProjectmanager.getFunction());
    }

    @Test
    public void remove() {
        transaction.begin();

        manager.remove(testProjectmanager);
        manager.remove(testCompany);
        manager.remove(testAddress);

        transaction.commit();

        this.testAddress = manager.find(Location.class, testAddress.getAddress());
        this.testCompany= manager.find(Company.class, testCompany.getCompany());
        this.testProjectmanager = manager.find(Projectmanager.class, testProjectmanager.getUserId());

        assertNull(testProjectmanager);
        assertNull(testCompany);
        assertNull(testAddress);

    }

}
