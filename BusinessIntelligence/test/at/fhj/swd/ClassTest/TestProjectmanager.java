package at.fhj.swd.ClassTest;

import at.fhj.swd.BusinessIntelligence.Company;
import at.fhj.swd.BusinessIntelligence.Location;
import at.fhj.swd.BusinessIntelligence.Projectmanager;
import at.fhj.swd.Helper.JdbcHandler;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestProjectmanager extends JdbcHandler {

    private static Location testLocation;
    private static Company testCompany;
    private static Projectmanager testProjectmanager;

    static final Integer involved = 1;
    static final String function = "Personal";
    static final String newFunction = "Zulieferung";


    @BeforeClass
    public static void setup() {
        JdbcHandler.build();
        JdbcHandler.init();
    }

    @AfterClass
    public static void teardown() {
        JdbcHandler.close();
        JdbcHandler.dropTables();
    }

    @Test
    public void create()
    {
        transaction.begin();

        testLocation = new Location(TestLocation.address, TestLocation.country, TestLocation.zip, TestLocation.city);
        assertNotNull(testLocation);
        manager.persist(testLocation);

        testCompany = new Company(TestCompany.company_name, TestCompany.branch, testLocation);
        assertNotNull(testCompany);
        manager.persist(testCompany);

        testProjectmanager = new Projectmanager(TestUser.name, TestUser.email, TestUser.password, involved, function, testCompany);
        assertNotNull(testProjectmanager);
        manager.persist(testProjectmanager);

        transaction.commit();

    }

    @Test
    public void modify() {
        transaction.begin();

        assertNotNull(testLocation);
        assertNotNull(testCompany);
        assertNotNull(testProjectmanager);

        Projectmanager merge = manager.merge(testProjectmanager);
        merge.setFunction(newFunction);

        transaction.commit();

        testProjectmanager = manager.find(Projectmanager.class, testProjectmanager.getUserId());
        assertEquals(newFunction, testProjectmanager.getFunction());
    }

    @Test
    public void remove() {
        transaction.begin();

        manager.remove(testProjectmanager);
        manager.remove(testCompany);
        manager.remove(testLocation);

        transaction.commit();

        testLocation = manager.find(Location.class, testLocation.getAddress());
        testCompany= manager.find(Company.class, testCompany.getCompany());
        testProjectmanager = manager.find(Projectmanager.class, testProjectmanager.getUserId());

        assertNull(testProjectmanager);
        assertNull(testCompany);
        assertNull(testLocation);

    }

}
