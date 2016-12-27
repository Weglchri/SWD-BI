package at.fhj.swd.ClassTest;

import at.fhj.swd.BusinessIntelligence.Freelancer;
import at.fhj.swd.BusinessIntelligence.Location;
import at.fhj.swd.Helper.Handler;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestFreelancer extends Handler
{

    private static Location testLocation;
    private static Freelancer testFreelancer;

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

        testLocation = new Location(address, country, zip, city);
        assertNotNull(testLocation);
        manager.persist(testLocation);

        testFreelancer = new Freelancer(name, email, password, profession, availability, hourly_wage, education, testLocation );
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
