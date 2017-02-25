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
import static dataAccess.AccountLogic.currentId;
import static dataAccess.AccountLogic.school;
import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Applic;
import model.ApplicationStatus;
import model.Course;
import model.Profile;
import model.Search;

/**
 * Application Logic class
 *
 * @author CocoMango
 */
public class ApplicationLogic extends DatabaseConnection {

    //declare statement and result set
    CallableStatement statement;
    ResultSet result;
    //declare applicationID variable
    public static int applicationId;

    /**
     *
     * @param application Inset application description
     * @return applicationID
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public int insertApplication(Applic application) throws ClassNotFoundException, SQLException {
        //set application ID
        //applicationId = 0;
        int accountId = application.getAccountId();
        String uniName = application.getUniName();
        String uniCountry = application.getUniCountry();
        Date startDate = application.getStartDate();
        Date finishDate = application.getFinishDate();
        boolean unswPartner = application.isUnswPartner();
        boolean creditTransferToUnsw = application.isCreditTransferToUnsw();
        FileInputStream transcript = application.getTranscript();

        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_application_insertApplication(?,?,?,?,?,?,?,?,?)}";
            statement = conn.prepareCall(sql);
            statement.setString(1, uniName);
            statement.setString(2, uniCountry);
            statement.setDate(3, startDate);
            statement.setDate(4, finishDate);
            statement.setBoolean(5, unswPartner);
            statement.setBoolean(6, creditTransferToUnsw);
            statement.setBinaryStream(7, transcript);
            statement.setInt(8, accountId);
            statement.registerOutParameter(9, java.sql.Types.INTEGER);

            //execute the query
            int feedback = statement.executeUpdate();
            applicationId = statement.getInt(9);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //close the statement and the connection to the database
            statement.close();
            closeConnection();
        }
        return applicationId;
    }

    public int updateApplicationGen(Applic application) throws ClassNotFoundException, SQLException {
        int feedback = 0;
        String uniName = application.getUniName();
        String uniCountry = application.getUniCountry();
        Date startDate = application.getStartDate();
        Date finishDate = application.getFinishDate();
        boolean unswPartner = application.isUnswPartner();
        boolean creditTransferToUnsw = application.isCreditTransferToUnsw();
        FileInputStream transcript = application.getTranscript();

        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_application_updateGen(?,?,?,?,?,?,?,?)}";
            statement = conn.prepareCall(sql);
            statement.setString(1, uniName);
            statement.setString(2, uniCountry);
            statement.setDate(3, startDate);
            statement.setDate(4, finishDate);
            statement.setBoolean(5, unswPartner);
            statement.setBoolean(6, creditTransferToUnsw);
            statement.setBinaryStream(7, transcript);
            statement.setInt(8, applicationId);
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

    public int acceptApplication(int applicatioNo) throws ClassNotFoundException, SQLException {
        int feedback = 0;
        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_application_accept(?,?)}";
            statement = conn.prepareCall(sql);
            statement.setInt(1, applicatioNo);
            statement.setInt(2, currentId);
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

    public int declineApplication(int applicatioNo, String comments) throws ClassNotFoundException, SQLException {
        int feedback = 0;
        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_application_decline(?,?,?)}";
            statement = conn.prepareCall(sql);
            statement.setInt(1, applicatioNo);
            statement.setString(2, comments);
            statement.setInt(3, currentId);
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

    public int insertAppQuestions(Applic application) throws ClassNotFoundException, SQLException {

        int feedback = 0;
        String q1 = application.getSupportQues();
        String q2 = application.getDemoQues();
        String q3 = application.getBringBackQues();
        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_application_insertQuestions(?,?,?,?)}";
            statement = conn.prepareCall(sql);
            statement.setString(1, q1);
            statement.setString(2, q2);
            statement.setString(3, q3);
            statement.setInt(4, applicationId);
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

    public ArrayList<Applic> findApplicationsById() throws ClassNotFoundException, SQLException {
        ArrayList<Applic> applications = new ArrayList<>();
        Applic application;
        String uni;
        Date submitDate;
        String status;
        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call s_applications_findByAccId(?)}";
            statement = conn.prepareCall(sql);
            statement.setInt(1, currentId);

            //execute the query
            result = statement.executeQuery();
            while (result.next()) {
                applicationId = result.getInt("ApplicationNo");
                uni = result.getString("UniversityName");
                submitDate = result.getDate("DateSubmitted");
                status = result.getString("Status");
                application = new Applic(applicationId, uni, submitDate, status);
                applications.add(application);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //close the statement and the connection to the database
            result.close();
            statement.close();
            closeConnection();
        }

        return applications;
    }

    public ArrayList<Applic> findAppsByStaffId() throws ClassNotFoundException, SQLException {
        ArrayList<Applic> applications = new ArrayList<>();
        Applic application;
        String studentId;
        String uni;
        Date submitDate;
        String status;
        int applicationNo;
        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_applications_findByStaffId(?)}";
            statement = conn.prepareCall(sql);
            statement.setInt(1, currentId);
            //execute the query
            result = statement.executeQuery();
            while (result.next()) {
                studentId = result.getString("StudentId");
                uni = result.getString("UniversityName");
                submitDate = result.getDate("DateSubmitted");
                status = result.getString("Status");
                applicationNo = result.getInt("ApplicationNo");
                application = new Applic(studentId, uni, submitDate, status, applicationNo);
                applications.add(application);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //close the statement and the connection to the database
            result.close();
            statement.close();
            closeConnection();
        }

        return applications;
    }
    
    public ArrayList<Applic> sortAppsByStaffId(String sortBy) throws ClassNotFoundException, SQLException {
        ArrayList<Applic> applications = new ArrayList<>();
        Applic application;
        String studentId;
        String uni;
        Date submitDate;
        String status;
        int applicationNo;
        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_applications_sortForOfficer(?,?)}";
            statement = conn.prepareCall(sql);
            statement.setInt(1, currentId);
             statement.setString(2, sortBy);
            //execute the query
            result = statement.executeQuery();
            while (result.next()) {
                studentId = result.getString("StudentId");
                uni = result.getString("UniversityName");
                submitDate = result.getDate("DateSubmitted");
                status = result.getString("Status");
                applicationNo = result.getInt("ApplicationNo");
                application = new Applic(studentId, uni, submitDate, status, applicationNo);
                applications.add(application);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //close the statement and the connection to the database
            result.close();
            statement.close();
            closeConnection();
        }

        return applications;
    }

    public ArrayList<Applic> findPendingApplications() throws ClassNotFoundException, SQLException {
        ArrayList<Applic> applications = new ArrayList<>();
        Applic application;
        String studentId;
        String uni;
        Date submitDate;
        String status;
        int applicationNo;
        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_application_findAllPendings(?)}";
            statement = conn.prepareCall(sql);
            statement.setString(1, school);
            //execute the query
            result = statement.executeQuery();
            while (result.next()) {
                studentId = result.getString("StudentId");
                uni = result.getString("UniversityName");
                submitDate = result.getDate("DateSubmitted");
                status = result.getString("Status");
                applicationNo = result.getInt("ApplicationNo");
                application = new Applic(studentId, uni, submitDate, status, applicationNo);
                applications.add(application);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //close the statement and the connection to the database
            result.close();
            statement.close();
            closeConnection();
        }

        return applications;
    }

    public int assignAppToMe(int applicationNo) throws ClassNotFoundException, SQLException {
        int feedback = 0;
        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_application_assignToMe(?,?)}";
            statement = conn.prepareCall(sql);
            statement.setInt(1, currentId);
            statement.setInt(2, applicationNo);
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

    public int unAssignApp(int applicationId) throws ClassNotFoundException, SQLException {
        int feedback = 0;
        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_application_unAssign(?,?)}";
            statement = conn.prepareCall(sql);
            statement.setInt(1, applicationId);
            statement.setInt(2, currentId);
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

    public Profile findProfileByAppId(int applicationNo) throws ClassNotFoundException, SQLException {

        Profile profile = null;
        String studentId;
        String uni;
        Date submitDate;
        String status;

        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_applications_findByAppNo(?)}";
            statement = conn.prepareCall(sql);
            statement.setInt(1, applicationId);
            //execute the query
            result = statement.executeQuery();
            while (result.next()) {
                studentId = result.getString("StudentId");
                String firstName = result.getString("FirstName");
                String lastName = result.getString("LastName");
                String email = result.getString("Email");
                String phone = result.getString("Phone");
                String degreeName = result.getString("DegreeName");
                String programCode = result.getString("ProgramCode");

                String yearOfStudy = result.getString("YearOfStudy");
                int yearToComplete = result.getInt("YearToComple");
                double wam = result.getDouble("WAM");
                String major = result.getString("Major");
                uni = result.getString("UniversityName");
                String country = result.getString("Country");
                boolean partner = result.getBoolean("UNSWPartner");
                boolean transferCredit = result.getBoolean("TransferCridetToUNSW");
                Date startDate = result.getDate("StartDate");
                Date ensdtDate = result.getDate("EndDate");
                profile = new Profile(firstName, lastName, studentId, email, phone, degreeName, programCode,
                        yearOfStudy, yearToComplete, wam, major, uni, country, partner, transferCredit, startDate, ensdtDate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //close the statement and the connection to the database
            result.close();
            statement.close();
            closeConnection();
        }
        return profile;
    }

    public Applic findApplicationByAppId() throws ClassNotFoundException, SQLException {
        Applic application = null;
        String uniName;
        String uniCountry;
        Date startDate;
        Date finishDate;
        boolean unswPartner;
        boolean creditTransferToUnsw;
        FileInputStream transcript;
        String q1;
        String q2;
        String q3;
        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_application_findByAppId(?)}";
            statement = conn.prepareCall(sql);
            statement.setInt(1, applicationId);
            //execute the query
            result = statement.executeQuery();
            while (result.next()) {
                uniName = result.getString("UniversityName");
                uniCountry = result.getString("Country");
                startDate = result.getDate("StartDate");
                finishDate = result.getDate("EndDate");
                unswPartner = result.getBoolean("UNSWPartner");
                creditTransferToUnsw = result.getBoolean("TransferCridetToUNSW");
                q1 = result.getString("SupportQuestion");
                q2 = result.getString("DemonstrationQuestion");
                q3 = result.getString("BringBackQuestion");
                application = new Applic(uniName, uniCountry, startDate, finishDate, unswPartner, creditTransferToUnsw, q1, q2, q3);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //close the statement and the connection to the database
            result.close();
            statement.close();
            closeConnection();
        }
        return application;
    }

    public ApplicationStatus findApplicationStatus() throws ClassNotFoundException, SQLException {
        ApplicationStatus applicationStatus = null;
        String status;
        String comments;

        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_applications_findStatus(?)}";
            statement = conn.prepareCall(sql);
            statement.setInt(1, applicationId);
            //execute the query
            result = statement.executeQuery();
            while (result.next()) {
                status = result.getString("Status");
                comments = result.getString("Comments");
                applicationStatus = new ApplicationStatus(status, comments);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //close the statement and the connection to the database
            result.close();
            statement.close();
            closeConnection();
        }
        return applicationStatus;
    }

    public ArrayList<Course> findCoursesAppIdForStudent() throws SQLException, ClassNotFoundException {
        Course course = null;
        ArrayList<Course> list = new ArrayList<>();
        String foreignCode;
        String status;
        String condition;
        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_courses_findByAppIdForStudent(?)}";
            statement = conn.prepareCall(sql);
            statement.setInt(1, applicationId);
            result = statement.executeQuery();
            while (result.next()) {
                foreignCode = result.getString("foreignCode");
                status = result.getString("Status");
                condition = result.getString("Condition");
                course = new Course();
                course.setForeignCode(foreignCode);
                course.setStatus(status);
                course.setConditions(condition);
                list.add(course);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //close the statement and the connection to the database
            result.close();
            statement.close();
            closeConnection();
        }
        return list;
    }

    public ArrayList<String> findEmailsBySchool(String school) throws ClassNotFoundException, SQLException {
        ArrayList<String> emails = new ArrayList<>();
        String email;
        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_accounts_findEmailsBySchool(?)}";
            statement = conn.prepareCall(sql);
            statement.setString(1, school);
            //execute the query
            result = statement.executeQuery();
            while (result.next()) {
                email = result.getString("Email");
                emails.add(email);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //close the statement and the connection to the database
            result.close();
            statement.close();
            closeConnection();
        }
        return emails;
    }

    public String findSchoolByAccountId() throws ClassNotFoundException, SQLException {
        String school = null;
        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_accounts_findSchoolByAccountId(?)}";
            statement = conn.prepareCall(sql);
            statement.setInt(1, currentId);
            //execute the query
            result = statement.executeQuery();
            while (result.next()) {
                school = result.getString("School");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //close the statement and the connection to the database
            result.close();
            statement.close();
            closeConnection();
        }
        return school;
    }

    public ArrayList<Applic> searchApplications(Search search) throws ClassNotFoundException, SQLException {
        ArrayList<Applic> applications = new ArrayList<>();
        Applic application;

        String studentNo;
        String uni;
        Date submitDate;
        String appStatus;
        int applicNo;

        String firstName = search.getFirstName();
        String lastName = search.getLastName();
        String studentId = search.getStudentNo();
        Date fromDate = search.getFromDate();
        Date toDate = search.getToDate();
        String status = search.getStatus();

        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_application_search(?,?,?,?,?,?)}";
            statement = conn.prepareCall(sql);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, studentId);
            statement.setDate(4, fromDate);
            statement.setDate(5, toDate);
            statement.setString(6, status);

            //execute the query
            result = statement.executeQuery();
            while (result.next()) {
                studentNo = result.getString("StudentId");
                uni = result.getString("UniversityName");
                submitDate = result.getDate("DateSubmitted");
                appStatus = result.getString("Status");
                applicNo = result.getInt("ApplicationNo");
                application = new Applic(studentNo, uni, submitDate, appStatus, applicNo);
                applications.add(application);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //close the statement and the connection to the database
            result.close();
            statement.close();
            closeConnection();
        }

        return applications;
    }

    public String findEmailByAppliocationId(int applicationNumber) throws ClassNotFoundException, SQLException {
        String email = null;

        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_accounts_findEmailByApplicationId(?)}";
            statement = conn.prepareCall(sql);
            statement.setInt(1, applicationNumber);
            //execute the query
            result = statement.executeQuery();
            while (result.next()) {
                email = result.getString("Email");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //close the statement and the connection to the database
            result.close();
            statement.close();
            closeConnection();
        }
        return email;
    }

    public int deleteByAppliocationId(int applicationNumber) throws ClassNotFoundException, SQLException {
        int feedback = 0;

        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_application_deleteById(?)}";
            statement = conn.prepareCall(sql);
            statement.setInt(1, applicationNumber);
            //execute the query
            feedback = statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //close the statement and the connection to the database
            result.close();
            statement.close();
            closeConnection();
        }
        return feedback;
    }

    public int countPendingAppliocation() throws ClassNotFoundException, SQLException {
        int count = 0;

        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_application_countPending(?)}";
            statement = conn.prepareCall(sql);
            statement.setString(1, school);
            //execute the query
            result = statement.executeQuery();
            while (result.next()) {
                count = result.getInt("Count");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //close the statement and the connection to the database
            result.close();
            statement.close();
            closeConnection();
        }
        return count;
    }

    public int countMyPendingAppliocation() throws ClassNotFoundException, SQLException {
        int count = 0;

        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_application_countMyApp(?,?)}";
            statement = conn.prepareCall(sql);

            statement.setInt(1, currentId);
            statement.setString(2, school);

            //execute the query
            result = statement.executeQuery();
            while (result.next()) {
                count = result.getInt("Count");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //close the statement and the connection to the database
            result.close();
            statement.close();
            closeConnection();
        }
        return count;
    }

}
