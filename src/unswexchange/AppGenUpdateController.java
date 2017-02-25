/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unswexchange;

import static dataAccess.AccountLogic.name;
import dataAccess.ApplicationLogic;
import static dataAccess.ApplicationLogic.applicationId;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Applic;
import model.Profile;

/**
 * FXML Controller class
 *
 * @author ismmedina
 */
public class AppGenUpdateController implements Initializable {

    private ApplicationLogic appLogic;
    private Applic application;

    private FileInputStream transcript;

    public AppGenUpdateController() {
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
    private Button updateButton;
    @FXML
    private Button backButton;
    @FXML
    private CheckBox unswPartnerCheck;
    @FXML
    private CheckBox transferCreditCheck;
    @FXML
    private Label transcriptNameLabel;
    @FXML
    private Label welcomeLabel;
    @FXML
    private Button uploadTranscriptButton;

    @FXML
    private void updateButtonPressed(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        //TODO: INSERT form details into application table where foreign key = currentUser
        int feedback = 0;
        String uniName = universityName.getText();
        String uniCountry = countryCombo.getValue().toString();
        Date startDate = convertToSqlDate(startDatePicker.getValue());
        Date finishDate = convertToSqlDate(endDatePicker.getValue());
        boolean unswPartner = unswPartnerCheck.isSelected();
        boolean transferCredit = transferCreditCheck.isSelected();

        application = new Applic(uniName, uniCountry, startDate, finishDate, unswPartner, transferCredit, transcript);
        feedback = appLogic.updateApplicationGen(application);
        if (feedback != 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Application has been updated");
            alert.show();
            stage = (Stage) updateButton.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("AppGenUpdate.fxml"));
        }
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
     @FXML
    private void backButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) welcomeLabel.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("StudentMyApplications.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
      @FXML
    private void coursesHyperPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) welcomeLabel.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("AppCoursesEdit.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
     @FXML
    private void attachmentsHyperPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) welcomeLabel.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("AppAttachmentsEdit.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
     @FXML
    private void questionsHyperPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) welcomeLabel.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("AppQuestionsEdit.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
     @FXML
    private void commentsHyperPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) welcomeLabel.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("AppCommentReview.fxml"));
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            welcomeLabel.setText("Welcome " + name);
            application = appLogic.findApplicationByAppId();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AppGenUpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }
        universityName.setText(application.getUniName());
        countryCombo.setValue(application.getUniCountry());
        startDatePicker.setValue(application.getStartDate().toLocalDate());
        endDatePicker.setValue(application.getFinishDate().toLocalDate());
        if (application.isCreditTransferToUnsw()) {
            transferCreditCheck.setSelected(true);
        }
        if (application.isUnswPartner()) {
            unswPartnerCheck.setSelected(true);
        }
        
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
    }

}
