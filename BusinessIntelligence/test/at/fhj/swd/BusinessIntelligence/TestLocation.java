
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

public class TestLocation {

    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "persistence";

    //data for Location
    static final String address = "Alte Poststraße 122/15";
    static final String country = "Austria";
    static final Integer zip = 8020;
    static final String city = "Graz";
    static final String cityMerge ="Vienna";


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
        transaction.commit();
    }

    @Test
    public void modify() {
        transaction.begin();
        Location testAddress = manager.find(Location.class, address);
        assertNotNull(testAddress);
        Location merge = manager.merge(testAddress);
        merge.setCity(cityMerge);
        transaction.commit();

        testAddress = manager.find(Location.class, address);
        assertEquals(cityMerge ,testAddress.getCity());
    }

    @Test
    public void remove() {
        transaction.begin();
        Location testAddress = manager.find(Location.class, address);
        assertNotNull(testAddress);
        manager.remove(testAddress);
        transaction.commit();

        testAddress = manager.find(Location.class, address);
        assertNull(testAddress);
    }
}
