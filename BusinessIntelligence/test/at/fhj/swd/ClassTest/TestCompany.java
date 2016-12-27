
package at.fhj.swd.ClassTest;

import at.fhj.swd.BusinessIntelligence.Company;
import at.fhj.swd.BusinessIntelligence.Location;
import at.fhj.swd.Helper.Handler;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestCompany extends Handler {

    private static Location testLocation;
    private static Company testCompany;


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

        testLocation = manager.find(Location.class, address);
        testCompany = manager.find(Company.class, company_name);

        assertNull(testLocation);
        assertNull(testCompany);
    }

}
