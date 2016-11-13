
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

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCompany {


    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "BusinessIntelligence";

    //data for Location
    static final String address = "Alte Poststraße 122/13";
    static final String country = "Austria";
    static final Integer zip = 8020;
    static final String city = "Graz";

    //data for Company
    static final String company_name = "Stahl Incorporation";
    static final String branch = "Stahlbau";
    static final String branchMerge = "Bergbau";

    //data for project
    static final Integer capital = 1000;
    Date date = new Date();
    static final String task = "Website-Programming";
    static final String taskMerge = "Database-Design";


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
        Company testCompany = new Company(company_name, branch, testAddress);
        assertNotNull(testCompany);
        manager.persist(testCompany);
        transaction.commit();
    }

    @Test
    public void modify() {
        transaction.begin();
        Location testAddress = new Location(address, country, zip, city);
        assertNotNull(testAddress);
        Company testCompany = new Company(company_name, branch, testAddress);
        Company merge = manager.merge(testCompany);
        merge.setBranch(branchMerge);
        transaction.commit();

        testCompany = manager.find(Company.class, company_name);
        assertEquals(branchMerge, testCompany.getBranch());
    }

    @Test
    public void remove() {
        transaction.begin();
        Company testCompany = manager.find(Company.class, company_name);
        assertNotNull(testCompany);
        Location testAddress = manager.find(Location.class, address);
        assertNotNull(testAddress);
        manager.remove(testCompany);
        manager.remove(testAddress);
        transaction.commit();

        testAddress = manager.find(Location.class, address);
        assertEquals(null, testAddress);
        testCompany = manager.find(Company.class, company_name);
        assertNull(testAddress);
        assertNull(testCompany);
    }

}
