/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccess;

import static dataAccess.ApplicationLogic.applicationId;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import model.CourseMatch;

/**
 *
 * @author Zikri
 */
public class CourseLogic extends DatabaseConnection {

    private Course course;
     private CourseMatch courseMatch;
    public static int courseId;
    CallableStatement statement;
    ResultSet result;

    public int insertCourse(Course course) throws SQLException, ClassNotFoundException {
        int feedback = 0;

        String foreignCode = course.getForeignCode();
        String foreignName = course.getForeignName();
        String unswCode = course.getUnswCode();
        String unswName = course.getUnswName();
        FileInputStream courseOutline = course.getOutline();
        int applicationNo = course.getApplicationNo();
        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_courses_insert(?,?,?,?,?,?)}";
            statement = conn.prepareCall(sql);
            statement.setString(1, foreignCode);
            statement.setString(2, foreignName);
            statement.setString(3, unswCode);
            statement.setString(4, unswName);
            statement.setBinaryStream(5, courseOutline);
            statement.setInt(6, applicationNo);
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

    public ArrayList<Course> findCoursesByAppId(int applicationNo) throws SQLException, ClassNotFoundException {

        ArrayList<Course> list = new ArrayList<>();
        int courseId;
        String foreignCode;
        String foreignName;
        String unswCode;
        String unswName;
        boolean previouslyMatched;
        String status;
        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_courses_findByAppId(?)}";
            statement = conn.prepareCall(sql);
            statement.setInt(1, applicationNo);
            result = statement.executeQuery();
            while (result.next()) {
                courseId = result.getInt("CourseId");
                foreignCode = result.getString("foreignCode");
                foreignName = result.getString("foreignName");
                unswCode = result.getString("unswCode");
                unswName = result.getString("foreignName");
                previouslyMatched = result.getBoolean("Match");
                status = result.getString("Status");
                course = new Course(courseId, foreignCode, foreignName, unswCode, unswName, previouslyMatched, status);
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

    public Course findCoursesById(int courseId) throws SQLException, ClassNotFoundException {
        Course course = null;
        String foreignCode;
        String foreignName;
        String unswCode;
        String unswName;
        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_courses_findById(?)}";
            statement = conn.prepareCall(sql);
            statement.setInt(1, courseId);
            result = statement.executeQuery();
            while (result.next()) {
                foreignCode = result.getString("foreignCode");
                foreignName = result.getString("foreignName");
                unswCode = result.getString("unswCode");
                unswName = result.getString("foreignName");
                course = new Course(foreignCode, foreignName, unswCode, unswName);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //close the statement and the connection to the database
            result.close();
            statement.close();
            closeConnection();
        }
        return course;
    }

    public int removeCourse(int courseId) throws SQLException, ClassNotFoundException {
        int feedback = 0;
        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_courses_removeCourse(?)}";
            statement = conn.prepareCall(sql);
            statement.setInt(1, courseId);
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

    public int acceptCourse(int courseId) throws SQLException, ClassNotFoundException {
        int feedback = 0;
        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_courses_accept(?)}";
            statement = conn.prepareCall(sql);
            statement.setInt(1, courseId);
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

    public int declineCourse(int courseId) throws SQLException, ClassNotFoundException {
        int feedback = 0;
        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_courses_decline(?)}";
            statement = conn.prepareCall(sql);
            statement.setInt(1, courseId);
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

    public int acceptCourseOnCondition(int courseId, String condition) throws SQLException, ClassNotFoundException {
        int feedback = 0;
        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_courses_acceptOnCondition(?,?)}";
            statement = conn.prepareCall(sql);
            statement.setInt(1, courseId);
            statement.setString(2, condition);
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

    public int updateCourse(Course course) throws SQLException, ClassNotFoundException {
        int feedback = 0;
        int courseId = course.getCourseId();
        String foreignCode = course.getForeignCode();
        String foreignName = course.getForeignName();
        String unswCode = course.getUnswCode();
        String unswName = course.getUnswName();
        FileInputStream courseOutline = course.getOutline();

        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_courses_insert(?,?,?,?,?,?)}";
            statement = conn.prepareCall(sql);
            statement.setString(1, foreignCode);
            statement.setString(2, foreignName);
            statement.setString(3, unswCode);
            statement.setString(4, unswName);
            statement.setBinaryStream(5, courseOutline);
            statement.setInt(6, courseId);
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

    public String downloadCourseOutline(int courseId) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
        InputStream inputStream = null;
        FileOutputStream outPutStream = null;
        String path = null;
        File file = null;

        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_files_courseOutline(?)}";
            statement = conn.prepareCall(sql);
            statement.setInt(1, courseId);
            //execute the query
            result = statement.executeQuery();
            file = new File("Course Outline.pdf");

            outPutStream = new FileOutputStream(file);
            if (result.next()) {
                inputStream = result.getBinaryStream("CourseOutline");
                byte[] buffer = new byte[1024];
                while (inputStream.read(buffer) > 0) {
                    outPutStream.write(buffer);
                }
                path = file.getAbsolutePath();
            } else {
                path = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outPutStream != null) {
                outPutStream.close();
            }
            result.close();
            statement.close();
            closeConnection();
        }
        return path;
    }

    public CourseMatch findCourseMatchDetails(String foreignCourse, String unswCourse) throws SQLException, ClassNotFoundException {

        String foreignCode;
        String foreignName;
        String unswCode;
        String unswName;
        String status;
        java.sql.Date dateReviewed;
        String firstName;
        String lastName;
        String uniName;
        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_courses_courseMatchDetails(?,?)}";
            statement = conn.prepareCall(sql);
            statement.setString(1, foreignCourse);
            statement.setString(2, unswCourse);
            result = statement.executeQuery();
            while (result.next()) {
                foreignCode = result.getString("foreignCode");
                foreignName = result.getString("foreignName");
                unswCode = result.getString("unswCode");
                unswName = result.getString("UnswName");
                status = result.getString("Status");
                dateReviewed = result.getDate("DateReviewed");
                firstName = result.getString("FirstName");
                lastName = result.getString("LastName");
                uniName = result.getString("UniversityName");  
                
                courseMatch = new CourseMatch(foreignCode, foreignName, unswCode, unswName, status, dateReviewed, firstName,lastName, uniName);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //close the statement and the connection to the database
            result.close();
            statement.close();
            closeConnection();
        }
        return courseMatch;
    }

}
