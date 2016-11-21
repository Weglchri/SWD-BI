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

import static org.junit.Assert.*;

/**
 * Created by sattlerb on 16/11/16.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUserQuery
{
    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "BusinessIntelligence";

    //data for User
    static final Integer id = 1;
    static final String name = "Administrator";
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
    public void A_create()
    {
        transaction.begin();
        this.testUser = new User(name, email, password);
        assertNotNull(testUser);
        manager.persist(testUser);
        transaction.commit();
    }


    @Test
    public void B_repoTest()
    {
        UserRepository userRepo = new UserRepository(manager);
        User testUser1 = userRepo.findByName(name);

        assertEquals(testUser1.getName(), testUser.getName());
        assertEquals(testUser1.getEmail(), testUser.getEmail());
        assertEquals(testUser1.getPassword(), testUser.getPassword());
        assertEquals(testUser1.getUserId(), testUser.getUserId());
    }
    @Test
    public void C_remove() {
        transaction.begin();
        assertNotNull(testUser);
        manager.remove(testUser);
        transaction.commit();
        this.testUser = manager.find(User.class, testUser.getUserId());
        assertNull(testUser);
    }
}
