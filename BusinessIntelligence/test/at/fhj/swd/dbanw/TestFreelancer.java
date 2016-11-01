package at.fhj.swd.dbanw;

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

public class TestFreelancer
{

    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "persistence";

    //data for Location
    static final String address = "Alte Poststraße 122/14";
    static final String country = "Austria";
    static final Integer zip = 8020;
    static final String city = "Graz";

    //data for User
    static final int id = 1;
    static final String name = "Christopher Wegl";
    static final String email = "christopher.wegl@edu.fh-joanneum.at";
    static final String password = "*******";
    static final String dtype = "Freelancer";

    //data for Freelancer
    static final String eduacation = "FH Joanneum";
    static final String availability = "available";
    static final int hourly_wage = 13;
    static final String profession = "Software Engineer Freelancer";
    static final String professionMerge ="Database Admin";


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
        Freelancer testFreelancer = new Freelancer( id, name, email, password, dtype, profession, availability, hourly_wage, eduacation, testAddress );
        assertNotNull(testFreelancer);
        manager.persist(testFreelancer);
        transaction.commit();
    }

    @Test
    public void modify() {
        transaction.begin();
        Location testAddress = new Location(address, country, zip, city);
        assertNotNull(testAddress);
        Freelancer testFreelancer = new Freelancer( id, name, email, password, dtype, profession, availability, hourly_wage, eduacation, testAddress );
        assertNotNull(testFreelancer);
        Freelancer merge = manager.merge(testFreelancer);
        merge.setProfession(professionMerge);
        transaction.commit();

        testFreelancer = manager.find(Freelancer.class, id );
        assertEquals(professionMerge, testFreelancer.getProfession());
    }

    @Test
    public void remove() {
        Freelancer testFreelancer = manager.find(Freelancer.class, id);
        assertNotNull(testFreelancer);
        Location testAddress = manager.find(Location.class, address);
        assertNotNull(testAddress);
        transaction.begin();
        manager.remove(testFreelancer);
        manager.remove(testAddress);
        transaction.commit();

        testFreelancer = manager.find  (Freelancer.class, id);
        assertNull(testFreelancer);
        testAddress = manager.find(Location.class, address);
        assertNull(testAddress);
    }

}
