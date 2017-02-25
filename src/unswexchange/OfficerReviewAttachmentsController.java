/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unswexchange;

import dataAccess.AccountLogic;
import static dataAccess.AccountLogic.name;
import static dataAccess.ApplicationLogic.applicationId;
import dataAccess.FileLogic;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import javafx.stage.Stage;
import model.ApplicationFile;

/**
 * FXML Controller class
 *
 * @author ismmedina
 */
public class OfficerReviewAttachmentsController implements Initializable {

    private FileLogic fileLogic;
    private AccountLogic accountLogic;
    private InputStream inputStream = null;
    byte[] buffer = new byte[1024];
    FileOutputStream FileOutPutStream;
    File file;
    private String path;
    private Desktop desktop = Desktop.getDesktop();

    public OfficerReviewAttachmentsController() {
        fileLogic = new FileLogic();
        accountLogic = new AccountLogic();
    }

    Stage stage;
    Parent root;

    @FXML
    private Button passportButton;
    @FXML
    private Button resumeButton;
    @FXML
    private Button creditButton;
    @FXML
    private Button transcriptButton;
    @FXML
    private Label welcomeLabel;

    @FXML
    private void backButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        //scene switch to RegisterFormCompleteDet.FXML
        //get reference to the button's stage         
        stage = (Stage) passportButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("OfficerMyApps.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void logoutPressed(ActionEvent event) throws IOException {
        stage = (Stage) passportButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void generalHyperPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) passportButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("OfficerReviewApp.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void coursesHyperPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) passportButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("OfficerReviewCoursesMatch.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void questionsHyperPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) passportButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("OfficerReviewQuest.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void reviewHyperPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) passportButton.getScene().getWindow();
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
        passportButton.setOnAction((final ActionEvent e) -> {
            try {
                path = fileLogic.downloadFile("passport");
                if (path == null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("No Files attached!");
                    alert.show();
                    stage = (Stage) passportButton.getScene().getWindow();
                    //load up OTHER FXML document
                    root = FXMLLoader.load(getClass().getResource("OfficerReviewAttachments.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            } catch (ClassNotFoundException | SQLException | IOException ex) {
                Logger.getLogger(OfficerReviewAttachmentsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            file = new File(path);
            try {
                desktop.open(file);
            } catch (IOException ex) {
                Logger.getLogger(OfficerReviewAttachmentsController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        resumeButton.setOnAction((final ActionEvent e) -> {
            try {
                path = fileLogic.downloadFile("resume");
                if (path == null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("No Files attached!");
                    alert.show();
                    stage = (Stage) passportButton.getScene().getWindow();
                    //load up OTHER FXML document
                    root = FXMLLoader.load(getClass().getResource("OfficerReviewAttachments.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(OfficerReviewAttachmentsController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(OfficerReviewAttachmentsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            file = new File(path);
            try {
                desktop.open(file);
            } catch (IOException ex) {
                Logger.getLogger(OfficerReviewAttachmentsController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        creditButton.setOnAction((final ActionEvent e) -> {
            try {
                path = fileLogic.downloadFile("credit");
                if (path == null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("No Files attached!");
                    alert.show();
                    stage = (Stage) passportButton.getScene().getWindow();
                    //load up OTHER FXML document
                    root = FXMLLoader.load(getClass().getResource("OfficerReviewAttachments.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(OfficerReviewAttachmentsController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(OfficerReviewAttachmentsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            file = new File(path);
            try {
                desktop.open(file);
            } catch (IOException ex) {
                Logger.getLogger(OfficerReviewAttachmentsController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        transcriptButton.setOnAction((final ActionEvent e) -> {
            try {
                path = accountLogic.downloadTranscript();
                if (path == null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("No Files attached!");
                    alert.show();
                    stage = (Stage) passportButton.getScene().getWindow();
                    //load up OTHER FXML document
                    root = FXMLLoader.load(getClass().getResource("OfficerReviewAttachments.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(OfficerReviewAttachmentsController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(OfficerReviewAttachmentsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            file = new File(path);
            try {
                desktop.open(file);
            } catch (IOException ex) {
                Logger.getLogger(OfficerReviewAttachmentsController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

}
