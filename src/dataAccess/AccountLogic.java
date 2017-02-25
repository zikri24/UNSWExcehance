/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccess;

import static dataAccess.ApplicationLogic.applicationId;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import model.Account;
import model.LoginModel;

/**
 *
 * @author Zikri
 */
public class AccountLogic extends DatabaseConnection {

    private Account account;
    public static int currentId;
    public static String name;
    public static String school;
    //declare statement and result set
    CallableStatement callStatement;
    //PreparedStatement prepStatement;
    ResultSet result;

    public int register(String zID, String password, String role) throws ClassNotFoundException, SQLException {
        
        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_account_register(?,?,?,?)}";
            callStatement = conn.prepareCall(sql);
            callStatement.setString(1, password);
            callStatement.setString(2, zID);
            callStatement.setString(3, role);
            callStatement.registerOutParameter(4, java.sql.Types.INTEGER);
            //execute the query
            callStatement.executeUpdate();
            currentId = callStatement.getInt(4);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //close the statement and the connection to the database
            callStatement.close();
            closeConnection();
        }
        return currentId;
    }

    public int insertAccount(Account account) throws ClassNotFoundException, SQLException {
        //returns how many rows is inserted in the DB
        int feedback = 0;

        name = account.getFirstName();
        String lastName = account.getLastName();
        String email = account.getEmail();
        String sex = account.getSex();
        String street = account.getStreet();
        String city = account.getCity();
        String state = account.getState();
        String postcode = account.getPostcode();
        java.sql.Date dob = account.getDob();
        String phone = account.getPhone();

        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_accounts_insert(?,?,?,?,?,?,?,?,?,?,?)}";
            callStatement = conn.prepareCall(sql);
            callStatement.setString(1, name);
            callStatement.setString(2, lastName);
            callStatement.setString(3, email);
            callStatement.setString(4, sex);
            callStatement.setString(5, street);
            callStatement.setString(6, city);
            callStatement.setString(7, state);
            callStatement.setString(8, postcode);
            callStatement.setDate(9, dob);
            callStatement.setString(10, phone);
            callStatement.setInt(11, currentId);

            //callStatement.registerOutParameter(13, java.sql.Types.INTEGER);
            //execute the query
            feedback = callStatement.executeUpdate();
            // currentId = callStatement.getInt(13);

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //close the statement and the connection to the database
            callStatement.close();
            closeConnection();
        }
        return feedback;
    }

    //Find user by ID
    public Account findAccountById() throws ClassNotFoundException, SQLException {
        try {
            openConnection();
            String sql = "{call sp_account_findById(?)}";
            callStatement = conn.prepareCall(sql);
            callStatement.setInt(1, currentId);
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
                account = new Account(firstName, lastName, studentId, sex, email, street, city, state, postcode, dob, phone);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            callStatement.close();
            closeConnection();
        }
        return account;
    }

    //Update Login details
    public int updateLoginDetails(String zID, String password) throws ClassNotFoundException, SQLException {
        int feedback = 0;
        try {
            openConnection();
            String sql = "{call sp_accounts_update_login(?,?,?)}";
            callStatement = conn.prepareCall(sql);
            callStatement.setString(1, zID);
            callStatement.setString(2, password);
            callStatement.setInt(3, currentId);
            feedback = callStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            callStatement.close();
            closeConnection();
        }
        return feedback;
    }

    //Update Personal Information
    public int updateAccount(Account account) throws ClassNotFoundException, SQLException {
        //returns how many rows is inserted in the DB
        int feedback = 0;

        String firstName = account.getFirstName();
        String lastName = account.getLastName();
        String studentId = account.getStudentId();
        String sex = account.getSex();
        String street = account.getStreet();
        String city = account.getCity();
        String state = account.getState();
        String postcode = account.getPostcode();
        java.sql.Date dob = account.getDob();
        String phone = account.getPhone();

        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_accounts_update(?,?,?,?,?,?,?,?,?,?,?)}";
            callStatement = conn.prepareCall(sql);

            callStatement.setInt(1, currentId);
            callStatement.setString(2, firstName);
            callStatement.setString(3, lastName);
            callStatement.setString(4, studentId);
            callStatement.setString(5, sex);
            callStatement.setString(6, street);
            callStatement.setString(7, city);
            callStatement.setString(8, state);
            callStatement.setString(9, postcode);
            callStatement.setDate(10, dob);
            callStatement.setString(11, phone);

            //execute the query
            feedback = callStatement.executeUpdate();
            // currentId = callStatement.getInt(13);

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //close the statement and the connection to the database
            callStatement.close();
            closeConnection();
        }
        return feedback;
    }

    public LoginModel login(String zID, String password) throws ClassNotFoundException, SQLException {
        LoginModel loginModel = new LoginModel();
        String role;

        boolean login;
        try {
            openConnection();
            String sql = "{call sp_accounts_login(?,?,?,?,?,?,?)}";
            callStatement = conn.prepareCall(sql);
            callStatement.setString(1, zID);
            callStatement.setString(2, password);
            callStatement.registerOutParameter(3, java.sql.Types.BOOLEAN);
            callStatement.registerOutParameter(4, java.sql.Types.INTEGER);
            callStatement.registerOutParameter(5, java.sql.Types.VARCHAR);
            callStatement.registerOutParameter(6, java.sql.Types.VARCHAR);
            callStatement.registerOutParameter(7, java.sql.Types.VARCHAR);
            callStatement.executeUpdate();
            //get the output results 
            login = callStatement.getBoolean(3);
            currentId = callStatement.getInt(4);
            role = callStatement.getString(5);
            name = callStatement.getString(6);
            school = callStatement.getString(7);

            //set it to the login model 
            loginModel.setLogin(login);
            loginModel.setAccountId(currentId);
            loginModel.setRole(role);
            loginModel.setName(name);

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            callStatement.close();
            closeConnection();
        }
        return loginModel;
    }

    public int uploadImage(FileInputStream stream) throws ClassNotFoundException, SQLException {
        //returns how many rows is inserted in the DB
        int feedback = 0;
        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_accounts_insertImage(?,?)}";
            callStatement = conn.prepareCall(sql);

            callStatement.setInt(1, currentId);
            callStatement.setBinaryStream(2, stream);

            //execute the query
            feedback = callStatement.executeUpdate();
            // currentId = callStatement.getInt(13);

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //close the statement and the connection to the database
            callStatement.close();
            closeConnection();
        }
        return feedback;
    }

    public BufferedImage findProfileImage() throws ClassNotFoundException, SQLException, IOException {
        byte[] imageBytes = null;
        BufferedImage bImageFromConvert = null;
        OutputStream outStream = null;
        InputStream inputStream = null;
        try {
            openConnection();
            String sql = "{call sp_accounts_getProfileImage(?)}";
            callStatement = conn.prepareCall(sql);
            callStatement.setInt(1, currentId);
            result = callStatement.executeQuery();
            outStream = new FileOutputStream("progileImage");

            while (result.next()) {
                imageBytes = result.getBytes("Image");
                InputStream in = new ByteArrayInputStream(imageBytes);
                bImageFromConvert = ImageIO.read(in);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outStream != null) {
                outStream.close();
            }
            result.close();
            callStatement.close();
            closeConnection();
        }
        return bImageFromConvert;
    }

    public String downloadTranscript() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
        InputStream inputStream = null;
        FileOutputStream outPutStream = null;
        String path = null;
        File file = null;
        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_application_downloadTranscript(?)}";
            callStatement = conn.prepareCall(sql);
            callStatement.setInt(1, applicationId);
            //execute the query
            result = callStatement.executeQuery();
            file = new File("transcript.pdf");
            outPutStream = new FileOutputStream(file);
            if (result.next()) {
                inputStream = result.getBinaryStream("Transcript");
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
            callStatement.close();
            closeConnection();
        }
        return path;
    }
     public Account findPassword(String zID) throws ClassNotFoundException, SQLException {
        try {
            openConnection();
            String sql = "{call sp_accounts_findEmailById(?)}";
            callStatement = conn.prepareCall(sql);
            callStatement.setString(1, zID);
            result = callStatement.executeQuery();
            while (result.next()) {
                String email = result.getString("Email");
                String password = result.getString("password");
                account = new Account();
                account.setEmail(email);
                account.setPassword(password);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            callStatement.close();
            closeConnection();
        }
        return account;
    }

}
