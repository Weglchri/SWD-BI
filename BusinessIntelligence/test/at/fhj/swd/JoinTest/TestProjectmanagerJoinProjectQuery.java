package at.fhj.swd.JoinTest;

import at.fhj.swd.BusinessIntelligence.*;
import at.fhj.swd.BusinessIntelligence.Project;
import at.fhj.swd.BusinessIntelligenceRepositories.ProjectmanagerRepository;
import at.fhj.swd.Helper.JdbcHandler;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.util.List;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestProjectmanagerJoinProjectQuery extends JdbcHandler
{

    private static final int expectedInvolvedIn = 3;

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
    public void B_repoTest() {
        ProjectmanagerRepository projectmanagerRepo = new ProjectmanagerRepository(manager);
        List<Project> projects = projectmanagerRepo.findAllProjectsOfProjectmanager("Someone");

        assertEquals(expectedInvolvedIn, projects.size());
    }
}
