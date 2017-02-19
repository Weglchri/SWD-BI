package at.fhj.swd.JoinTest;

import at.fhj.swd.BusinessIntelligence.Offer;
import at.fhj.swd.BusinessIntelligenceRepositories.FreelancerRepository;
import at.fhj.swd.Helper.JdbcHandler;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestFreelanceJoinOffer extends JdbcHandler
{

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
    public void getAllCreatedOffersByFreelancer()
    {
        FreelancerRepository freelancerRepository = new FreelancerRepository(manager);
        List<Offer>  offersPerFreelancer= freelancerRepository.getAllOffersByFreelancer("Somebody");

        assertEquals(2, offersPerFreelancer.size());
    }

}
