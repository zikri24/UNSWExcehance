/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unswexchange;

import dataAccess.AccountLogic;
import static dataAccess.AccountLogic.name;
import dataAccess.ApplicationLogic;
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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Applic;
import model.Profile;

/**
 * FXML Controller class
 *
 * @author ismmedina
 */
public class OfficerReviewQuestController implements Initializable {

    //declair private filed 
    private ApplicationLogic applicationLogic;

    public OfficerReviewQuestController() {
        applicationLogic = new ApplicationLogic();
    }

    private Applic application;

    Stage stage;
    Parent root;

    @FXML
    private Label welcomeLabel;
    @FXML
    private Label q1Label;
    @FXML
    private Label q2Label;
    @FXML
    private Label q3Label;

    @FXML
    private void generalHyperPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) welcomeLabel.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("OfficerReviewApp.fxml"));
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
    private void coursesHyperPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) welcomeLabel.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("OfficerReviewCoursesMatch.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void reviewHyperPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) welcomeLabel.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("OfficerApplicationDecision.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void backButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        //scene switch to RegisterFormCompleteDet.FXML
        //get reference to the button's stage         
        stage = (Stage) welcomeLabel.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("OfficerMyApps.fxml"));
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
            application = applicationLogic.findApplicationByAppId();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OfficerReviewQuestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        q1Label.setText(application.getSupportQues());
        q2Label.setText(application.getDemoQues());
        q3Label.setText(application.getBringBackQues());

    }

}
