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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ApplicationFile;

/**
 *
 * @author Zikri
 */
public class FileLogic extends DatabaseConnection {

    CallableStatement callStatement;
    //PreparedStatement prepStatement;
    ResultSet result;

    public int insertFiles(ArrayList<ApplicationFile> files) throws ClassNotFoundException, SQLException {
        //returns how many rows is inserted in the DB
        int feedback = 0;
        String fileName;
        FileInputStream file;

        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_files_insert(?,?,?)}";
            for (ApplicationFile file1 : files) {
                fileName = file1.getFileName();
                file = file1.getFile();
                callStatement = conn.prepareCall(sql);
                callStatement.setInt(1, applicationId);
                callStatement.setBinaryStream(2, file);
                callStatement.setString(3, fileName);
                callStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //close the statement and the connection to the database
            callStatement.close();
            closeConnection();
        }
        return feedback;
    }
    
    public int updateFiles(ArrayList<ApplicationFile> files) throws ClassNotFoundException, SQLException {
        //returns how many rows is inserted in the DB
        int feedback = 0;
        String fileName;
        FileInputStream file;

        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_files_update(?,?,?)}";
            for (ApplicationFile file1 : files) {
                fileName = file1.getFileName();
                file = file1.getFile();
                callStatement = conn.prepareCall(sql);
                callStatement.setInt(1, applicationId);
                callStatement.setBinaryStream(2, file);
                callStatement.setString(3, fileName);
                callStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //close the statement and the connection to the database
            callStatement.close();
            closeConnection();
        }
        return feedback;
    }

    public ArrayList<ApplicationFile> findFilesByAppId() throws ClassNotFoundException, SQLException {
        ArrayList<ApplicationFile> files = new ArrayList<>();
        ApplicationFile applicationFile;
        String fileName;
        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_files_findByAppId(?)}";
            callStatement = conn.prepareCall(sql);
            callStatement.setInt(1, applicationId);

            //execute the query
            result = callStatement.executeQuery();
            while (result.next()) {
                fileName = result.getString("FileDescription");
                applicationFile = new ApplicationFile();
                applicationFile.setFileName(fileName);
                files.add(applicationFile);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //close the statement and the connection to the database
            result.close();
            callStatement.close();
            closeConnection();
        }
        return files;
    }

    public String downloadFile(String fileName) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
        InputStream inputStream = null;
        FileOutputStream outPutStream = null;
        String path = null;
        File file = null;
        try {
            //open connection and call the query, set the fields to the object
            openConnection();
            String sql = "{call sp_files_download(?,?)}";
            callStatement = conn.prepareCall(sql);
            callStatement.setInt(1, applicationId);
            callStatement.setString(2, fileName);
            //execute the query
            result = callStatement.executeQuery();
            if (fileName.equals("passport")) {
                file = new File(fileName + ".jpg");
            } else {
                file = new File(fileName + ".pdf");
            }
            outPutStream = new FileOutputStream(file);
            if (result.next()) {
                inputStream = result.getBinaryStream("File");
                byte[] buffer = new byte[1024];
                while (inputStream.read(buffer) > 0) {
                    outPutStream.write(buffer);
                }
                path = file.getAbsolutePath();
            }
            else{
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
}
