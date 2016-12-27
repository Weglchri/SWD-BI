package at.fhj.swd.ClassTest;

import at.fhj.swd.BusinessIntelligence.*;
import at.fhj.swd.Helper.Handler;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestOffer extends Handler
{

    private static Location testLocation;
    private static Location testLocation2;
    private static Freelancer testFreelancer;
    private static Projectmanager testProjectmanager;
    private static Company testCompany;
    private static Project testProject;
    private static Offer testOffer;

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

        testLocation2 = new Location(address2, country, zip, city);
        assertNotNull(testLocation2);
        manager.persist(testLocation2);

        testCompany = new Company(company_name, branch, testLocation);
        assertNotNull(testCompany);
        manager.persist(testCompany);

        testFreelancer = new Freelancer(name, email, password, profession, availability, hourly_wage, education, testLocation2 );
        assertNotNull(testFreelancer);
        manager.persist(testFreelancer);

        testProjectmanager = new Projectmanager(name2, email2, password2, involved, function, testCompany);
        assertNotNull(testProjectmanager);
        manager.persist(testProjectmanager);

        testProject = new Project (capital, date, task, testCompany);
        assertNotNull(testProject);
        manager.persist(testProject);

        testOffer = new Offer(price, date, testFreelancer, testProject);
        assertNotNull(testOffer);
        manager.persist(testOffer);

        transaction.commit();

    }

    @Test
    public void modify() {
        transaction.begin();

        assertNotNull(testLocation);
        assertNotNull(testLocation2);
        assertNotNull(testCompany);
        assertNotNull(testProjectmanager);
        assertNotNull(testProject);
        assertNotNull(testProject);
        assertNotNull(testOffer );

        Offer merge = manager.merge(testOffer);
        merge.setPrice(newPrice);

        transaction.commit();

        testOffer = manager.find(Offer.class, testOffer.getOfferId());
        assertEquals(newPrice, testOffer.getPrice());
    }

    @Test
    public void remove() {
        transaction.begin();

        manager.remove(testOffer);
        manager.remove(testProject);
        manager.remove(testProjectmanager);
        manager.remove(testFreelancer);
        manager.remove(testCompany);
        manager.remove(testLocation2);
        manager.remove(testLocation);

        transaction.commit();

        testLocation = manager.find(Location.class, testLocation.getAddress());
        testLocation2 = manager.find(Location.class, testLocation2.getAddress());
        testCompany= manager.find(Company.class, testCompany.getCompany());
        testFreelancer= manager.find(Freelancer.class, testFreelancer.getUserId());
        testProjectmanager = manager.find(Projectmanager.class, testProjectmanager.getUserId());
        testProject = manager.find(Project.class, testProject.getProjectId());
        testOffer = manager.find(Offer.class, testOffer.getOfferId());

        assertNull(testLocation);
        assertNull(testLocation2);
        assertNull(testCompany);
        assertNull(testProjectmanager);
        assertNull(testProject);
        assertNull(testProject);
        assertNull(testOffer );
    }
}
