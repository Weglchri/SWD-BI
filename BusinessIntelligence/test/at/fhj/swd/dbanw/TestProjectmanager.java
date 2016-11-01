package at.fhj.swd.dbanw;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.sound.midi.Sequence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestProjectmanager {


    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "persistence";

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

        Location testAddress = new Location(address, country, zip, city);
        assertNotNull(testAddress);
        manager.persist(testAddress);

        Company testCompany = new Company(company_name, branch, testAddress);
        assertNotNull(testCompany);
        manager.persist(testCompany);

        Projectmanager testProjectmanager = new Projectmanager(id, name, email, password, dtype, involved, function, testCompany);
        assertNotNull(testProjectmanager);
        manager.persist(testProjectmanager);
        transaction.commit();

    }

    @Test
    public void modify() {
        transaction.begin();
        Location testAddress = new Location(address, country, zip, city);
        assertNotNull(testAddress);
        Company testCompany = new Company(company_name, branch, testAddress);
        assertNotNull(testCompany);
        Projectmanager testProjectmanager = new Projectmanager( id, name, email, password, dtype, involved, function, testCompany);
        assertNotNull(testProjectmanager);
        Projectmanager merge = manager.merge(testProjectmanager);
        merge.setFunction(functionMerge);
        transaction.commit();

        testProjectmanager = manager.find(Projectmanager.class, id);
        assertEquals(functionMerge, testProjectmanager.getFunction());
    }

    @Test
    public void remove() {
        Projectmanager testProjectmanager = manager.find(Projectmanager.class, id);
        assertNotNull(testProjectmanager);
        Company testCompany = manager.find(Company.class, company_name);
        assertNotNull(testCompany);
        Location testAddress = manager.find(Location.class, address);
        assertNotNull(testAddress);
        transaction.begin();
        manager.remove(testProjectmanager);
        manager.remove(testCompany);
        manager.remove(testAddress);
        transaction.commit();

        testProjectmanager = manager.find(Projectmanager.class, id);
        assertEquals(null, testProjectmanager);
        testAddress = manager.find(Location.class, address);
        assertEquals(null, testAddress);
        testCompany = manager.find(Company.class, company_name);
        assertEquals(null, testCompany);
    }

}
