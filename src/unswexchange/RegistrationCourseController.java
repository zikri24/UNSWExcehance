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
import static dataAccess.AccountLogic.currentId;
import static dataAccess.AccountLogic.name;
import dataAccess.EducationLogic;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Education;

/**
 * FXML Controller class
 *
 * @author CocoMango
 */
public class RegistrationCourseController implements Initializable {

    private EducationLogic eduLogic;
    private Education education;

    public RegistrationCourseController() {
        eduLogic = new EducationLogic();
    }

    Stage stage;
    Parent root;
//declare all needed GUI Components
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
    private Button nextButton;
    @FXML
    private Button backButton;
    @FXML
    private Label welcomeLabel;
    @FXML
    private Label degreeLabel;
    @FXML
    private Label codeLabel;
    @FXML
    private Label schoolLabel;
    @FXML
    private Label yosLabel;
    @FXML
    private Label completedLabel;
    @FXML
    private Label wamLabel;
    @FXML
    private Label majorLabel;
    @FXML
    private Label checkForm;

    /*
     * method for 
     * 
     */
    @FXML
    private void registerNextButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        //TODO: UPDATE form details into student table where primary key = currentUser
        String degreeName = degreeNameText.getText();
        String programCode = programCodeText.getText();
        String faculty = facultyCombo.getValue().toString();
        String yearOfStudy = yearOfStudyCombo.getValue().toString();
        int yearToComplete = Integer.parseInt(degreeCompletedText.getText());
        double wam = Double.parseDouble(currentWam.getText());
        String major = currentMajor.getText();
        // AccountLogic.school= faculty;

        education = new Education(currentId, degreeName, programCode, faculty, yearOfStudy, yearToComplete, wam, major);
        eduLogic = new EducationLogic();
        
         if (degreeName.isEmpty()){
          checkForm.setVisible(true);
          checkForm.setStyle("-fx-text-fill: Red;");
          checkForm.setText("Please check your form");
          degreeLabel.setVisible(true);
          degreeLabel.setStyle("-fx-text-fill: Red;");
          degreeLabel.setText("Please enter your degree name");
        } else if (programCode.isEmpty()){
            checkForm.setVisible(true);
          checkForm.setStyle("-fx-text-fill: Red;");
          checkForm.setText("Please check your form");
          codeLabel.setVisible(true);
          codeLabel.setStyle("-fx-text-fill: Red;");
          codeLabel.setText("Please enter your program code");
        } else if (faculty.isEmpty()){
            checkForm.setVisible(true);
          checkForm.setStyle("-fx-text-fill: Red;");
          checkForm.setText("Please check your form");
          schoolLabel.setVisible(true);
          schoolLabel.setStyle("-fx-text-fill: Red;");
          schoolLabel.setText("Please enter your school");
        } else if (yearOfStudy.isEmpty()){
            checkForm.setVisible(true);
          checkForm.setStyle("-fx-text-fill: Red;");
          checkForm.setText("Please check your form");
          yosLabel.setVisible(true);
          yosLabel.setStyle("-fx-text-fill: Red;");
          yosLabel.setText("Please enter your year of study");
        } else if (degreeCompletedText.getText().isEmpty()){
             checkForm.setVisible(true);
          checkForm.setStyle("-fx-text-fill: Red;");
          checkForm.setText("Please check your form");
          completedLabel.setVisible(true);
          completedLabel.setStyle("-fx-text-fill: Red;");
          completedLabel.setText("Please enter the year you will complete your degree");
        } else if (currentWam.getText().isEmpty()){
            checkForm.setVisible(true);
          checkForm.setStyle("-fx-text-fill: Red;");
          checkForm.setText("Please check your form");
          wamLabel.setVisible(true);
          wamLabel.setStyle("-fx-text-fill: Red;");
          wamLabel.setText("Please enter your current wam");
        } else if (major.isEmpty()){
             checkForm.setVisible(true);
          checkForm.setStyle("-fx-text-fill: Red;");
          checkForm.setText("Please check your form");
          majorLabel.setVisible(true);
          majorLabel.setStyle("-fx-text-fill: Red;");
          majorLabel.setText("Please enter your major");
        } else if (programCode.matches("[a-zA-Z]+")){
          checkForm.setStyle("-fx-text-fill: Red;");
          checkForm.setText("Please check your form");
          codeLabel.setVisible(true);
          codeLabel.setStyle("-fx-text-fill: Red;");
          codeLabel.setText("Program code only accepts numbers");
        } else if (programCode.length() != 4){
            checkForm.setStyle("-fx-text-fill: Red;");
          checkForm.setText("Please check your form");
          codeLabel.setVisible(true);
          codeLabel.setStyle("-fx-text-fill: Red;");
          codeLabel.setText("Program code must be 4 digits");
        }else if (degreeCompletedText.getText().length() !=4){
            checkForm.setStyle("-fx-text-fill: Red;");
          checkForm.setText("Please check your form");
          completedLabel.setVisible(true);
          completedLabel.setStyle("-fx-text-fill: Red;");
          completedLabel.setText("Year of Completion must be 4 digits");
        }
         else {
        int feedback = eduLogic.insertEducation(education);
        if (feedback > 0) {
            //scene switch to RegisterFormCompleteDet.FXML
            //get reference to the button's stage         
            stage = (Stage) nextButton.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("RegistrationComplete.fxml"));
            //create a new scene with root and set the stage
        }
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            //TODO: Form Validation on submit
        }
    }

    @FXML
    private void registerBackButtonPressed(ActionEvent event) throws IOException {
        //TODO: code to reload RegisterFormPersonalDet.FXML and set all fields where user = currentUser
        stage = (Stage) nextButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("RegistrationPersonal.fxml"));
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       /* education = new Education();
        try {
            education = eduLogic.findEducationById();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RegistrationCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (education != null) {
            degreeNameText.setText(education.getDegreeName());
            programCodeText.setText(education.getProgramCode());
            facultyCombo.setValue(education.getFaculty());
            yearOfStudyCombo.setValue(education.getYearOfStudy());
            degreeCompletedText.setText(String.valueOf(education.getYearToComplete()));
            currentWam.setText(String.valueOf(education.getWam()));
            currentMajor.setText(education.getMajor());
        }
*/
        
degreeNameText.focusedProperty().addListener(new ChangeListener<Boolean>()
{
    @Override
    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
    {
        if (newPropertyValue)
        {
            degreeLabel.setVisible(false);
        }
        else
        {
            if(degreeNameText.getText().isEmpty()){
                degreeLabel.setVisible(true);
                degreeLabel.setStyle("-fx-text-fill: Red;");
                 degreeLabel.setText("Please enter your degree name");
            }      
        }
        }
});

degreeNameText.focusedProperty().addListener(new ChangeListener<Boolean>()
{
    @Override
    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
    {
        if (newPropertyValue)
        {
            codeLabel.setVisible(false);
        }
        else
        {
            if(programCodeText.getText().isEmpty()){
                codeLabel.setVisible(true);
                codeLabel.setStyle("-fx-text-fill: Red;");
                 codeLabel.setText("Please enter your degree name");
            }      
        }
        }
});

facultyCombo.focusedProperty().addListener(new ChangeListener<Boolean>()
{
    @Override
    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
    {
        if (newPropertyValue)
        {
            codeLabel.setVisible(false);
        }
        else
        {
            if(facultyCombo.getValue() == null){
                schoolLabel.setVisible(true);
                schoolLabel.setStyle("-fx-text-fill: Red;");
                 schoolLabel.setText("Please enter your school");
            }      
        }
        }
});


yearOfStudyCombo.focusedProperty().addListener(new ChangeListener<Boolean>()
{
    @Override
    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
    {
        if (newPropertyValue)
        {
            yosLabel.setVisible(false);
        }
        else
        {
            if(yearOfStudyCombo.getValue() == null){
                yosLabel.setVisible(true);
                yosLabel.setStyle("-fx-text-fill: Red;");
                 yosLabel.setText("Please select your year of study");
            }      
        }
        }
});

degreeCompletedText.focusedProperty().addListener(new ChangeListener<Boolean>()
{
    @Override
    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
    {
        if (newPropertyValue)
        {
            completedLabel.setVisible(false);
        }
        else
        {
            if(degreeCompletedText.getText().isEmpty()){
                completedLabel.setVisible(true);
                completedLabel.setStyle("-fx-text-fill: Red;");
                 completedLabel.setText("Please enter the year you will finish your degree");
            }      
        }
        }
});

currentWam.focusedProperty().addListener(new ChangeListener<Boolean>()
{
    @Override
    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
    {
        if (newPropertyValue)
        {
            wamLabel.setVisible(false);
        }
        else
        {
            if(currentWam.getText().isEmpty()){
                wamLabel.setVisible(true);
                wamLabel.setStyle("-fx-text-fill: Red;");
                 wamLabel.setText("Please enter your current wam");
            }      
        }
           }
});

currentMajor.focusedProperty().addListener(new ChangeListener<Boolean>()
{
    @Override
    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
    {
        if (newPropertyValue)
        {
            majorLabel.setVisible(false);
        }
        else
        {
            if(currentMajor.getText().isEmpty()){
                majorLabel.setVisible(true);
                majorLabel.setStyle("-fx-text-fill: Red;");
                 majorLabel.setText("Please enter your major");
            }      
        }
           }
});

programCodeText.focusedProperty().addListener(new ChangeListener<Boolean>()
{
    @Override
    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
    {
        if (newPropertyValue)
        {
            codeLabel.setVisible(false);
        }
        else
        {
            if(programCodeText.getText().length() != 4){
                codeLabel.setVisible(true);
                codeLabel.setStyle("-fx-text-fill: Red;");
                 codeLabel.setText("program code must only be 4 numbers");
            }
        }
        }
});


degreeCompletedText.focusedProperty().addListener(new ChangeListener<Boolean>()
{
    @Override
    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
    {
        if (newPropertyValue)
        {
            completedLabel.setVisible(false);
        }
        else
        {
            if(degreeCompletedText.getText().length() != 4){
                completedLabel.setVisible(true);
                completedLabel.setStyle("-fx-text-fill: Red;");
                 completedLabel.setText("Degree completed only accepts 4 digits");
            }
        }
        }
});

degreeCompletedText.focusedProperty().addListener(new ChangeListener<Boolean>()
{
    @Override
    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
    {
        if (newPropertyValue)
        {
            completedLabel.setVisible(false);
        }
        else
        {
            if(degreeCompletedText.getText().matches("[a-zA-Z]+")){
                completedLabel.setVisible(true);
                completedLabel.setStyle("-fx-text-fill: Red;");
                 completedLabel.setText("degree completed can only accept numbers");
            }   
        }
        }
});

programCodeText.focusedProperty().addListener(new ChangeListener<Boolean>()
{
    @Override
    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
    {
        if (newPropertyValue)
        {
            codeLabel.setVisible(false);
        }
        else
        {
            if(programCodeText.getText().matches("[a-zA-Z]+")){
                codeLabel.setVisible(true);
                codeLabel.setStyle("-fx-text-fill: Red;");
                 completedLabel.setText("program code can only accept numbers");
            }   
        }
        }
});
        ObservableList<String> Faculty = FXCollections.observableArrayList("Management", "Economics", "Information Systems", "Finance", "Actuarials", "Accounting", "Marketing"
        );
        ObservableList<String> yearOfStudy = FXCollections.observableArrayList("1st Year", "2nd Year", "3rd Year", "4th Year", "5th Year", "6th Year"
        );
        //set comboBox components
        yearOfStudyCombo.setItems(yearOfStudy);
        facultyCombo.setItems(Faculty);
    }
}
