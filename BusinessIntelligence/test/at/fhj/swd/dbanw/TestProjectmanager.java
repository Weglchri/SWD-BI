
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

import static at.fhj.swd.dbanw.TestCompany.address;
import static at.fhj.swd.dbanw.TestCompany.companyName;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestProjectmanager {


    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "persistence";

    static final String companyName = "Orangen Inc.";
    static final String branch = "Stahlbau";
    static final String address = "Straße3";
    static final String country = "Mexico";
    static final Integer zip = 8020;
    static final String city = "Canberra";
    static final Integer user_Id = 123;
    static final String name = "Herbert";
    static final String email = "herbert@Orange";
    static final String password = "password";
    static final Integer involved = 3;
    static final String task = "Personal";



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
        assertNotNull(testAddress);
        manager.persist(testCompany);
        Projectmanager testProjectmanager = new Projectmanager( user_Id, name, email, password, involved, task, testCompany );
        assertNotNull(testAddress);
        manager.persist(testProjectmanager);
        transaction.commit();

    }

    @Test
    public void modify() {
        transaction.begin();
        Location testAddress = new Location(address, country, zip, city);
        assertNotNull(testAddress);
        Company testCompany = new Company(companyName, branch, testAddress);
        assertNotNull(testCompany);
        Projectmanager testProjectmanager = new Projectmanager( user_Id, name, email, password, involved, task, testCompany );
        assertNotNull(testProjectmanager);
        Projectmanager merge = manager.merge(testProjectmanager);
        merge.setEmail("Herbert@asd");
        transaction.commit();

        testProjectmanager = manager.find(Projectmanager.class, user_Id);
        assertEquals("Herbert@asd", testProjectmanager.getEmail());
    }


    @Test
    public void remove() {
        Projectmanager testProjectmanager = manager.find(Projectmanager.class, user_Id);
        assertNotNull(testProjectmanager);
        Company testCompany = manager.find(Company.class, companyName);
        assertNotNull(testCompany);
        Location testAddress = manager.find(Location.class, address);
        assertNotNull(testAddress);
        transaction.begin();
        manager.remove(testProjectmanager);
        manager.remove(testCompany);
        manager.remove(testAddress);
        transaction.commit();

        testProjectmanager = manager.find(Projectmanager.class, user_Id);
        assertEquals(null, testProjectmanager);
        testAddress = manager.find(Location.class, address);
        assertEquals(null, testAddress);
        testCompany = manager.find(Company.class, companyName);
        assertEquals(null, testCompany);
    }

}
