
package at.fhj.swd.ClassTest;

import at.fhj.swd.BusinessIntelligence.Location;
import at.fhj.swd.Helper.Handler;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestLocation extends Handler {

    private static Location testLocation;

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
        transaction.commit();
    }

    @Test
    public void modify() {
        transaction.begin();

        assertNotNull(testLocation);

        Location merge = manager.merge(testLocation);
        merge.setCity(newCity);

        transaction.commit();

        testLocation = manager.find(Location.class, address);
        assertEquals(newCity ,testLocation.getCity());
    }

    @Test
    public void remove() {
        transaction.begin();
        Location testLocation = manager.find(Location.class, address);
        assertNotNull(testLocation);
        manager.remove(testLocation);
        transaction.commit();

        testLocation = manager.find(Location.class, address);
        assertNull(testLocation);
    }
}
