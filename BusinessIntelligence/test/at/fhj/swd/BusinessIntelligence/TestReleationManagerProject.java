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

/**
 * Created by sattlerb on 14/11/16.
 */
public class TestReleationManagerProject
{
    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "BusinessIntelligence";

    //data for User 1
    static final int id = 1;
    static final String name = "Björn Sattler";
    static final String email = "bjoern.sattler@edu.fh-joanneum.at";
    static final String password = "*******";
    //data for Projectmanager 1
    static final Integer involved = 1;
    static final String function = "Personal";
    static final String functionMerge = "Zulieferung";


    //data for User 2
    static final int id1 = 2;
    static final String name1 = "Christopher Wegl";
    static final String email1 = "christopher.wegl@edu.fh-joanneum.at";
    static final String password1 = "*********";
    //data for Projectmanager 2
    static final Integer involved1 = 1;
    static final String function1 = "Engineer";
    static final String functionMerge1 = "Deploy";

    //data for User 3
    static final int id2 = 3;
    static final String name2 = "Harald Habiger";
    static final String email2 = "harald.habiger@fh-joanneum.at";
    static final String password2 = "***";
    //data for Projectmanager 3
    static final Integer involved2 = 2;
    static final String function2 = "Teacher";
    static final String functionMerge2 = "Lecture Room";


    //data for Project
    static final Integer capital = 1000;
    Date date = new Date();
    static final String task = "Website-Programming";
    static final String taskMerge = "Database-Design";

    //data for Project1
    static final Integer capital1 = 10000;
    Date date1 = new Date();
    static final String task1 = "Software Quality Testing";
    static final String taskMerge1 = "SW_QUAL";

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

        this.managerSattler = new Projectmanager(name, email, password, involved, function, null);
        this.managerWegl = new Projectmanager(name1, email1, password1, involved1, function1, null);
        this.managerHabiger = new Projectmanager(name2, email2, password2, involved2, function2, null);
        assertNotNull(managerSattler);
        assertNotNull(managerWegl);
        assertNotNull(managerHabiger);
        manager.persist(managerSattler);
        manager.persist(managerWegl);
        manager.persist(managerHabiger);


        this.projectWebsite = new Project(capital, date, task, null);
        this.projectQualityCheck = new Project(capital1, date1, task1, null);
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

//    @Test
//    public void modify() {
//        transaction.begin();
//
//        assertNotNull(managerSattler);
//
//        Projectmanager merge = manager.merge(managerSattler);
//        merge.setFunction(functionMerge);
//
//        transaction.commit();
//
//        managerSattler = manager.find(Projectmanager.class, managerSattler.getUserId());
//        assertEquals(functionMerge, managerSattler.getFunction());
//    }

//    @Test
//    public void remove() {
//        transaction.begin();
//
//        manager.remove(managerSattler);
//        manager.remove(testCompany);
//        manager.remove(testAddress);
//
//        transaction.commit();
//
//        this.testAddress = manager.find(Location.class, testAddress.getAddress());
//        this.testCompany= manager.find(Company.class, testCompany.getCompany());
//        this.managerSattler = manager.find(Projectmanager.class, managerSattler.getUserId());
//
//        assertNull(managerSattler);
//        assertNull(testCompany);
//        assertNull(testAddress);
//
//    }

}

