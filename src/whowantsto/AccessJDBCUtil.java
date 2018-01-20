/*
 * AccessJDBCUtil.java
 *
 * Created on 04 September 2007, 14:32
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package whowantsto;

/**
 *
 * @author Rodney
 */
import java.sql.*;

public class AccessJDBCUtil {
    private static final String accessDBURLPrefix = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=";
    private static final String accessDBURLSuffix = ";DriverID=22;READONLY=true}";
    private Connection Connection;
    ResultSet Resultset;
    private String db_filename;
  
    static {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        } catch(ClassNotFoundException e) {
            System.err.println("JdbcOdbc Bridge Driver not found!");
            // ABORT ABORT... How? System.exit(1) is not nice from webapp...
        }
    }
    // construtor method, sets up a DB connection
    public AccessJDBCUtil(String filename)
    {
        db_filename = filename;
        try
        {
            Connection = getAccessDBConnection(db_filename);
        }
        catch(SQLException sqle)
        {
             System.err.println("Could not open connection to the Database");
        }
        
    }
    
    /** Creates a Connection to a Access Database
     * @param filename
     * @return 
     * @throws java.sql.SQLException */
    public static java.sql.Connection getAccessDBConnection(String filename) throws SQLException {
        filename = filename.replace('\\', '/').trim();
        String databaseURL = accessDBURLPrefix + filename + accessDBURLSuffix;
        // System.err.println("Datebase URL: " + databaseURL);
        return DriverManager.getConnection(databaseURL, "", "");
    }  
    
    // method to setup current resultset
   public ResultSet setUpResultSet(String query)
   {
       
       try
       {
            Statement stmt = Connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            Resultset = stmt.executeQuery(query);
       }
       catch(SQLException sqle)
        {
             System.err.println(sqle);
        }
       return Resultset;
       
   }
    
    /*
    public static void main(String args[])
    {
        AccessJDBCUtil Db = new AccessJDBCUtil();
        try
        {
            Connection con = Db.getAccessDBConnection("C:/parish.mdb");
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
          ResultSet srs = stmt.executeQuery("SELECT COUNT(*) FROM Parish_Households");
           srs.first();
           System.out.println(srs.getString(1));
            srs.close();
        }
        catch(SQLException sqle)
        {
             System.err.println(sqle);
        }
        
    }*/
}