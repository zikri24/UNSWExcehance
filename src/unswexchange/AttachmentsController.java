/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unswexchange;

import static dataAccess.AccountLogic.name;
import dataAccess.ApplicationLogic;
import dataAccess.FileLogic;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Applic;
import model.ApplicationFile;

/**
 * FXML Controller class
 *
 * @author ismmedina
 */
public class AttachmentsController implements Initializable {

    private FileLogic fileLogic;
    private ApplicationFile applicationfile;
    private FileInputStream passport;
    private FileInputStream resume;
    private FileInputStream credit;

    public AttachmentsController() {
        fileLogic = new FileLogic();
    }

    Stage stage;
    Parent root;
    //declare all needed GUI Components

    @FXML
    private Button backButton;
    @FXML
    private Button nextButton;
    @FXML
    private Label welcomeLabel;
    @FXML
    private Label passportLabel;
    @FXML
    private Label resumeLabel;
    @FXML
    private Label creditLabel;
    @FXML
    private Button passportButton;
    @FXML
    private Button resumeButton;
    @FXML
    private Button creditButton;
    @FXML
    private Label checkForm;

    @FXML
    private void nextButtonAction(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        int feedback = 0;

        ArrayList<ApplicationFile> files = new ArrayList<>();
        ApplicationFile passportPdf = new ApplicationFile(passport, "passport");
        files.add(passportPdf);
        ApplicationFile resumePdf = new ApplicationFile(resume, "resume");
        files.add(resumePdf);
        ApplicationFile creditPdf = new ApplicationFile(credit, "credit");
        files.add(creditPdf);
        
        if (files.isEmpty() || files.size() <2) {
     
             checkForm.setStyle("-fx-text-fill: Red;");
            checkForm.setText("Please ensure you have uploaded all files");

} else {
        fileLogic.insertFiles(files);
        
    }
        stage = (Stage) nextButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("ApplyDeclairation.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        /*
         feedback = appLogic.insertAppQuestions(application);
         if (feedback > 0) {
         stage = (Stage) backButton.getScene().getWindow();
         //load up OTHER FXML document
         root = FXMLLoader.load(getClass().getResource("ApplyDeclairation.fxml"));
         Scene scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
         } else {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Information Dialog");
         alert.setHeaderText(null);
         alert.setContentText("Something went wrong!");
         alert.show();
         stage = (Stage) backButton.getScene().getWindow();
         //load up OTHER FXML document
         root = FXMLLoader.load(getClass().getResource("Attachments.fxml"));
         Scene scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
         }*/
    }

    @FXML
    private void backButtonPressed(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        stage = (Stage) backButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("ApplicationQuestions.fxml"));
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


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        welcomeLabel.setText("Welcome " + name);

        FileChooser fileChooser = new FileChooser();
        passportButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(final ActionEvent e) {
                java.io.File file = fileChooser.showOpenDialog(stage);
                String fileName;                 
                if (file != null) {
                    try {
                        fileName =file.getName();
                        passportLabel.setText(fileName);
                        passport = new FileInputStream(file);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(CoursesAddController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        );

        resumeButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(final ActionEvent e) {
                java.io.File file = fileChooser.showOpenDialog(stage);
                String fileName;
                
                if (file != null) {
                    try {
                        fileName= file.getName();
                        resumeLabel.setText(fileName);
                        resume = new FileInputStream(file);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(CoursesAddController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        );

        creditButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(final ActionEvent e) {
                java.io.File file = fileChooser.showOpenDialog(stage);
                String fileName; 
               
                if (file != null) {
                    try {
                        fileName = file.getName();
                         creditLabel.setText(fileName);
                        credit = new FileInputStream(file);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(CoursesAddController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        );

    }

}
