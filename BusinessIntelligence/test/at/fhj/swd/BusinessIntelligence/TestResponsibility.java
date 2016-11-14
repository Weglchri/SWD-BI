package at.fhj.swd.BusinessIntelligence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.util.Date;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.*;

public class TestResponsibility
{
    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "BusinessIntelligence";

    //data for User 1
    static final int id1 = 1;
    static final String name1 = "Björn Sattler";
    static final String email1 = "bjoern.sattler@edu.fh-joanneum.at";
    static final String password1 = "*******";

    //data for Projectmanager 1
    static final Integer involved1 = 1;
    static final String function1 = "Personal";

    //data for User 2
    static final int id2 = 2;
    static final String name2 = "Christopher Wegl";
    static final String email2 = "christopher.wegl@edu.fh-joanneum.at";
    static final String password2 = "*********";

    //data for Projectmanager 2
    static final Integer involved2 = 2;
    static final String function2 = "Engineer";

    //data for User 3
    static final int id3 = 3;
    static final String name3 = "Harald Habiger";
    static final String email3 = "harald.habiger@fh-joanneum.at";
    static final String password3 = "***";

    //data for Projectmanager 3
    static final Integer involved3 = 3;
    static final String function3 = "Teacher";

    //data for Project 1
    static final Integer capital = 1000;
    Date date1 = new Date();
    static final String task = "Website-Programming";

    //data for Project 2
    static final Integer capital1 = 10000;
    Date date2 = new Date();
    static final String task1 = "Software Quality Testing";

    //date for tests
    private static Projectmanager managerSattler;
    private static Projectmanager managerWegl;
    private static Projectmanager managerHabiger;
    private static Project projectWebsite;
    private static Project projectQualityCheck;


    @BeforeClass
    public static void setup() {
        factory = Persistence.createEntityManagerFactory(persistenceUnitName);
        assertNotNull(factory);
        manager = factory.createEntityManager();
        assertNotNull(manager);
        transaction = manager.getTransaction();
    }

    @AfterClass
    public static void teardown() {
        if (manager == null) return;
        manager.close();
        factory.close();
    }

    @Test
    public void create()
    {
        transaction.begin();

        this.managerSattler = new Projectmanager(name1, email1, password1, involved1, function1, null);
        this.managerWegl = new Projectmanager(name2, email2, password2, involved2, function2, null);
        this.managerHabiger = new Projectmanager(name3, email3, password3, involved3, function3, null);
        assertNotNull(managerSattler);
        assertNotNull(managerWegl);
        assertNotNull(managerHabiger);
        manager.persist(managerSattler);
        manager.persist(managerWegl);
        manager.persist(managerHabiger);


        this.projectWebsite = new Project(capital, date1, task, null);
        this.projectQualityCheck = new Project(capital1, date2, task1, null);
        assertNotNull(projectWebsite);
        assertNotNull(projectQualityCheck);
        manager.persist(projectWebsite);
        manager.persist(projectQualityCheck);

        managerSattler.addProject(projectWebsite);
        managerSattler.addProject(projectQualityCheck);
        managerWegl.addProject(projectWebsite);
        managerHabiger.addProject(projectQualityCheck);

        transaction.commit();


        assertFalse(managerWegl.getProjects().contains(projectQualityCheck));
        assertFalse(managerHabiger.getProjects().contains(projectWebsite));
        assertTrue(managerSattler.getProjects().contains(projectWebsite));
        assertTrue(managerHabiger.getProjects().contains(projectQualityCheck));

        assertTrue(projectWebsite.getProjectmanagers().contains(managerSattler));
        assertTrue(projectQualityCheck.getProjectmanagers().contains(managerSattler));
        assertFalse(projectQualityCheck.getProjectmanagers().contains(managerWegl));
        assertFalse(projectWebsite.getProjectmanagers().contains(managerHabiger));

    }

    @Test
    public void remove() {

        transaction.begin();

        manager.remove(managerSattler);
        manager.remove(managerWegl);
        manager.remove(managerHabiger);
        manager.remove(projectWebsite);
        manager.remove(projectQualityCheck);

        transaction.commit();

        this.projectWebsite = manager.find(Project.class, projectWebsite.getProjectId());
        this.projectQualityCheck =  manager.find(Project.class, projectQualityCheck.getProjectId());
        this.managerSattler = manager.find(Projectmanager.class, managerSattler.getUserId());
        this.managerWegl = manager.find(Projectmanager.class, managerWegl.getUserId());
        this.managerHabiger = manager.find(Projectmanager.class, managerHabiger.getUserId());

        assertNull(managerSattler);
        assertNull(managerWegl);
        assertNull(managerHabiger);
        assertNull(projectWebsite);
        assertNull(projectQualityCheck);

    }

}

