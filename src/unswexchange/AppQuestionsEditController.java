/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unswexchange;

import static dataAccess.AccountLogic.name;
import dataAccess.ApplicationLogic;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Applic;
import model.Course;

/**
 *
 * @author ismmedina
 */
public class AppQuestionsEditController implements Initializable {

    private ApplicationLogic appLogic;
    private Applic application;

    public AppQuestionsEditController() {
        appLogic = new ApplicationLogic();
    }

    Stage stage;
    Parent root;
    //declare all needed GUI Components

    @FXML
    private Button backButton;
    @FXML
    private Button nextButton;
    @FXML
    private TextArea q1Text;
    @FXML
    private TextArea q2Text;
    @FXML
    private TextArea q3Text;

    @FXML
    private Label welcomeLabel;

    @FXML
    private void nextButtonAction(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        int feedback = 0;
        String q1 = q1Text.getText();
        String q2 = q2Text.getText();
        String q3 = q3Text.getText();
        application = new Applic(q1, q2, q3);
        feedback = appLogic.insertAppQuestions(application);
        if (feedback > 0) {
            stage = (Stage) backButton.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("AppQuestionsEdit.fxml"));
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
            root = FXMLLoader.load(getClass().getResource("ApplicationQuestions.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private void backButtonPressed(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        stage = (Stage) backButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("StudentMyApplications.fxml"));
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
    private void generalHyperPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) backButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("AppGenUpdate.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void coursesHyperPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) backButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("AppCoursesEdit.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void attachmentsHyperPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) backButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("AppAttachmentsEdit.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void commentsHyperPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) backButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("AppCommentReview.fxml"));
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
        welcomeLabel.setText("Welcom" + " " + name);
        try {
            application = appLogic.findApplicationByAppId();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OfficerReviewQuestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        q1Text.setText(application.getSupportQues());
        q2Text.setText(application.getDemoQues());
        q3Text.setText(application.getBringBackQues());

    }

}
