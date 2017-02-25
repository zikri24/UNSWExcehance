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
package unswexchange;

//list all imports
import dataAccess.AccountLogic;
import static dataAccess.AccountLogic.name;
import dataAccess.EducationLogic;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Account;
import model.Education;

/**
 * FXML Controller class
 *
 * @author CocoMango
 */
public class StudentLoginEditController implements Initializable {

    private Account account;
    private Education education;
    private final AccountLogic accountLogic;
    private final EducationLogic educationLogic;

    public StudentLoginEditController() {
        accountLogic = new AccountLogic();
        educationLogic = new EducationLogic();
    }
    //Declairing Root and Statge for 
    Stage stage;
    Parent root;

    @FXML
    private TextField zIDTextField;
    @FXML
    private TextField confirmzIDTextField;
   
    @FXML
    private Button backButton;
    @FXML
    private Label welcomeLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label yearOfStudyLabel;
    @FXML
    private Label degreesLabel;
    
    @FXML
    private PasswordField newPasswordTextField;
    @FXML
    private PasswordField confirmPasswordTextField;

    @FXML
    private void saveButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        String zID = zIDTextField.getText();
        String zIDConfirm = confirmzIDTextField.getText();
        String password = newPasswordTextField.getText();
        String confirmPassword = confirmPasswordTextField.getText();
        if (password.equals(confirmPassword) && zID.equals(zIDConfirm)) {
            accountLogic.updateLoginDetails(zID, password);
            //get reference to the button's stage         
            stage = (Stage) backButton.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("StudentMyProfile.fxml"));
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("The Email or Password don't match!");
            alert.show();
            stage = (Stage) backButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("RegistrationEmail.fxml"));
        }

        /*
         String email = emailTextField.getText();
         String emailConfirm = emailTextField.getText();
         if (email.equals(emailConfirm)) {
         email = emailTextField.getText();
         }
         else{
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Information Dialog");
         alert.setHeaderText(null);
         alert.setContentText("The Email or Password don't match!");
         alert.show();
         stage = (Stage) backButton.getScene().getWindow();
         root = FXMLLoader.load(getClass().getResource("RegistrationEmail.fxml"));   
         }
        
         String password = newPasswordTextField.getText();
         String confirmPassword = confirmPasswordTextField.getText();
         if (password.equals(confirmPassword)) {
         accountLogic.updateLoginDetails(email, password);
         //get reference to the button's stage         
         stage = (Stage) backButton.getScene().getWindow();
         //load up OTHER FXML document
         root = FXMLLoader.load(getClass().getResource("loginForm.fxml"));
         } else {
         confirmLabel.setText("Password don't match");
         }
         */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void backButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) backButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("StudentMyProfile.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

        /**
     * Initializes the controller class.
     */
    @FXML
    private void logoutPressed(ActionEvent event) throws IOException {
        stage = (Stage) welcomeLabel.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            account = accountLogic.findAccountById();
            education = educationLogic.findEducationById();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentLoginEditController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StudentLoginEditController.class.getName()).log(Level.SEVERE, null, ex);
        }

        zIDTextField.setText(account.getStudentId());
       // passwordTextField.setText(account.getPassword());
        //welcomeLabel.setText("Welcome " + name);
        nameLabel.setText(name);
        yearOfStudyLabel.setText(education.getYearOfStudy());
        degreesLabel.setText(education.getDegreeName());
    }
}
