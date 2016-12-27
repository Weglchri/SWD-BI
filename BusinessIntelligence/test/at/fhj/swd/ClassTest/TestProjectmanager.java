package at.fhj.swd.ClassTest;

import at.fhj.swd.BusinessIntelligence.Company;
import at.fhj.swd.BusinessIntelligence.Location;
import at.fhj.swd.BusinessIntelligence.Projectmanager;
import at.fhj.swd.Helper.Handler;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestProjectmanager extends Handler {

    private static Location testLocation;
    private static Company testCompany;
    private static Projectmanager testProjectmanager;

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
    public void create()
    {
        transaction.begin();

        testLocation = new Location(address, country, zip, city);
        assertNotNull(testLocation);
        manager.persist(testLocation);

        testCompany = new Company(company_name, branch, testLocation);
        assertNotNull(testCompany);
        manager.persist(testCompany);

        testProjectmanager = new Projectmanager(name, email, password, involved, function, testCompany);
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
