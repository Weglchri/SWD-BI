package at.fhj.swd.QueryTest;

import at.fhj.swd.BusinessIntelligence.User;
import at.fhj.swd.BusinessIntelligenceRepositories.UserRepository;
import at.fhj.swd.Helper.JdbcHandler;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUserQuery extends JdbcHandler {

    private static User testUser;

    static final String name = "Administrator";
    static final String email = "Admin@edu.fh-joanneum.at";
    static final String password = "1234567";

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
    public void A_create()
    {
        transaction.begin();
        testUser = new User(name, email, password);
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
        testUser = manager.find(User.class, testUser.getUserId());
        assertNull(testUser);
    }
}
