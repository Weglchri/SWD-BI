package at.fhj.swd.Security;

import at.fhj.swd.BusinessIntelligence.Location;
import at.fhj.swd.BusinessIntelligenceRepositories.LocationRepository;
import at.fhj.swd.Helper.JdbcHandler;
import at.fhj.swd.Helper.ScriptLoader;
import org.junit.*;
import org.junit.runners.MethodSorters;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sattlerb on 12/01/17.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSecurityReaderWriter extends JdbcHandler
{


    private static final String user = "smithers";
    private static final String password = "burns";
    private static final String persistenceUnitName = "BusinessIntelligence";

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

    private static EntityManagerFactory factoryReaderWriter;
    private static EntityManager managerReaderWriter;
    private static EntityTransaction transactionReaderWriter;

    private Location location = new Location("Hauptplatz", "Österreich", 8010, "Graz");
    private static Location location1 = new Location(address, country, zip, city);
    private static Location location2 = new Location(address1, country1, zip1, city1);
    private static Location location3 = new Location(address2, country2, zip2, city2);

    private static EntityManagerFactory factoryOwner;
    private static EntityManager managerOwner;

    private static LocationRepository locationRepo;

    @BeforeClass
    public static void hurray()
    {
        JdbcHandler.build();
        JdbcHandler.buildSecurity();

        factoryOwner = Persistence.createEntityManagerFactory(persistenceUnitName);
        managerOwner = factoryOwner.createEntityManager();
    }

    @Before
    public void setup()
    {
        Map<String, String> properties = new HashMap<String, String>();
        properties.put("javax.persistence.jdbc.user", user);
        properties.put("javax.persistence.jdbc.password", password);

        //creates new connection with different user and password
        factoryReaderWriter = Persistence.createEntityManagerFactory(persistenceUnitName, properties);
        managerReaderWriter = factoryReaderWriter.createEntityManager();

        locationRepo = new LocationRepository(managerReaderWriter);

        transactionReaderWriter = managerOwner.getTransaction();
        transactionReaderWriter.begin();

        managerOwner.persist(location1);
        managerOwner.persist(location2);
        managerOwner.persist(location3);
        transactionReaderWriter.commit();

    }

    @Test
    public void A_testReaderSelect()
    {
        List<Location> locationList = locationRepo.findAll();
        Assert.assertEquals(3, locationList.size());
    }

    @Test
    public void B_testReaderInsert()
    {
        transactionReaderWriter = managerReaderWriter.getTransaction();
        transactionReaderWriter.begin();

        managerReaderWriter.persist(location);
        transactionReaderWriter.commit();

        LocationRepository findLocation = new LocationRepository(managerReaderWriter);
        Assert.assertEquals(1, findLocation.findByCountry("Österreich").size());
        Assert.assertEquals(4, findLocation.findAll().size());
    }

    @Test
    public void C_testReaderRemove()
    {
        transactionReaderWriter = managerReaderWriter.getTransaction();
        transactionReaderWriter.begin();

        LocationRepository findLocationToRemove = new LocationRepository(managerReaderWriter);
        Location foundLocation = findLocationToRemove.findByAddress("Hauptplatz");
        try {
            managerReaderWriter.remove(foundLocation);
        }
        catch(Exception e)
        {
            Assert.assertTrue(e.getMessage().contains( "permission denied" ));
        }
    }

    @Test
    public void D_testReaderUpdate()
    {
        transactionReaderWriter = managerReaderWriter.getTransaction();
        transactionReaderWriter.begin();

        LocationRepository findLocationToUpdate = new LocationRepository(managerReaderWriter);
        Location updateLocation = findLocationToUpdate.findByAddress("Hauptplatz");
        updateLocation.setCity("Graz-Innere Stadt");
        managerReaderWriter.persist(updateLocation);
        transactionReaderWriter.commit();

        Assert.assertEquals("Graz-Innere Stadt", findLocationToUpdate.findByAddress("Hauptplatz").getCity());
    }


    @AfterClass
    public static void teardown()
    {
        managerOwner.close();
        factoryOwner.close();
        ScriptLoader Loader = new ScriptLoader();
        Loader.executeSqlScript("sql/drop.sql");
        Loader.executeSqlScript("sql/drop_sequences.sql");
        Loader.executeSqlScript("sql/security_drop.sql");
    }


}
