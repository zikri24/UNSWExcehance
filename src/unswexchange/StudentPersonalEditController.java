/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unswexchange;

import dataAccess.AccountLogic;
import static dataAccess.AccountLogic.name;
import dataAccess.ProfileLogic;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import model.Account;
import model.Profile;

/**
 * FXML Controller class
 *
 * @author ismmedina
 */
public class StudentPersonalEditController implements Initializable {

    //declair private filed 
    private ProfileLogic profileLogic;
    private AccountLogic accountLigic;

    public StudentPersonalEditController() {
        profileLogic = new ProfileLogic();
        accountLigic = new AccountLogic();
    }

    private Profile profile;
    private Account account;

    Stage stage;
    Parent root;
    @FXML
    private Label welcomeLabel;
    @FXML
    private TextField familyName;
    @FXML
    private TextField givenNameText;
    @FXML
    private TextField studentID;
    @FXML
    private RadioButton maleRadio;
    @FXML
    private RadioButton femaleRadio;

    @FXML
    private TextField streetAddress;
    @FXML
    private TextField suburb;
    @FXML
    private ComboBox stateCombo;
    @FXML
    private TextField postCode;
    @FXML
    private DatePicker dobPick;
    @FXML
    private TextField contactNo;
    @FXML
    private Label degreeLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label yearOfStudyLabel;

    @FXML
    private Button nextButton;
    @FXML
    private ImageView profileImageView;
   

    /**
     * Initializes the controller class.
     */
    @FXML
    private void updateButtonPressed(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        //TODO: Insert form details into student table where primary key = studentID and store studentID as currentUser
        //to use in other forms
        final ToggleGroup group = new ToggleGroup();
        maleRadio.isSelected();
        maleRadio.setUserData("male");
        maleRadio.setToggleGroup(group);
        femaleRadio.setUserData("female");
        femaleRadio.setToggleGroup(group);

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {

            }
        });
        String sex = group.getSelectedToggle().getUserData().toString();
        //collectiong the values from the UI and store them in local variables
        String firstName = givenNameText.getText();
        String lastName = familyName.getText();
        String studentId = studentID.getText();
        String street = streetAddress.getText();
        String city = suburb.getText();
        String state = stateCombo.getValue().toString();
        String postcode = postCode.getText();
        java.sql.Date dob = convertToSqlDate(dobPick.getValue());
        String phone = contactNo.getText();
        //Creating an instance of Account
        account = new Account(firstName, lastName, studentId, sex, street,
                city, state, postcode, dob, phone);

        //Creating and instance of AccountLogic and calling InsertAccount method       
        accountLigic.updateAccount(account);
        //get reference to the button's stage         
        stage = (Stage) nextButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("StudentMyProfile.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        //TODO: Form Validation on submit
    }

    @FXML
    private void backButtonPressed(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        stage = (Stage) nextButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("StudentMyProfile.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void logoutPressed(ActionEvent event) throws IOException {
        stage = (Stage) nextButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /*
     * method for 
     * 
     */

    private java.sql.Date convertToSqlDate(LocalDate localDate) {
        java.sql.Date date = new Date(
                localDate.getYear() - 1900,
                localDate.getMonthValue() - 1,
                localDate.getDayOfMonth()
        );
        return date;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //welcomeLabel.setText("Welcom" + " " + name);
        BufferedImage imageBytes = null;
        String imageUrl = "file:src\\maleprofilecircle2.jpg";
        Image proImage = new Image(imageUrl);
        try {
            profile = profileLogic.finProfileById();
            imageBytes = accountLigic.findProfileImage();
            File file = new File("src\\new-photo.jpg");
            ImageIO.write(imageBytes, "jpg", file);
            imageUrl = "file:src\\new-photo.jpg";
            proImage = new Image(imageUrl);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StudentMyProfileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StudentPersonalEditController.class.getName()).log(Level.SEVERE, null, ex);
        }

        profileImageView.setImage(proImage);
        degreeLabel.setText("Degree: " + profile.getDegreeName());
        nameLabel.setText(profile.getFirstName() + " " + profile.getLastName());
        yearOfStudyLabel.setText(profile.getYearOfStudy());
        familyName.setText(profile.getLastName());
        givenNameText.setText(profile.getFirstName());
        studentID.setText(profile.getStudentId());
        streetAddress.setText(profile.getStreet());
        suburb.setText(profile.getCity());
        stateCombo.setValue(profile.getState());
        postCode.setText(profile.getPostcode());
        contactNo.setText(profile.getPhone());
        String gender = profile.getSex();
        if (gender.equals("male")) {
            maleRadio.setSelected(true);
        } else {
            femaleRadio.setSelected(true);
        }
        dobPick.setValue(profile.getDob().toLocalDate());

        ObservableList<String> states = FXCollections.observableArrayList("NSW", "NT", "SA", "WA", "TAS", "VIC", "QLD", "ACT"
        );
        //set comboBox components
        stateCombo.setItems(states);
    }
}
