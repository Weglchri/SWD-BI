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
    static final String dtype = "Freelancer";
    static final String password = "1234567";
    static final String passwordMerge = "7654321";

    User testUser = new User(name, email, password, dtype);

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
        //User testUser = new User(name, email, password, dtype);
        assertNotNull(testUser);
        manager.persist(testUser);
        transaction.commit();
    }

//    @Test
//    public void modify() {
//        transaction.begin ();
//        User testUser = manager.find(User.class, id);
//        assertNotNull(testUser);
//        User merge = manager.merge(testUser);
//        merge.setPassword(passwordMerge);
//        transaction.commit();
//
//        testUser = manager.find(User.class, id);
//        assertEquals (passwordMerge, testUser.getPassword());
//    }

    @Test
    public void remove() {
        transaction.begin();
        //User testUser1 = manager.find(User.class, name);
        //assertNotNull(testUser1);
        manager.remove(testUser);
        transaction.commit();
    }
}
