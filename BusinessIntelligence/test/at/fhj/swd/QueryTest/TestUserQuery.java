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

    static final String name1 = "Somebody";
    static final String email1 = "Somebody@edu.fh-joanneum.at";
    static final String password1 = "1234567";

    static final String name2 = "Someone";
    static final String email2 = "Someone@edu.fh-joanneum.at";
    static final String password2 = "1234567";


    @BeforeClass
    public static void setup() {
        JdbcHandler.build();
        JdbcHandler.insert();
        JdbcHandler.init();
    }

    @AfterClass
    public static void teardown() {
        JdbcHandler.close();
        JdbcHandler.dropTables();
    }

    @Test
    public void A_createObjects() {
        testUser = new User(name, email, password);
        assertNotNull(testUser);
    }

    @Test
    public void B_repoTest() {
        UserRepository userRepo = new UserRepository(manager);
        User testUser1 = userRepo.findByName(name);

        assertEquals(testUser.getName(), testUser1.getName());
        assertEquals(testUser.getEmail(), testUser1.getEmail());
        assertEquals(testUser.getPassword(), testUser1.getPassword());

    }

}
