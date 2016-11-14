package at.fhj.swd.BusinessIntelligence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestOffer {

    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "BusinessIntelligence";

    //data for Location
    static final String addressFreelancer = "Alte Poststraße 122/17";
    static final String addressProjectmanager = "Alte Poststraße 122/18";
    static final String country = "Austria";
    static final Integer zip = 8020;
    static final String city = "Graz";

    //data for User-Freelancer
    static final String name = "Christopher Wegl";
    static final String email = "christopher.wegl@edu.fh-joanneum.at";
    static final String password = "*******";

    //data for User-Projectmanager
    static final String name2 = "Björn Sattler";
    static final String email2 = "bjoern.sattler@edu.fh-joanneum.at";
    static final String password2 = "*******";

    //data for Freelancer
    static final String eduacation = "FH Joanneum";
    static final String availability = "available";
    static final int hourly_wage = 13;
    static final String profession = "Software Engineer Freelancer";

    //data for Projectmanager
    static final Integer involved = 1;
    static final String function = "Personal";

    //data for Company
    static final String company_name = "Stahl Incorporation";
    static final String branch = "Stahlbau";

    //data for Project
    static final Integer capital = 1000;
    Date date = new Date();
    static final String task = "Website-Programming";

    //data for Offer
    static final Integer price = 100;
    static final Integer priceMerge = 120;
    Date date2 = new Date();


    //data for test
    private static Location testAddress;
    private static Location testAddress2;
    private static Freelancer testFreelancer;
    private static Projectmanager testProjectmanager;
    private static Company testCompany;
    private static Project testProject;
    private static Offer testOffer;

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

        this.testAddress = new Location(addressFreelancer, country, zip, city);
        assertNotNull(testAddress);
        manager.persist(testAddress);

        this.testAddress2 = new Location(addressProjectmanager, country, zip, city);
        assertNotNull(testAddress2);
        manager.persist(testAddress2);

        this.testCompany = new Company(company_name, branch, testAddress);
        assertNotNull(testCompany);
        manager.persist(testCompany);

        this.testFreelancer = new Freelancer(name, email, password, profession, availability, hourly_wage, eduacation, testAddress2 );
        assertNotNull(testFreelancer);
        manager.persist(testFreelancer);

        this.testProjectmanager = new Projectmanager(name2, email2, password2, involved, function, testCompany);
        assertNotNull(testProjectmanager);
        manager.persist(testProjectmanager);

        this.testProject = new Project (capital, date, task, testCompany);
        assertNotNull(testProject);
        manager.persist(testProject);

        this.testOffer = new Offer(price, date2, testFreelancer, testProject);
        assertNotNull(testOffer);
        manager.persist(testOffer);

        transaction.commit();

    }

    @Test
    public void modify() {
        transaction.begin();

        assertNotNull(testAddress);
        assertNotNull(testAddress2);
        assertNotNull(testCompany);
        assertNotNull(testProjectmanager);
        assertNotNull(testProject);
        assertNotNull(testProject);
        assertNotNull(testOffer );

        Offer merge = manager.merge(testOffer);
        merge.setPrice(priceMerge);

        transaction.commit();

        testOffer = manager.find(Offer.class, testOffer.getOfferId());
        assertEquals(priceMerge, testOffer.getPrice());
    }

    @Test
    public void remove() {
        transaction.begin();

        manager.remove(testOffer);
        manager.remove(testProject);
        manager.remove(testProjectmanager);
        manager.remove(testFreelancer);
        manager.remove(testCompany);
        manager.remove(testAddress2);
        manager.remove(testAddress);

        transaction.commit();

        this.testAddress = manager.find(Location.class, testAddress.getAddress());
        this.testAddress2 = manager.find(Location.class, testAddress2.getAddress());
        this.testCompany= manager.find(Company.class, testCompany.getCompany());
        this.testFreelancer= manager.find(Freelancer.class, testFreelancer.getUserId());
        this.testProjectmanager = manager.find(Projectmanager.class, testProjectmanager.getUserId());
        this.testProject = manager.find(Project.class, testProject.getProjectId());
        this.testOffer = manager.find(Offer.class, testOffer.getOfferId());

        assertNull(testAddress);
        assertNull(testAddress2);
        assertNull(testCompany);
        assertNull(testProjectmanager);
        assertNull(testProject);
        assertNull(testProject);
        assertNull(testOffer );
    }
}
