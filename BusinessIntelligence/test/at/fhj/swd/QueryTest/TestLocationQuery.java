package at.fhj.swd.QueryTest;

import at.fhj.swd.BusinessIntelligence.Location;
import at.fhj.swd.BusinessIntelligenceRepositories.LocationRepository;
import at.fhj.swd.Helper.JdbcHandler;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestLocationQuery extends JdbcHandler {

    private static Location testAddress;
    private static Location testAddress1;
    private static Location testAddress2;

    static final String address = "Alte Poststraße 122/1";
    static final String country = "Austria";
    static final Integer zip = 8020;
    static final String city = "Graz";

    static final String address1 = "Lugner City 1";
    static final String country1 = "Austria";
    static final Integer zip1 = 1010;
    static final String city1 = "Wien";

    static final String address2 = "Melbourne Road 88";
    static final String country2 = "Australia";
    static final Integer zip2 = 9020;
    static final String city2 = "Sidney";

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
    public void A_create() {
        transaction.begin();

        this.testAddress = new Location(address, country, zip, city);
        assertNotNull(testAddress);
        manager.persist(testAddress);

        this.testAddress1 = new Location(address1, country1, zip1, city1);
        assertNotNull(testAddress1);
        manager.persist(testAddress1);

        this.testAddress2 = new Location(address2, country2, zip2, city2);
        assertNotNull(testAddress2);
        manager.persist(testAddress2);

        transaction.commit();
    }

    @Test
    public void B_repoTest()
    {
        LocationRepository locRepo = new LocationRepository(manager);
        List<Location> testLocationRepo = locRepo.findByCountry(country);

        assertEquals(2, testLocationRepo.size());
        assertTrue(testLocationRepo.contains(testAddress));
        assertTrue(testLocationRepo.contains(testAddress1));
        assertFalse(testLocationRepo.contains(testAddress2));
    }
    @Test
    public void C_remove()
    {
        transaction.begin();

        assertNotNull(testAddress);
        assertNotNull(testAddress1);
        assertNotNull(testAddress2);
        manager.remove(testAddress);
        manager.remove(testAddress1);
        manager.remove(testAddress2);

        transaction.commit();

        this.testAddress = manager.find(Location.class, testAddress.getAddress());
        this.testAddress1 = manager.find(Location.class, testAddress1.getAddress());
        this.testAddress2 = manager.find(Location.class, testAddress2.getAddress());
        assertNull(testAddress);
        assertNull(testAddress1);
        assertNull(testAddress2);
    }

}
