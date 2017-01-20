package at.fhj.swd.QueryTest;

import at.fhj.swd.BusinessIntelligence.Company;
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
import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestLocationQuery extends JdbcHandler {

    private static Location testLocation;
    private static Company testCompany;

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

    static final String address3 = "Chapel Hill 12";
    static final String country3 = "UK";
    static final Integer zip3 = 1234;
    static final String city3 = "London";




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

        testLocation = new Location(address, country, zip, city);
        assertNotNull(testLocation);

        testCompany = new Company(TestCompanyQuery.company_name, TestCompanyQuery.branch, testLocation);
        assertNotNull(testCompany);

    }

    @Test
    public void B_repoTest() {
        
        LocationRepository locRepo = new LocationRepository(manager);
        List<Location> testLocation1 = locRepo.findByCountry(country);

        assertEquals(2, testLocation1.size());

        assertEquals(testLocation.getAddress(), testLocation1.get(0).getAddress());
        assertEquals(testLocation.getCity(), testLocation1.get(0).getCity());
        assertEquals(testLocation.getCountry(), testLocation1.get(0).getCountry());
        assertEquals(testLocation.getZip(), testLocation1.get(0).getZip());

        assertEquals(testLocation.getCompany().getCompanyName(), testLocation1.get(0).getCompany().getCompanyName());
        assertEquals(testLocation.getCompany().getBranch(), testLocation1.get(0).getCompany().getBranch());

    }


}
