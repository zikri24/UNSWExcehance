/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccess;




import static dataAccess.AccountLogic.currentId;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Education;

/**
 *
 * @author Zikri
 */
public class EducationLogic extends DatabaseConnection {

    private Education education;
    //declare statement and result set
    CallableStatement callStatement;
    //PreparedStatement prepStatement;
    ResultSet result;

    public int insertEducation(Education education) throws SQLException, ClassNotFoundException {
        int feedback = 0;
        
        String degreeName = education.getDegreeName();
        String programCode = education.getProgramCode();
        String faculty = education.getFaculty();
        String yearOfStudy = education.getYearOfStudy();
        int yearToComplete = education.getYearToComplete();
        double wam = education.getWam();
        String major = education.getMajor();

        try {
            openConnection();
            String sql = "{call sp_educations_insert(?,?,?,?,?,?,?,?)}";
            callStatement = conn.prepareCall(sql);
            callStatement.setInt(1, currentId);
            callStatement.setString(2, degreeName);
            callStatement.setString(3, programCode);
            callStatement.setString(4, faculty);
            callStatement.setString(5, yearOfStudy);
            callStatement.setInt(6, yearToComplete);
            callStatement.setDouble(7, wam);
            callStatement.setString(8, major);            
            //callStatement.registerOutParameter(8, java.sql.Types.INTEGER);
            feedback = callStatement.executeUpdate();
            //accountId = callStatement.getInt(8);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //close the statement and the connection to the database
            callStatement.close();
            closeConnection();
        }
        return feedback;
    }

    //ind the student education details 
    public Education findEducationById() throws ClassNotFoundException, SQLException {
        try {
            openConnection();
            String sql = "{call sp_education_findByAccountId(?)}";
            callStatement = conn.prepareCall(sql);
            callStatement.setInt(1, currentId);
          
            result = callStatement.executeQuery();
            while(result.next()){
            String degreeName = result.getString("DegreeName");
            String programCode = result.getString("ProgramCode");
            String faculty = result.getString("Faculty");
            String yearOfStudy = result.getString("YearOfStudy");
            int yearToComplete = result.getInt("YearToComple");
            double wam = result.getDouble("WAM");
            education = new Education(degreeName, programCode, faculty, yearOfStudy, yearToComplete, wam);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            callStatement.close();
            closeConnection();
        }
        return education;
    }
    //Update Education Information
    public int updateEducation(Education education) throws SQLException, ClassNotFoundException {
        int feedback = 0;
        
        String degreeName = education.getDegreeName();
        String programCode = education.getProgramCode();
        String faculty = education.getFaculty();
        String yearOfStudy = education.getYearOfStudy();
        int yearToComplete = education.getYearToComplete();
        double wam = education.getWam();
        String major = education.getMajor();

        try {
            openConnection();
            String sql = "{call sp_education_update(?,?,?,?,?,?,?,?)}";
            callStatement = conn.prepareCall(sql);
            callStatement.setInt(1, currentId);
            callStatement.setString(2, degreeName);
            callStatement.setString(3, programCode);
            callStatement.setString(4, faculty);
            callStatement.setString(5, yearOfStudy);
            callStatement.setInt(6, yearToComplete);
            callStatement.setDouble(7, wam);
            callStatement.setString(8, major);
            
            //callStatement.registerOutParameter(8, java.sql.Types.INTEGER);

            feedback = callStatement.executeUpdate();
            //accountId = callStatement.getInt(8);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //close the statement and the connection to the database
            callStatement.close();
            closeConnection();
        }
        return feedback;
    }
}
