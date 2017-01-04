
package at.fhj.swd.ClassTest;

import at.fhj.swd.BusinessIntelligence.Location;
import at.fhj.swd.Helper.JdbcHandler;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestLocation extends JdbcHandler {

    private static Location testLocation;

    static final String address = "Alte Poststraße 122/1";
    static final String address2= "Alte Poststraße 122/2";
    static final String country = "Austria";
    static final Integer zip = 8020;
    static final String city = "Graz";
    static final String newCity = "Vienna";


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
        manager.remove(testLocation);
        transaction.commit();

        testLocation = manager.find(Location.class, address);
        assertNull(testLocation);
    }
}
