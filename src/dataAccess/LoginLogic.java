/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccess;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.LoginModel;

/**
 *
 * @author Zikri
 */
public class LoginLogic extends DatabaseConnection {
/*
    //declare statement and result set
    CallableStatement callStatement;
    //PreparedStatement prepStatement;
    ResultSet result;
    public static int currentId;
    public static String name;

    public LoginModel login(String email, String password) throws ClassNotFoundException, SQLException {
        LoginModel loginModel = new LoginModel();
        String role;

        boolean login;
        try {
            openConnection();
            String sql = "{call sp_accounts_login(?,?,?,?,?,?)}";
            callStatement = conn.prepareCall(sql);
            callStatement.setString(1, email);
            callStatement.setString(2, password);
            callStatement.registerOutParameter(3, java.sql.Types.BOOLEAN);
            callStatement.registerOutParameter(4, java.sql.Types.INTEGER);
            callStatement.registerOutParameter(5, java.sql.Types.VARCHAR);
            callStatement.registerOutParameter(6, java.sql.Types.VARCHAR);
            callStatement.executeUpdate();
            //get the output results 
            login = callStatement.getBoolean(3);
            currentId = callStatement.getInt(4);
            role = callStatement.getString(5);
            name = callStatement.getString(6);
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
*/
}
