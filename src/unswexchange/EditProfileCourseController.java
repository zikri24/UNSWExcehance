/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unswexchange;

import dataAccess.AccountLogic;
import static dataAccess.AccountLogic.currentId;
import static dataAccess.AccountLogic.name;
import dataAccess.EducationLogic;
import dataAccess.ProfileLogic;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import model.Education;
import model.Profile;

/**
 * FXML Controller class
 *
 * @author ismmedina
 */
public class EditProfileCourseController implements Initializable {

    private EducationLogic eduLogic;
    private Education education;
    private ProfileLogic profileLogic;
    private AccountLogic accountLogic;

    public EditProfileCourseController() {
        profileLogic = new ProfileLogic();
        accountLogic = new AccountLogic();
        eduLogic = new EducationLogic();
    }

    private Profile profile;
    Stage stage;
    Parent root;

    @FXML
    private TextField degreeNameText;
    @FXML
    private TextField programCodeText;
    @FXML
    private ComboBox facultyCombo;
    @FXML
    private ComboBox yearOfStudyCombo;
    @FXML
    private TextField degreeCompletedText;
    @FXML
    private TextField currentWam;
    @FXML
    private TextField currentMajor;
    @FXML
    private Button updateButton;
    @FXML
    private Button backButton;
    @FXML
    private Label welcomeLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label studentMajorLabel;
    @FXML
    private Label yearOfStudyLabel;
    @FXML
    private ImageView profileImageView;

    @FXML
    private void updateButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        String degreeName = degreeNameText.getText();
        String programCode = programCodeText.getText();
        String faculty = facultyCombo.getValue().toString();
        String yearOfStudy = yearOfStudyCombo.getValue().toString();
        int yearToComplete = Integer.parseInt(degreeCompletedText.getText());
        double wam = Double.parseDouble(currentWam.getText());
        String major = currentMajor.getText();
        education = new Education(currentId, degreeName, programCode, faculty, yearOfStudy, yearToComplete, wam, major);
       
        int feedback = eduLogic.updateEducation(education);
        if (feedback > 0) {
            //scene switch to RegisterFormCompleteDet.FXML
            //get reference to the button's stage         
            stage = (Stage) updateButton.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("StudentMyProfile.fxml"));
            //create a new scene with root and set the stage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            //TODO: Form Validation on submit
        }
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
    private void backButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        //scene switch to RegisterFormCompleteDet.FXML
        //get reference to the button's stage         
        stage = (Stage) backButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("StudentMyProfile.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void myDashPressed(ActionEvent event) throws IOException {
        stage = (Stage) welcomeLabel.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("StudentDashboard_1.fxml"));
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
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        welcomeLabel.setText("Welcom" + " " + name);
        BufferedImage imageBytes = null;
        String imageUrl = "file:src\\maleprofilecircle2.jpg";
        Image proImage = new Image(imageUrl);
        try {
            profile = profileLogic.finProfileById();
            imageBytes = accountLogic.findProfileImage();
            File file = new File("src\\new-photo.jpg");
            ImageIO.write(imageBytes, "jpg", file);
            imageUrl = "file:src\\new-photo.jpg";
            proImage = new Image(imageUrl);
        } catch (ClassNotFoundException | SQLException | IOException ex) {
            Logger.getLogger(StudentMyProfileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            imageUrl = "file:src\\icon-large-support.png";
            proImage = new Image(imageUrl);
        }
        profileImageView.setImage(proImage);
        degreeNameText.setText(profile.getDegreeName());
        programCodeText.setText(profile.getProgramCode());
        facultyCombo.setValue(profile.getFaculty());
        yearOfStudyCombo.setValue(profile.getYearOfStudy());
        degreeCompletedText.setText(String.valueOf(profile.getYearToComplete()));
        currentWam.setText(String.valueOf(profile.getWam()));
        currentMajor.setText(profile.getMajor());
        nameLabel.setText(profile.getFirstName() + " " + profile.getLastName());
        studentMajorLabel.setText(profile.getMajor());
        yearOfStudyLabel.setText(profile.getYearOfStudy());
        
         ObservableList<String> Faculty = FXCollections.observableArrayList("Business", "Art & Design", "Engineering", "Medicine", "Science"
        );
        ObservableList<String> yearOfStudy = FXCollections.observableArrayList("1st Year", "2nd Year", "3rd Year", "4th Year", "5th Year", "6th Year"
        );
        //set comboBox components
        yearOfStudyCombo.setItems(yearOfStudy);
        facultyCombo.setItems(Faculty);
    }

}
