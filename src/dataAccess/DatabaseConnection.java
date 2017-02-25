/**
 *
 * INFS3605 Assignment Two - Group Assignment - UNSW Exchange The Student
 * Exchange application allows exchange officers and student central
 * administrators to handle the CRUD functionality related to a students
 * outbound exchange application. As such a student can register for the system
 * and submit an application. At this point, an exchange officer can search,
 * view, self-assign, accept or decline and application. The application will
 * also handle reporting and email notification functionality.
 *
 * @author CocoMango
 *
 * @version 1.0
 */
package dataAccess;

//list all imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Database Connection class
 *
 * @author CocoMango
 */
public class DatabaseConnection {
    //declare connection to database
    protected Connection conn;
    
    
    /**
     * Open connection to the Database
     * 
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    protected void openConnection() throws ClassNotFoundException, SQLException {

        if (conn == null) {
            try {
                //try connect to sql database with the given url
                String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                Class.forName(driverName);
                String username = "sa";
                String password = "3091982";
               //String url = "jdbc:sqlserver://208.91.198.174:1433;databaseName=studentExchange;user=omar;password=Z!kr!.15";
               //conn = DriverManager.getConnection(url);
                
               conn = DriverManager.getConnection("jdbc:sqlserver://OMAR:1433;databaseName=studentExchange;user=sa;password=30919823");
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
      /*
     * method for closing connection 
     * 
     */
    protected void closeConnection(){
        try {
            if (conn != null) {
                //close the connection and reset conn
                conn.close();
                conn = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
