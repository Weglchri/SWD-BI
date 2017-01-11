
package at.fhj.swd.ClassTest;

import at.fhj.swd.BusinessIntelligence.Company;
import at.fhj.swd.BusinessIntelligence.Location;
import at.fhj.swd.Helper.JdbcHandler;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestCompany extends JdbcHandler {

    private static Location testLocation;
    private static Company testCompany;

    static final String company_name = "Stahl Incorporation";
    static final String branch = "Stahlbau";
    static final String newBranch = "Bergbau";


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
    public void create() {
        transaction.begin();

        testLocation = new Location(TestLocation.address, TestLocation.country, TestLocation.zip, TestLocation.city);
        assertNotNull(testLocation);
        manager.persist(testLocation);

        testCompany = new Company(company_name, branch, testLocation);
        assertNotNull(testCompany);
        manager.persist(testCompany);

        transaction.commit();
    }

    @Test
    public void modify() {
        transaction.begin();

        assertNotNull(testCompany);

        Company merge = manager.merge(testCompany);
        merge.setBranch(newBranch);

        transaction.commit();

        testCompany = manager.find(Company.class, company_name);
        assertEquals(newBranch, testCompany.getBranch());
    }

    @Test
    public void remove() {
        transaction.begin();

        manager.remove(testCompany);
        manager.remove(testLocation);

        transaction.commit();

        testLocation = manager.find(Location.class, testLocation.getAddress());
        testCompany = manager.find(Company.class, company_name);

        assertNull(testLocation);
        assertNull(testCompany);
    }

}
