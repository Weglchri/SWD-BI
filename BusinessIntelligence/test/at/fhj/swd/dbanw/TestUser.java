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

/**
 * Created by sattlerb on 31/10/16.
 */
public class TestUser
{

    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "persistence";

    static final int id = 1;
    static final String name = "Björn Sattler";
    static final String eMail = "bjoern.sattler@edu.fh-joanneum.at";
    static final String password = "*****";

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
        User u = new User(id, name, eMail, password);
        assertNotNull(u);
        manager.persist(u);
        transaction.commit();
    }

    @Test
    public void modify()
    {

        User u = manager.find(User.class, id);
        assertNotNull(u);

        transaction.begin ();
        u.setEmail("test@test.test");

        transaction.commit();
        teardown();
        setup();
        u = manager.find (User.class, id);
        assertEquals ("test@test.test", u.getEmail());
    }

    @Test
    public void remove()
    {
        User u = manager.find (User.class, id);
        assertNotNull (u);
        transaction.begin();
        manager.remove(u);
        transaction.commit();
        u = manager.find(User.class, id);
        assertNull(u);
    }



}
