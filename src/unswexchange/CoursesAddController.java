/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unswexchange;

import static dataAccess.AccountLogic.name;
import static dataAccess.ApplicationLogic.applicationId;
import dataAccess.CourseLogic;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Course;

/**
 * FXML Controller class
 *
 * @author ismmedina
 */
public class CoursesAddController implements Initializable {

    private Course course;
    private CourseLogic coursLogic;
    private FileInputStream courseOutline;
    @FXML
    private Button backButton1;
    @FXML
    private Button addCourseButton1;

    public CoursesAddController() {
        coursLogic = new CourseLogic();
    }

    Stage stage;
    Parent root;

    private Button backButton;
    @FXML
    private Button uploadCourseOutlineButton;
    @FXML
    private Label welcomeLabel;
    @FXML
    private Label filenameLabel;
    @FXML
    private TextField foreignCodeText;
    @FXML
    private TextField foreignNameText;
    @FXML
    private TextField unswCodeText;
    @FXML
    private TextField unswNameText;

    @FXML
    private void addButtonPressed(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {

        String foreignCode = foreignCodeText.getText();
        String foreignName = foreignNameText.getText();
        String unswCode = unswCodeText.getText();
        String unswName = unswNameText.getText();

        course = new Course(foreignCode, foreignName, unswCode, unswName, courseOutline, applicationId);
        int feedback = coursLogic.insertCourse(course);
        if (feedback > 0) {
            stage = (Stage) welcomeLabel.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("Courses.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            stage = (Stage) welcomeLabel.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("CoursesAdd.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private void backButtonPressed(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        stage = (Stage) backButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("Courses.fxml"));
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        welcomeLabel.setText("Welcome " + name);
        FileChooser fileChooser = new FileChooser();
        uploadCourseOutlineButton.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(final ActionEvent e) {
                File file = fileChooser.showOpenDialog(stage);
                String fileName = file.getName();
                filenameLabel.setText(fileName);
                if (file != null) {
                    try {
                        //Desktop.getDesktop().open(file);
                        courseOutline = new FileInputStream(file);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(CoursesAddController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
}
