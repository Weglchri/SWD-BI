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

public class TestUser
{

    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "BusinessIntelligence";

    //data for User
    static final Integer id = 1;
    static final String name = "Admin";
    static final String email = "Admin@edu.fh-joanneum.at";
    static final String password = "1234567";
    static final String passwordMerge = "7654321";

    //data for tests
    private static User testUser;


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
        this.testUser = new User(name, email, password);
        assertNotNull(testUser);
        manager.persist(testUser);
        transaction.commit();
    }

    @Test
    public void modify() {
        transaction.begin();
        assertNotNull(testUser);
        User merge = manager.merge(testUser);
        merge.setPassword(passwordMerge);
        transaction.commit();
        assertEquals(passwordMerge, testUser.getPassword());
    }

    @Test
    public void remove() {
        transaction.begin();
        assertNotNull(testUser);
        manager.remove(testUser);
        transaction.commit();
        this.testUser = manager.find(User.class, testUser.getUserId());
        assertNull(testUser);
    }
}
