/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unswexchange;

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
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Applic;
import model.Profile;

/**
 * FXML Controller class
 *
 * @author ismmedina
 */
public class OfficerApplicationDecisionController implements Initializable {

    private ApplicationLogic appLogic;
    private Applic application;

    private Profile profile;
    @FXML
    private Button submitDecisionButton;

    public OfficerApplicationDecisionController() {
        appLogic = new ApplicationLogic();
    }

    Stage stage = null;
    Stage decisionStage;
    Parent root = null;

    @FXML
    private Label welcomeLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label uniLabel;
    @FXML
    private Label startDateLabel;
    @FXML
    private Label endDateLabel;
    @FXML
    private Button cancelButton;
    
     @FXML
    private void logsButtonPressed(ActionEvent event) throws IOException {
        stage = new Stage();
        stage.setTitle("Application Log");
        stage.getIcons().add(new Image("Images/icon.png"));
        root = FXMLLoader.load(getClass().getResource("OfficerLog.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void approveButtonPressed(ActionEvent event) throws IOException {
        stage = new Stage();
        stage.setTitle("Review Application");
        stage.getIcons().add(new Image("Images/icon.png"));
        root = FXMLLoader.load(getClass().getResource("OfficerApplicationDecisionAccept.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void declineButtonPressed(ActionEvent event) throws IOException {
        stage = new Stage();
        stage.setTitle("Review Application");
        stage.getIcons().add(new Image("Images/icon.png"));
        root = FXMLLoader.load(getClass().getResource("OfficerApplicationDecisionDecline.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void logoutPressed(ActionEvent event) throws IOException {
        decisionStage = (Stage) welcomeLabel.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Scene scene = new Scene(root);
        decisionStage.setScene(scene);
        decisionStage.show();
    }

    @FXML
    private void backButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) welcomeLabel.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("OfficerDashboard.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void generalHyperPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) welcomeLabel.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("OfficerReviewApp.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void coursesHyperPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) welcomeLabel.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("OfficerReviewCoursesMatch.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void attachmentsHyperPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) welcomeLabel.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("OfficerReviewAttachments.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void questionsHyperPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) welcomeLabel.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("OfficerReviewQuest.fxml"));
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
            profile = appLogic.findProfileByAppId(applicationId);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OfficerReviewAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
        nameLabel.setText(profile.getFirstName() + " " + profile.getLastName());
        uniLabel.setText(profile.getUniName());
        startDateLabel.setText(String.valueOf(profile.getStartDate()));
        endDateLabel.setText(String.valueOf(profile.getFinishDate()));

    }

}
