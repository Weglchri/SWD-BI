/*
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

public class TestUser
{

    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "wegl";

    @BeforeClass
    public static void setup() {

        factory = Persistence.createEntityManagerFactory( persistenceUnitName );
        assertNotNull (factory);
        manager = factory.createEntityManager();
        assertNotNull (manager);
        transaction = manager.getTransaction();
    }

    @AfterClass
    public static void teardown() {
        if (manager == null) return;
        manager.close();
        factory.close();
    }

    @Test
    public void create ()
    {
        transaction.begin ();
        User testUser = new User (1, "CE", "CE@CE.CE", "*****");
        assertNotNull (testUser);
        manager.persist (testUser);
        transaction.commit();
    }

    @Test
    public void getName()
    {
        User testUser = new User (1, "CE", "CE@CE.CE", "*****");
        assertEquals("CE", testUser.getName());
    }

}
*/