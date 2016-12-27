package at.fhj.swd.QueryTest;

import at.fhj.swd.BusinessIntelligence.Company;
import at.fhj.swd.BusinessIntelligence.CompanyRepository;
import at.fhj.swd.BusinessIntelligence.Location;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCompanyQuery {
    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "BusinessIntelligence";

    //data for Location1
    static final String address = "Alte Poststraße 122/13";
    static final String country = "Austria";
    static final Integer zip = 8020;
    static final String city = "Graz";
    //data for Company1
    static final String company_name = "Stahl Incorporation";
    static final String branch = "Stahlbau";
    static final String branchMerge = "Bergbau";

    //data for project1
    static final Integer capital = 80000;
    Date date = new Date();
    static final String task = "Website-Programming";
    static final String taskMerge = "Database-Design";

    //data for Location2
    static final String address1 = "Lugner City 1";
    static final String country1 = "Austria";
    static final Integer zip1 = 1010;
    static final String city1 = "Wien";
    //data for Company2
    static final String company_name1 = "Stahl Incorporation GmbH";
    static final String branch1 = "Stahlbau";
    static final String branchMerge1 = "";

    //data for test
    private static Location testAddress;
    private static Location testAddress1;
    private static Company testCompany;
    private static Company testCompany1;
    private static List<Company> companyBranchSearch;


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
    public void A_create()
    {
        transaction.begin();

        this.testAddress = new Location(address, country, zip, city);
        assertNotNull(testAddress);
        manager.persist(testAddress);

        this.testAddress1 = new Location(address1, country1, zip1, city1);
        assertNotNull(testAddress1);
        manager.persist(testAddress1);

        this.testCompany = new Company(company_name, branch, testAddress);
        assertNotNull(testCompany);
        manager.persist(testCompany);

        this.testCompany1 = new Company(company_name1, branch1, testAddress1);
        assertNotNull(testCompany1);
        manager.persist(testCompany1);

        transaction.commit();
    }

    @Test
    public void B_repoTest()
    {
        CompanyRepository companyRepo = new CompanyRepository(manager);
        CompanyRepository companyRepo1 = new CompanyRepository(manager);

        Company companySearch1 = companyRepo.findByName(company_name);
        Company companySearch2 = companyRepo1.findByName(company_name1);

        assertEquals(companySearch1.getBranch(), testCompany.getBranch());
        assertEquals(companySearch1.getCompany(), testCompany.getCompany());
        assertEquals(companySearch1.getAddress(), testCompany.getAddress());

        assertEquals(companySearch2.getBranch(), testCompany1.getBranch());
        assertEquals(companySearch2.getCompany(), testCompany1.getCompany());
        assertEquals(companySearch2.getAddress(), testCompany1.getAddress());
    }

    @Test
    public void C_repoTestGetBranche()
    {
        CompanyRepository companyRepo = new CompanyRepository(manager);
        companyBranchSearch = companyRepo.findByBranch(branch);

        assertEquals(2, companyBranchSearch.size());
    }

    @Test
    public void D_remove()
    {
        transaction.begin();

        Company testCompany = manager.find(Company.class, company_name);
        Company testCompany1 = manager.find(Company.class, company_name1);
        assertNotNull(testCompany);
        assertNotNull(testCompany1);

        Location testAddress = manager.find(Location.class, address);
        Location testAddress1 = manager.find(Location.class, address1);
        assertNotNull(testAddress);
        assertNotNull(testAddress1);

        manager.remove(testCompany);
        manager.remove(testCompany1);
        manager.remove(testAddress);
        manager.remove(testAddress1);

        transaction.commit();

        testAddress = manager.find(Location.class, address);
        testAddress1 = manager.find(Location.class, address1);
        assertEquals(null, testAddress);
        assertEquals(null, testAddress1);

        testCompany = manager.find(Company.class, company_name);
        testCompany1 = manager.find(Company.class, company_name1);
        assertNull(testAddress);
        assertNull(testAddress1);
        assertNull(testCompany);
        assertNull(testCompany1);
    }

}
