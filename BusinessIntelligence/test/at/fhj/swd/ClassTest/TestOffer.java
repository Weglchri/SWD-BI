package at.fhj.swd.ClassTest;

import at.fhj.swd.BusinessIntelligence.*;
import at.fhj.swd.Helper.JdbcHandler;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestOffer extends JdbcHandler
{

    private static Location testLocation;
    private static Location testLocation2;
    private static Freelancer testFreelancer;
    private static Company testCompany;
    private static Project testProject;
    private static Offer testOffer;

    static final Integer price = 100;
    static final Integer newPrice = 120;
    static final Date date = new Date();


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

        testLocation2 = new Location(TestLocation.address2, TestLocation.country, TestLocation.zip, TestLocation.city);
        assertNotNull(testLocation2);
        manager.persist(testLocation2);

        testCompany = new Company(TestCompany.company_name, TestCompany.branch, testLocation);
        assertNotNull(testCompany);
        manager.persist(testCompany);

        testFreelancer = new Freelancer(TestUser.name, TestUser.email, TestUser.password, TestFreelancer.profession, TestFreelancer.availability, TestFreelancer.hourly_wage, TestFreelancer.education, testLocation2);
        assertNotNull(testFreelancer);
        manager.persist(testFreelancer);

        testProject = new Project (TestProject.capital, TestProject.date, TestProject.task, testCompany);
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
        manager.remove(testFreelancer);
        manager.remove(testCompany);
        manager.remove(testLocation2);
        manager.remove(testLocation);

        transaction.commit();

        testLocation = manager.find(Location.class, testLocation.getAddress());
        testLocation2 = manager.find(Location.class, testLocation2.getAddress());
        testCompany= manager.find(Company.class, testCompany.getCompanyName());
        testFreelancer= manager.find(Freelancer.class, testFreelancer.getUserId());
        testProject = manager.find(Project.class, testProject.getProjectId());
        testOffer = manager.find(Offer.class, testOffer.getOfferId());

        assertNull(testLocation);
        assertNull(testLocation2);
        assertNull(testCompany);
        assertNull(testProject);
        assertNull(testProject);
        assertNull(testOffer );
    }
}
