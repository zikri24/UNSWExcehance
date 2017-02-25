/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccess;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Log;

/**
 *
 * @author Zikri
 */
public class LogsLogic extends DatabaseConnection{
    CallableStatement statement;
    ResultSet result;
    
     public ArrayList<Log> findLogsByAppId(int applicationNumber) throws ClassNotFoundException, SQLException {
        ArrayList<Log> logs = new ArrayList<>();
        Log log;
        String description;
        Date logDate;
        String firstName;
        String lastName;
        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_logs_findByAppId(?)}";
            statement = conn.prepareCall(sql);
            statement.setInt(1, applicationNumber);

            //execute the query
            result = statement.executeQuery();
            while (result.next()) {
                description = result.getString("Description");
                logDate = result.getDate("Date");
                firstName = result.getString("FirstName");
                lastName = result.getString("LastName");
                log = new Log(firstName, lastName, description, logDate);
                logs.add(log);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //close the statement and the connection to the database
            result.close();
            statement.close();
            closeConnection();
        }

        return logs;
    }
    
    
     public int insertLog(int applicationNumber, String description, int staffId) throws ClassNotFoundException, SQLException {

        int feedback = 0;
        
        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_logs_insert(?,?,?)}";
            statement = conn.prepareCall(sql);
            statement.setInt(1, applicationNumber);
             statement.setInt(2, staffId);
            statement.setString(3, description);
           
           
            //execute the query
            feedback = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //close the statement and the connection to the database
            statement.close();
            closeConnection();
        }
        return feedback;
    }
}
