
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCompany {


    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "persistence";

    static final String companyName = "Orangen Inc.";
    static final String branch = "Stahlbau";
    static final String address = "Straße3";
    static final String country = "Mexico";
    static final int zip = 8020;
    static final String city = "Canberra";


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
    public void create() {
        transaction.begin();
        Location testAddress = new Location(address, country, zip, city);
        assertNotNull(testAddress);
        manager.persist(testAddress);
        Company testCompany = new Company(companyName, branch, testAddress);
        assertNotNull(testCompany);
        manager.persist(testCompany);
        transaction.commit();
    }

    @Test
    public void modify() {
        transaction.begin();
        Location testAddress = new Location(address, country, zip, city);
        assertNotNull(testAddress);
        Company testCompany = new Company(companyName, branch, testAddress);
        Company merge = manager.merge(testCompany);
        merge.setBranch("Industrie");
        transaction.commit();
        testCompany = manager.find(Company.class, companyName);
        assertEquals("Industrie", testCompany.getBranch());
    }


    @Test
    public void remove() {
        Company testCompany = manager.find(Company.class, companyName);
        assertNotNull(testCompany);
        Location testAddress = manager.find(Location.class, address);
        assertNotNull(testAddress);
        transaction.begin();
        manager.remove(testCompany);
        manager.remove(testAddress);
        transaction.commit();
        testAddress = manager.find(Location.class, address);
        assertEquals(null, testAddress);
        testCompany = manager.find(Company.class, companyName);
        assertEquals(null, testCompany);
    }

}
