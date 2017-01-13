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

    private static Location testAddress;
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

        testAddress = new Location(TestLocationQuery.address, TestLocationQuery.country, TestLocationQuery.zip, TestLocationQuery.city);
        assertNotNull(testAddress);

        testCompany = new Company(TestCompanyQuery.company_name, TestCompanyQuery.branch, testAddress);
        assertNotNull(testCompany);
        manager.persist(testCompany);

        testFreelancer = new Freelancer(TestUserQuery.name1, TestUserQuery.email1, TestUserQuery.password1, TestFreelancerQuery.profession, TestFreelancerQuery.availability, TestFreelancerQuery.hourly_wage, TestFreelancerQuery.education, testAddress );
        assertNotNull(testFreelancer);
        manager.persist(testFreelancer);

        testProject = new Project (TestProjectQuery.capital, TestProjectQuery.date, TestProjectQuery.task, testCompany);
        assertNotNull(testProject);
        manager.persist(testProject);

        testOffer = new Offer(price, date, testFreelancer, testProject);
        assertNotNull(testOffer);
        manager.persist(testOffer);

    }

    @Test
    public void B_repoTest() {
        OfferRepository offerRepo = new OfferRepository(manager);
        Offer testOffer1 = offerRepo.findByName(price);

        assertEquals(testOffer.getPrice(), testOffer1.getPrice());
        assertEquals(testOffer.getFkUserId().getName(), testOffer1.getFkUserId().getName());
        assertEquals(testOffer.getFkUserId().getEmail(), testOffer1.getFkUserId().getEmail());
        assertEquals(testOffer.getFkUserId().getPassword(), testOffer1.getFkUserId().getPassword());
        assertEquals(testOffer.getFkProjectId().getCapital(), testOffer1.getFkProjectId().getCapital());
        assertEquals(testOffer.getFkProjectId().getTask(), testOffer1.getFkProjectId().getTask());
        assertEquals(testOffer.getFkProjectId().getCompanyName(), testOffer1.getFkProjectId().getCompanyName());

    }

}
