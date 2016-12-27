package at.fhj.swd.Helper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class ScriptLoader
{
    public static final String SQL_STATEMENT_DELIMITER = ";";

    private String driver;
    private String url;
    private String user;
    private String password;


    public ScriptLoader()
    {
        this("sql/jdbc.properties");
    }

    public ScriptLoader(String propertyFileName)
    {
        if(propertyFileName == null || propertyFileName.length() == 0)
            throw new IllegalArgumentException("Invalid property file name!");

        try
        {
            Properties jdbcProperties = new Properties();
            jdbcProperties.load(new FileInputStream(propertyFileName));
            driver = jdbcProperties.getProperty("jdbc.driver");
            url = jdbcProperties.getProperty("jdbc.url");
            user = jdbcProperties.getProperty("jdbc.username");
            password = jdbcProperties.getProperty("jdbc.password");
        }
        catch(Exception e)
        {
            throw new IllegalStateException("Unable to load " + propertyFileName + "!");
        }

    }

    public void executeSqlScript(String sqlScriptFileName)
    {
        if(sqlScriptFileName == null || sqlScriptFileName.length() == 0)
            throw new IllegalArgumentException("Invalid SQL script file name!");

        try
        {
            String sqlScript = loadSqlScript(sqlScriptFileName);
            String[] sqlStatements = sqlScript.split(SQL_STATEMENT_DELIMITER);
            executeSqlStatements(sqlStatements);
        }
        catch(Exception e)
        {
            throw new RuntimeException("Can't execute SQL script: " + e.getMessage());
        }
    }

    public void executeSqlStatement(String sqlStatement)
            throws ClassNotFoundException, SQLException, FileNotFoundException, IOException
    {
        if(sqlStatement == null || sqlStatement.length() == 0)
            throw new IllegalArgumentException("Empty sqlStatement!");

        Connection con = null;
        Statement st = null;
        try
        {
            con = getConnection();
            st = con.createStatement();
            st.execute(sqlStatement);
        }
        finally
        {
            if(st != null)
                st.close();
            if(con != null)
                con.close();
        }
    }

    public void executeSqlStatements(String[] sqlStatements)
            throws SQLException, FileNotFoundException, IOException, ClassNotFoundException
    {
        if(sqlStatements == null)
            throw new IllegalArgumentException("Empty sqlStatement array!");

        Connection con = null;
        Statement st = null;
        try
        {
            con = getConnection();
            con.setAutoCommit(false);
            st = con.createStatement();
            for(String sqlStatement : sqlStatements)
            {
                st.execute(sqlStatement);
            }
            con.commit();
        }
        finally
        {
            if(st != null)
                st.close();
            if(con != null)
            {
                con.rollback();
                con.close();
            }
        }
    }

    protected String loadSqlScript(String sqlScriptFileName)
            throws IOException
    {
        if(sqlScriptFileName == null || sqlScriptFileName.length() == 0)
            throw new IllegalArgumentException("Invalid SQL script file name!");

        BufferedReader in = new BufferedReader(new FileReader(sqlScriptFileName));
        StringBuilder buffer = new StringBuilder();
        String line;
        while((line = in.readLine()) != null)
        {
            if(isCommentLine(line))
                continue;
            buffer.append(line.trim());
        }
        in.close();
        return buffer.toString();
    }

    protected boolean isCommentLine(String line)
    {
        if(line == null || line.length() == 0)
            return false;

        String comment = line.trim();
        if(comment.startsWith("--") || comment.startsWith("//"))
            return true;
        else
            return false;
    }


    protected Connection getConnection()
            throws ClassNotFoundException, SQLException
    {
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, user, password);
        return con;
    }


}