package at.fhj.swd.QueryTest;

import at.fhj.swd.BusinessIntelligence.Freelancer;
import at.fhj.swd.BusinessIntelligenceRepositories.FreelancerRepository;
import at.fhj.swd.BusinessIntelligence.Location;
import at.fhj.swd.Helper.JdbcHandler;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestFreelancerQuery extends JdbcHandler {

    private static Location testAddress;
    private static Freelancer testFreelancer;

    static final String eduacation = "FH Joanneum";
    static final String availability = "available";
    static final int hourly_wage = 13;
    static final String profession = "Software Engineer Freelancer";


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
    public void A_create() {
        transaction.begin();

        testAddress = new Location(TestLocationQuery.address, TestLocationQuery.country, TestLocationQuery.zip, TestLocationQuery.city);
        assertNotNull(testAddress);
        manager.persist(testAddress);

        testFreelancer = new Freelancer(TestUserQuery.name, TestUserQuery.email, TestUserQuery.password, TestFreelancerQuery.profession, TestFreelancerQuery.availability, TestFreelancerQuery.hourly_wage, TestFreelancerQuery.eduacation, testAddress );
        assertNotNull(testFreelancer);
        manager.persist(testFreelancer);

        transaction.commit();
    }

    @Test
    public void B_repoTest() {
        FreelancerRepository freelancerRepo = new FreelancerRepository(manager);
        Freelancer testFreelancer1 = freelancerRepo.findByName(TestUserQuery.name);

        assertEquals(testFreelancer1.getName(), testFreelancer.getName());
        assertEquals(testFreelancer1.getEmail(), testFreelancer.getEmail());
        assertEquals(testFreelancer1.getPassword(), testFreelancer.getPassword());
        assertEquals(testFreelancer1.getUserId(), testFreelancer.getUserId());
        assertEquals(testFreelancer1.getAvailability(), testFreelancer.getAvailability());
        assertEquals(testFreelancer1.getProfession(), testFreelancer.getProfession());
        assertEquals(testFreelancer1.getHourly_wage(), testFreelancer.getHourly_wage());
        assertEquals(testFreelancer1.getEducation(), testFreelancer.getEducation());
        assertEquals(testFreelancer1.getAddress(), testFreelancer.getAddress());
    }

    @Test
    public void C_remove() {
        transaction.begin();

        assertNotNull(testAddress);
        assertNotNull(testFreelancer);

        manager.remove(testAddress);
        manager.remove(testFreelancer);

        transaction.commit();

        testFreelancer = manager.find(Freelancer.class, testFreelancer.getUserId());
        assertNull(testFreelancer);
    }


}
