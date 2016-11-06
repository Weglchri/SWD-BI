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

public class TestFreelancer
{

    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "BusinessIntelligence";

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

    //data for Freelancer
    static final String eduacation = "FH Joanneum";
    static final String availability = "available";
    static final int hourly_wage = 13;
    static final String profession = "Software Engineer Freelancer";
    static final String professionMerge ="Database Admin";

    //data for test
    private static Location testAddress;
    private static Freelancer testFreelancer;

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

        this.testAddress = new Location(address, country, zip, city);
        assertNotNull(testAddress);
        manager.persist(testAddress);

        this.testFreelancer = new Freelancer(name, email, password, profession, availability, hourly_wage, eduacation, testAddress );
        assertNotNull(testFreelancer);
        manager.persist(testFreelancer);

        transaction.commit();
    }

    @Test
    public void modify() {
        transaction.begin();

        assertNotNull(testAddress);
        assertNotNull(testFreelancer);

        Freelancer merge = manager.merge(testFreelancer);
        merge.setProfession(professionMerge);

        transaction.commit();
        assertEquals(professionMerge, testFreelancer.getProfession());
    }


    @Test
    public void remove() {
        transaction.begin();

        manager.remove(testFreelancer);
        manager.remove(testAddress);

        transaction.commit();

        this.testFreelancer = manager.find(Freelancer.class, testFreelancer.getUserId());
        this.testAddress = manager.find(Location.class, testAddress.getAddress());

        assertNull(testFreelancer);
        assertNull(testAddress);

    }

}
