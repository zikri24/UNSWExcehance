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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Applic;
import model.ApplicationFile;
import model.ApplicationStatus;
import model.Course;

/**
 * FXML Controller class
 *
 * @author ismmedina
 */
public class AppCommentReviewController implements Initializable {

    private ApplicationLogic applicationLogic;

    public AppCommentReviewController() {
        applicationLogic = new ApplicationLogic();
    }

    Stage stage;
    Parent root;
    //declare all needed GUI Components

    @FXML
    private TableView<Course> coursesTable;
    @FXML
    private TableColumn<Course, String> courseCodeCell;
    @FXML
    private TableColumn<Course, String> statusCell;
    @FXML
    private TableColumn<Course, String> commentsCell;

    @FXML
    private Button backButton;

    @FXML
    private Label welcomeLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private Label commentsLabel;

    @FXML
    private void backButtonPressed(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        stage = (Stage) welcomeLabel.getScene().getWindow();
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
        stage = (Stage) welcomeLabel.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("AppGenUpdate.fxml"));
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
    private void questionsHyperPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) welcomeLabel.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("AppQuestionsEdit.fxml"));
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
        ApplicationStatus appStatus = null;
        String status = "";
        String comments = "";
        try {
            appStatus = applicationLogic.findApplicationStatus();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AppCommentReviewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        status = appStatus.getStatus();
        comments = appStatus.getComments();
        statusLabel.setText(status);
        commentsLabel.setText(comments);

        ObservableList<Course> courses = null;
        try {
            courses = FXCollections.observableArrayList(applicationLogic.findCoursesAppIdForStudent());
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StudentMyApplicationsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        courseCodeCell.setCellValueFactory(new PropertyValueFactory<>("foreignCode"));
        statusCell.setCellValueFactory(new PropertyValueFactory<>("status"));
        commentsCell.setCellValueFactory(new PropertyValueFactory<>("conditions"));
        coursesTable.setItems(courses);
    }

}
