package at.fhj.swd.ClassTest;

import at.fhj.swd.BusinessIntelligence.User;
import at.fhj.swd.Helper.Handler;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestUser extends Handler
{

    private static User testUser;

    @BeforeClass
    public static void setup() {
        Handler.build();
        Handler.init();
    }

    @AfterClass
    public static void teardown() {
        Handler.close();
        Handler.destroy();
    }

    @Test
    public void create() {
        transaction.begin();
        testUser = new User(name, email, password);
        assertNotNull(testUser);
        manager.persist(testUser);
        transaction.commit();
    }

    @Test
    public void modify() {
        transaction.begin();

        assertNotNull(testUser);

        User merge = manager.merge(testUser);
        merge.setPassword(newPassword);

        transaction.commit();
        assertEquals(newPassword, testUser.getPassword());
    }

    @Test
    public void remove() {
        transaction.begin();

        manager.remove(testUser);

        transaction.commit();

        testUser = manager.find(User.class, testUser.getUserId());

        assertNull(testUser);
    }
}
