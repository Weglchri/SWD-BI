package at.fhj.swd.QueryTest;

import at.fhj.swd.BusinessIntelligence.Freelancer;
import at.fhj.swd.BusinessIntelligence.FreelancerRepository;
import at.fhj.swd.BusinessIntelligence.Location;
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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestFreelancerQuery
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
    public void A_create()
    {
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
    public void B_repoTest()
    {
        FreelancerRepository freelancerRepo = new FreelancerRepository(manager);
        Freelancer freelancer1 = freelancerRepo.findByName(name);

        assertEquals(freelancer1.getName(), testFreelancer.getName());
        assertEquals(freelancer1.getEmail(), testFreelancer.getEmail());
        assertEquals(freelancer1.getPassword(), testFreelancer.getPassword());
        assertEquals(freelancer1.getUserId(), testFreelancer.getUserId());
        assertEquals(freelancer1.getAvailability(), testFreelancer.getAvailability());
        assertEquals(freelancer1.getProfession(), testFreelancer.getProfession());
        assertEquals(freelancer1.getHourly_wage(), testFreelancer.getHourly_wage());
        assertEquals(freelancer1.getEducation(), testFreelancer.getEducation());
        assertEquals(freelancer1.getAddress(), testFreelancer.getAddress());
    }

    @Test
    public void C_remove()
    {
        transaction.begin();

        assertNotNull(testAddress);
        assertNotNull(testFreelancer);

        manager.remove(testAddress);
        manager.remove(testFreelancer);

        transaction.commit();

        this.testFreelancer = manager.find(Freelancer.class, testFreelancer.getUserId());
        assertNull(testFreelancer);
    }


}
