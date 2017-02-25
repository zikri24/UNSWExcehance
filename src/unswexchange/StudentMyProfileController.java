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
import dataAccess.ProfileLogic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
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
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import model.Profile;

/**
 * FXML Controller class
 *
 * @author CocoMango
 */
public class StudentMyProfileController implements Initializable {

    //declair private filed 
    private ProfileLogic profileLogic;
    private AccountLogic accountLogic;

    public StudentMyProfileController() {
        profileLogic = new ProfileLogic();
        accountLogic = new AccountLogic();
    }

    private Profile profile;

    Stage stage;
    Parent root;
    //declare all needed GUI Components
    @FXML
    private Label welcomeLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label degreeLabel;
    @FXML
    private Label majorLabel;
    @FXML
    private Label sideNameLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label studentIdLabel;
    @FXML
    private Label yearOfStudyLabel;
    @FXML
    private Label degreesLabel;
    @FXML
    private Button loginEditButton;
    @FXML
    private Button personalInfoEditButton;
    @FXML
    private Button courseInfoEditButton;
    @FXML
    private Button backButton;
    @FXML
    private ImageView profileImageView;

    @FXML
    private void loginEditButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) loginEditButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("StudentLoginEdit.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void personalEditButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) loginEditButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("StudentPersonalEdit.fxml"));
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
        root = FXMLLoader.load(getClass().getResource("StudentDashboard_1.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    

    @FXML
    private void logoutPressed(ActionEvent event) throws IOException {
        stage = (Stage) welcomeLabel.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void courseEditButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) loginEditButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("EditProfileCourse.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /*
     @FXML
     private void courseEditButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
     //scene switch to RegisterFormCompleteDet.FXML 
     //get reference to the button's stage         
     stage = (Stage) backButton.getScene().getWindow();
     //load up OTHER FXML document
     root = FXMLLoader.load(getClass().getResource("StudentDashboard_1.fxml"));
     //create a new scene with root and set the stage
     Scene scene = new Scene(root);
     stage.setScene(scene);
     stage.show();
     }
     */

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
//        welcomeLabel.setText("Welcome " + name);
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
            imageUrl = "file:src\\AvatarProfile.png";
            proImage = new Image(imageUrl);
        }
        profileImageView.setImage(proImage);
        String address = profile.getStreet() + "\n"
                + profile.getCity() + ", " + profile.getState() + " " + profile.getPostcode();

        emailLabel.setText("Email: " + profile.getEmail());
        addressLabel.setText("Address: " + address);
        degreeLabel.setText("Degree: " + profile.getDegreeName());
        majorLabel.setText("Major: " + profile.getMajor());
        nameLabel.setText(profile.getFirstName() + " " + profile.getLastName());
        yearOfStudyLabel.setText(profile.getYearOfStudy());
        degreesLabel.setText("Degree: " + profile.getDegreeName());
        sideNameLabel.setText(profile.getFirstName() + " " + profile.getLastName());
        studentIdLabel.setText("Student ID: " + profile.getStudentId());
    }

    @FXML
    private void imageUpload(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            //Desktop.getDesktop().open(file);
            FileInputStream stream = new FileInputStream(file);
            accountLogic.uploadImage(stream);
        }
        stage = (Stage) backButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("StudentMyProfile.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
