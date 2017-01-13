package at.fhj.swd.QueryTest;

import at.fhj.swd.BusinessIntelligence.*;
import at.fhj.swd.BusinessIntelligenceRepositories.OfferRepository;
import at.fhj.swd.Helper.JdbcHandler;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestOfferQuery extends JdbcHandler {

    private static Location testLocation;
    private static Company testCompany;
    private static Freelancer testFreelancer;
    private static Project testProject;
    private static Offer testOffer;


    static final int price = 100;
    static final Date date = new Date();


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

        testLocation = new Location(TestLocationQuery.address, TestLocationQuery.country, TestLocationQuery.zip, TestLocationQuery.city);
        assertNotNull(testLocation);

        testCompany = new Company(TestCompanyQuery.company_name, TestCompanyQuery.branch, testLocation);
        assertNotNull(testCompany);

        testFreelancer = new Freelancer(TestUserQuery.name1, TestUserQuery.email1, TestUserQuery.password1, TestFreelancerQuery.profession, TestFreelancerQuery.availability, TestFreelancerQuery.hourly_wage, TestFreelancerQuery.education, testLocation);
        assertNotNull(testFreelancer);

        testProject = new Project (TestProjectQuery.capital, TestProjectQuery.date, TestProjectQuery.task, testCompany);
        assertNotNull(testProject);

        testOffer = new Offer(price, date, testFreelancer, testProject);
        assertNotNull(testOffer);

    }

    @Test
    public void B_repoTest() {
        OfferRepository offerRepo = new OfferRepository(manager);
        Offer testOffer1 = offerRepo.findByName(price);

        assertEquals(testOffer.getPrice(), testOffer1.getPrice());
        assertEquals(testOffer.getFkProjectId().getCompanyName().getCompany(), testOffer1.getFkProjectId().getCompanyName().getCompany());
        assertEquals(testOffer.getFkUserId().getName(), testOffer1.getFkUserId().getName());

    }

}
