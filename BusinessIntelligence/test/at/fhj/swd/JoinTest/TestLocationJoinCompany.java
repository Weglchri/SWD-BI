package at.fhj.swd.JoinTest;

import at.fhj.swd.BusinessIntelligence.Company;
import at.fhj.swd.BusinessIntelligence.Projectmanager;
import at.fhj.swd.BusinessIntelligenceRepositories.CompanyRepository;
import at.fhj.swd.BusinessIntelligenceRepositories.LocationRepository;
import at.fhj.swd.Helper.JdbcHandler;
import at.fhj.swd.QueryTest.TestCompanyQuery;
import at.fhj.swd.QueryTest.TestLocationQuery;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by sattlerb on 26.01.2017.
 */
public class TestLocationJoinCompany extends JdbcHandler
{
    private static final String address = "Alte Poststraße 122/1";
    private static final String expectedCompanyName = "Stahl Incorporation";

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
    public void getCompanyByLocation() {
        LocationRepository locationRepository = new LocationRepository(manager);
        Company company = locationRepository.findCompanyByLocation(address);

        assertEquals(expectedCompanyName, company.getCompanyName());
    }
}
