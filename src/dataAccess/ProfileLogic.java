/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccess;


import  dataAccess.AccountLogic;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Profile;

/**
 *
 * @author Zikri
 */
public class ProfileLogic extends DatabaseConnection {

    private Profile profile;
    //declare statement and result set
    CallableStatement callStatement;
    PreparedStatement prepStatement;
    ResultSet result;

    public Profile finProfileById() throws ClassNotFoundException, SQLException {
        
        try {
            openConnection();
            String sql = "{call sp_profile_findById(?)}";
            callStatement = conn.prepareCall(sql);
            callStatement.setInt(1, dataAccess.AccountLogic.currentId);
            result = callStatement.executeQuery();
            while (result.next()) {
                String firstName = result.getString("FirstName");
                String lastName = result.getString("LastName");
                String studentId = result.getString("StudentId");
                String sex = result.getString("Sex");
                String email = result.getString("Email");
                String street = result.getString("Street");
                String city = result.getString("City");
                String state = result.getString("State");
                String postcode = result.getString("Postcode");
                java.sql.Date dob = result.getDate("DOB");
                String phone = result.getString("Phone");
                String degreeName = result.getString("DegreeName");
                String programCode = result.getString("ProgramCode");
                String faculty = result.getString("Faculty");
                String yearOfStudy = result.getString("YearOfStudy");
                int yearToComplete = result.getInt("YearToComple");
                double wam = result.getDouble("WAM");
                String major = result.getString("Major");                

                profile = new Profile(firstName, lastName, studentId, sex, email, street, city, state,
                        postcode, dob,phone, degreeName, programCode, faculty, yearOfStudy, yearToComplete, wam, major);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            result.close();
            callStatement.close();
            closeConnection();
        }
        return profile;
    }
}
