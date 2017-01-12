package at.fhj.swd.Helper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.assertNotNull;

public class JdbcHandler {

    public static EntityManagerFactory factory;
    public static EntityManager manager;
    public static EntityTransaction transaction;

    private static final ScriptLoader Loader = new ScriptLoader();

    private static final String persistenceUnitName = "BusinessIntelligence";


    public static final void build() { Loader.executeSqlScript("sql/create.sql"); }

    public static final void insert() {Loader.executeSqlScript("sql/insert.sql");}

    public static final void dropTables() {
        Loader.executeSqlScript("sql/drop.sql");
        dropSequences();
    }

    private static final void dropSequences() {
        Loader.executeSqlScript("sql/drop_Sequences.sql");
    }


    public static final void init() {
        factory = Persistence.createEntityManagerFactory(persistenceUnitName);
        assertNotNull(factory);
        manager = factory.createEntityManager();
        assertNotNull(manager);
        transaction = manager.getTransaction();
        assertNotNull(transaction);
    }

    public static final void close() {
        if (manager == null) return;
        manager.close();
        factory.close();
    }

    public static final void buildSecurity(){
        Loader.executeSqlScript("sql/security_create.sql");
    }


    public static final void revokeSecurity(){
        Loader.executeSqlScript("sql/security_drop.sql");
    }

}
