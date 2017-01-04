package at.fhj.swd.ClassTest;

import at.fhj.swd.BusinessIntelligence.User;
import at.fhj.swd.Helper.JdbcHandler;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestUser extends JdbcHandler
{

    private static User testUser;

    public static final String name = "Admin";
    public static final String name2 = "Default";
    public static final String name3 = "Prof.Harald Habiger";
    public static final String email = "Admin@edu.fh-joanneum.at";
    public static final String password = "1234567";
    public static final String newPassword = "7654321";


    @BeforeClass
    public static void setup() {
        JdbcHandler.build();
        JdbcHandler.init();
    }

    @AfterClass
    public static void teardown() {
        JdbcHandler.close();
        JdbcHandler.destroy();
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
