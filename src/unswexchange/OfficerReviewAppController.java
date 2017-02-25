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
import dataAccess.ApplicationLogic;
import static dataAccess.ApplicationLogic.applicationId;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Profile;

/**
 * FXML Controller class
 *
 * @author CocoMango
 */
public class OfficerReviewAppController implements Initializable {

    //declair private filed 
    private ApplicationLogic applicationLogic;
    private AccountLogic accountLogic;

    public OfficerReviewAppController() {
        applicationLogic = new ApplicationLogic();
        accountLogic = new AccountLogic();
    }

    private Profile profile;

    Stage stage;
    Parent root;

    @FXML
    private Button backButton;
    @FXML
    private Label welcomeLabel;
    @FXML
    private Label studentNameLabel;
    @FXML
    private Label studentIdLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label contactNumberLabel;
    @FXML
    private Label degreeLabel;
    @FXML
    private Label codeLabel;
    @FXML
    private Label majorLabel;
    @FXML
    private Label yearLabel;
    @FXML
    private Label wamLabel;
    @FXML
    private Label finishLabel;
    @FXML
    private Label uniNameLabel;
    @FXML
    private Label countryLabel;
    @FXML
    private Label partnerLabel;
    @FXML
    private Label creditLabel;
    @FXML
    private Label startLabel;
    @FXML
    private Label endLabel;

    @FXML
    private void backButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        //scene switch to RegisterFormCompleteDet.FXML
        //get reference to the button's stage         
        stage = (Stage) backButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("OfficerMyApps.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void submitButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) backButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("StudentApply.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void logoutPressed(ActionEvent event) throws IOException {
        stage = (Stage) backButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void coursesHyperPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) backButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("OfficerReviewCoursesMatch.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void attachmentsHyperPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) backButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("OfficerReviewAttachments.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void questionsHyperPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) backButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("OfficerReviewQuest.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void reviewHyperPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) backButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("OfficerApplicationDecision.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
      @FXML
    private void myDashPressed(ActionEvent event) throws IOException {
        stage = (Stage) welcomeLabel.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("OfficerDashboard.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void aboutPressed(ActionEvent event) throws IOException {
        stage = (Stage) welcomeLabel.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("aboutFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        welcomeLabel.setText("Welcom" + " " + name);

        try {
            profile = applicationLogic.findProfileByAppId(applicationId);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OfficerReviewAppController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //welcomeLabel.setText("Welcome " + profile.getFirstName());
        emailLabel.setText(profile.getEmail());
        contactNumberLabel.setText(profile.getPhone());
        degreeLabel.setText(profile.getDegreeName());
        majorLabel.setText(profile.getMajor());
        studentNameLabel.setText(profile.getFirstName() + " " + profile.getLastName());
        codeLabel.setText(profile.getProgramCode());
        yearLabel.setText(profile.getYearOfStudy());
        wamLabel.setText(String.valueOf(profile.getWam()));
        finishLabel.setText(String.valueOf(profile.getYearToComplete()));
        studentIdLabel.setText(profile.getStudentId());
        uniNameLabel.setText(profile.getUniName());
        countryLabel.setText(profile.getUniCountry());
        if (profile.isUnswPartner()) {
            partnerLabel.setText("Yes");
        } else {
            partnerLabel.setText("No");
        }
        if (profile.isUnswPartner()) {
            creditLabel.setText("Yes");
        } else {
            creditLabel.setText("No");
        }
        startLabel.setText(String.valueOf(profile.getStartDate()));
        endLabel.setText(String.valueOf(profile.getFinishDate()));
    }
}
