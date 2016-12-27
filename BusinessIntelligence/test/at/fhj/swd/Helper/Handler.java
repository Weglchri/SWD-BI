package at.fhj.swd.Helper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.assertNotNull;

public class Handler extends TestData{

    public static EntityManagerFactory factory;
    public static EntityManager manager;
    public static EntityTransaction transaction;

    private static final ScriptLoader Loader = new ScriptLoader();

    private static final String persistenceUnitName = "BusinessIntelligence";


    public static void build() { Loader.executeSqlScript("sql/create.sql"); }

    public static void insert() {
        Loader.executeSqlScript("sql/insert.sql");
    }

    public static void destroy() { Loader.executeSqlScript("sql/drop.sql"); }


    public static void init() {
        factory = Persistence.createEntityManagerFactory(persistenceUnitName);
        assertNotNull(factory);
        manager = factory.createEntityManager();
        assertNotNull(manager);
        transaction = manager.getTransaction();
        assertNotNull(transaction);
    }

    public static void close() {
        if (manager == null) return;
        manager.close();
        factory.close();
    }

}
