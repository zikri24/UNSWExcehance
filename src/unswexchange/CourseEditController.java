/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unswexchange;

import static dataAccess.AccountLogic.name;
import dataAccess.CourseLogic;
import static dataAccess.CourseLogic.courseId;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Course;


/**
 * FXML Controller class
 *
 * @author ismmedina
 */
public class CourseEditController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private CourseLogic courseLogic;
    Course course = null;
    private FileInputStream courseOutline;

    public CourseEditController() {
        courseLogic = new CourseLogic();
    }

    Stage stage;
    Parent root;
    //declare all needed GUI Components

    @FXML
    private Button backButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button uploadCourseOutlineButton;
    @FXML
    private TextField foreignCodeText;
    @FXML
    private TextField foreignNameText;
    @FXML
    private TextField unswCodeText;
    @FXML
    private TextField unswNameText;

    @FXML
    private Label welcomeLabel;
    @FXML
    private Label filenameLabel;

    @FXML
    private void updateButtonPressed(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        int feedback = 0;
        String foreignCode = foreignCodeText.getText();
        String foreignName = foreignCodeText.getText();
        String unswCode = foreignCodeText.getText();
        String unswName = foreignCodeText.getText();
        FileInputStream coursOutLine = courseOutline;
        course = new Course(courseId, foreignCode, foreignName, unswCode, unswName);
        feedback = courseLogic.updateCourse(course);
        if (feedback > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Course has been updated");
            alert.show();
            stage = (Stage) backButton.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("Courses.fxml"));
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
         //welcomeLabel.setText(name);
        try {
            course = courseLogic.findCoursesById(CoursesController.courseId);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CourseEditController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String foreignCode = course.getForeignCode();
        String foreignName = course.getForeignName();
        String unswCode = course.getUnswCode();
        String unswName = course.getUnswName();
        foreignCodeText.setText(foreignCode);
        foreignNameText.setText(foreignName);
        unswCodeText.setText(unswCode);
        unswNameText.setText(unswName);
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
