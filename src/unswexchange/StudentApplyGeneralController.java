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
import dataAccess.LoginLogic;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Applic;

/**
 * FXML Controller class
 *
 * @author CocoMango
 */
public class StudentApplyGeneralController implements Initializable {
//declare all needed GUI Components

    private final ApplicationLogic appLogic;
    private Applic application;
    private int applicationId;
    private FileInputStream transcript;

    public StudentApplyGeneralController() {
        appLogic = new ApplicationLogic();
    }

    Stage stage = null;
    Parent root = null;
    @FXML
    private TextField universityName;
    @FXML
    private ComboBox countryCombo;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private Label uniLabel;
    @FXML
    private Label countryLabel;
    @FXML
    private Label startLabel;
    @FXML
    private Label endLabel;
    @FXML
    private Label creditLabel;
    @FXML
    private Label partnerLabel;
    @FXML
    private Label checkForm;

    @FXML
    private Button nextButton;
    @FXML
    private Button backButton;
    @FXML
    private CheckBox unswPartnerCheck;
    @FXML
    private CheckBox transferCreditCheck;
    @FXML
    private Label transcriptNameLabel;
    @FXML
    private Button uploadTranscriptButton;
    @FXML
    private Label welcomeLabel;
    /*
     * method for 
     * 
     */

    @FXML
    private void applicationNextButtonPressed(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        //TODO: INSERT form details into application table where foreign key = currentUser
        LocalDate startLocal = startDatePicker.getValue();
        java.sql.Date startDate;
        if (startLocal == null) {
            startDate = null;
        } else {
            startDate = convertToSqlDate(startLocal);
        }

        LocalDate endLocal = endDatePicker.getValue();
        java.sql.Date finishDate;
        if (endLocal == null) {
            finishDate = null;
        } else {
            finishDate = convertToSqlDate(endLocal);
        }

        int currentUser = AccountLogic.currentId;
        String uniName = universityName.getText();
        String uniCountry = countryCombo.getValue().toString();

        boolean unswPartner = unswPartnerCheck.isSelected();
        boolean transferCredit = transferCreditCheck.isSelected();

        application = new Applic(currentUser, uniName, uniCountry, startDate, finishDate, unswPartner, transferCredit, transcript);

        if (uniName.isEmpty()) {
            checkForm.setVisible(true);
            checkForm.setStyle("-fx-text-fill: Red;");
            checkForm.setText("Please check your form");
            uniLabel.setVisible(true);
            uniLabel.setStyle("-fx-text-fill: Red;");
            uniLabel.setText("Please enter the university name");
        } else if (uniCountry.isEmpty()) {
            checkForm.setVisible(true);
            checkForm.setStyle("-fx-text-fill: Red;");
            checkForm.setText("Please check your form");
            countryLabel.setVisible(true);
            countryLabel.setStyle("-fx-text-fill: Red;");
            countryLabel.setText("Please enter the country");
        } else if (transcript == null) {
            checkForm.setVisible(true);
            checkForm.setStyle("-fx-text-fill: Red;");
            checkForm.setText("Please check your form");
            transcriptNameLabel.setVisible(true);
            transcriptNameLabel.setStyle("-fx-text-fill: Red;");
            transcriptNameLabel.setText("Please uplaod your transcript");
        } else {

            applicationId = appLogic.insertApplication(application);

            stage = (Stage) nextButton.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("Courses.fxml"));

        }
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        //TODO: Form Validation on submit
    }

    @FXML
    private void backButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) backButton.getScene().getWindow();
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

    private java.sql.Date convertToSqlDate(LocalDate localDate) {
        java.sql.Date date = new Date(
                localDate.getYear() - 1900,
                localDate.getMonthValue() - 1,
                localDate.getDayOfMonth()
        );
        return date;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        welcomeLabel.setText("Welcome " + name);
        //declare comboBox dropdown components
        //TODO: update country list below
        ObservableList<String> Country = FXCollections.observableArrayList("Australia", "United States", "canada", "Austria", "Other"
        );
        //set comboBox components
        countryCombo.setItems(Country);
        //Upload Transcript Button
        FileChooser fileChooser = new FileChooser();
        uploadTranscriptButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(final ActionEvent e) {
                File file = fileChooser.showOpenDialog(stage);
                String fileName = file.getName();
                transcriptNameLabel.setText(fileName);
                if (file != null) {
                    try {
                        //Desktop.getDesktop().open(file);
                        transcript = new FileInputStream(file);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(CoursesAddController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        );

        universityName.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    uniLabel.setVisible(false);
                } else {
                    if (universityName.getText().isEmpty()) {
                        uniLabel.setVisible(true);
                        uniLabel.setStyle("-fx-text-fill: Red;");
                        uniLabel.setText("Please enter the university name");
                    }
                }
            }
        });

        countryCombo.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    countryLabel.setVisible(false);
                } else {
                    if (countryCombo.getValue() == null) {
                        countryLabel.setVisible(true);
                        countryLabel.setStyle("-fx-text-fill: Red;");
                        countryLabel.setText("Please enter the country");
                    }
                }
            }
        });

        /*    startDatePicker.focusedProperty().addListener(new ChangeListener<Boolean>() {
         @Override
         public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
         if (newPropertyValue) {
         startLabel.setVisible(false);
         } else {
         if (startDatePicker.getValue() == null) {
         startLabel.setVisible(true);
         startLabel.setStyle("-fx-text-fill: Red;");
         startLabel.setText("Please enter the start date");
         }
         }
         }
         });

         endDatePicker.focusedProperty().addListener(new ChangeListener<Boolean>() {
         @Override
         public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
         if (newPropertyValue) {
         endLabel.setVisible(false);
         } else {
         if (endDatePicker.getValue() == null) {
         endLabel.setVisible(true);
         endLabel.setStyle("-fx-text-fill: Red;");
         endLabel.setText("Please enter the end date");
         }
         }
         }
         });
         */
    }
}
