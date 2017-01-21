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

    private static Location testLocation;
    private static Freelancer testFreelancer;

    static final String education = "FH Joanneum";
    static final String availability = "available";
    static final int hourly_wage = 13;
    static final String profession = "Software Engineer Freelancer";


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
    public void A_create() {

        testLocation = new Location(TestLocationQuery.address3, TestLocationQuery.country3, TestLocationQuery.zip3, TestLocationQuery.city3);
        assertNotNull(testLocation);

        testFreelancer = new Freelancer(TestUserQuery.name1, TestUserQuery.email1, TestUserQuery.password1, TestFreelancerQuery.profession, TestFreelancerQuery.availability, TestFreelancerQuery.hourly_wage, TestFreelancerQuery.education, testLocation);
        assertNotNull(testFreelancer);

    }

    @Test
    public void B_repoTest() {
        FreelancerRepository freelancerRepo = new FreelancerRepository(manager);
        Freelancer testFreelancer1 = freelancerRepo.findByName(TestUserQuery.name1);

        assertEquals(testFreelancer.getName(), testFreelancer1.getName());
        assertEquals(testFreelancer.getEmail(), testFreelancer1.getEmail());
        assertEquals(testFreelancer.getPassword(), testFreelancer1.getPassword());
        assertEquals(testFreelancer.getAvailability(), testFreelancer1.getAvailability());
        assertEquals(testFreelancer.getProfession(), testFreelancer1.getProfession());
        assertEquals(testFreelancer.getHourly_wage(), testFreelancer1.getHourly_wage());
        assertEquals(testFreelancer.getEducation(), testFreelancer1.getEducation());

    }



}
