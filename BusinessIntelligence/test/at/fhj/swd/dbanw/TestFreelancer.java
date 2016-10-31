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

/**
 * Created by sattlerb on 31/10/16.
 */
public class TestFreelancer
{


    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "persistence";

    //data for location
    static final String address = "Alte Poststraße 122/15";
    static final String country = "Austria";
    static final Integer zip = 8020;
    static final String city = "Graz";

    //data for user
    static final Integer user_Id = 124;
    static final String name = "Freelancer Herbert";
    static final String email = "herbert@freelancer.at";
    static final String password = "*****";

    //data for freelancer
    static final String eduacation = "FH Joanneum";
    static final String availability = "available";
    static final int wagePerHour = 13;
    static final String profession = "Software Engineer Freelancer";



    @BeforeClass
    public static void setup()
    {
        factory = Persistence.createEntityManagerFactory(persistenceUnitName);
        assertNotNull(factory);
        manager = factory.createEntityManager();
        assertNotNull(manager);
        transaction = manager.getTransaction();
    }

    @AfterClass
    public static void teardown()
    {
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

        Freelancer testFreelancer = new Freelancer( user_Id, name, email, password, profession, availability, wagePerHour, eduacation, testAddress );
        assertNotNull(testAddress);
        manager.persist(testFreelancer);
        transaction.commit();

    }
//    @Test
//    public void modify() {
//        transaction.begin();
//        Location testAddress = new Location(address, country, zip, city);
//        assertNotNull(testAddress);
//        Company testCompany = new Company(companyName, branch, testAddress);
//        assertNotNull(testCompany);
//        Projectmanager testProjectmanager = new Projectmanager( user_id, name, email, password, involved, task, testCompany );
//        assertNotNull(testProjectmanager);
//        Projectmanager merge = manager.merge(testProjectmanager);
//        merge.setEmail("Herbert@asd");
//        transaction.commit();
//
//        testProjectmanager = manager.find(Projectmanager.class, user_id);
//        assertEquals("Herbert@asd", testProjectmanager.getEmail());
//    }
//
//
//    @Test
//    public void remove() {
//        Freelancer testFreelancer = manager.find(Freelancer.class, user_id);
//        assertNotNull(testFreelancer);
//        Location testAddress = manager.find(Location.class, address);
//        assertNotNull(testAddress);
//        transaction.begin();
//        manager.remove(testFreelancer);
//        manager.remove(testAddress);
//        transaction.commit();
//
//        testFreelancer = manager.find(Freelancer.class, user_id);
//        assertEquals(null, testFreelancer);
//        testAddress = manager.find(Location.class, address);
//        assertEquals(null, testAddress);
//    }

}
