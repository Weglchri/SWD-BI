package at.fhj.swd.ClassTest;

import at.fhj.swd.BusinessIntelligence.Freelancer;
import at.fhj.swd.BusinessIntelligence.Location;
import at.fhj.swd.Helper.JdbcHandler;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestFreelancer extends JdbcHandler
{

    private static Location testLocation;
    private static Freelancer testFreelancer;

    static final String education = "FH Joanneum";
    static final String availability = "available";
    static final int hourly_wage = 13;
    static final String profession = "Software Engineer Freelancer";
    static final String newProfession ="Database Admin";


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

        testLocation = new Location(TestLocation.address, TestLocation.country, TestLocation.zip, TestLocation.city);
        assertNotNull(testLocation);
        manager.persist(testLocation);

        testFreelancer = new Freelancer(TestUser.name, TestUser.email, TestUser.password, profession, availability, hourly_wage, education, testLocation );
        assertNotNull(testFreelancer);
        manager.persist(testFreelancer);

        transaction.commit();
    }

    @Test
    public void modify() {
        transaction.begin();

        assertNotNull(testLocation);
        assertNotNull(testFreelancer);

        Freelancer merge = manager.merge(testFreelancer);
        merge.setProfession(newProfession);

        transaction.commit();
        assertEquals(newProfession, testFreelancer.getProfession());
    }


    @Test
    public void remove() {
        transaction.begin();

        manager.remove(testFreelancer);
        manager.remove(testLocation);

        transaction.commit();

        testFreelancer = manager.find(Freelancer.class, testFreelancer.getUserId());
        testLocation = manager.find(Location.class, testLocation.getAddress());

        assertNull(testFreelancer);
        assertNull(testLocation);

    }

}
