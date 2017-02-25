/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unswexchange;

import dataAccess.CourseLogic;
import static dataAccess.CourseLogic.courseId;
import java.io.FileInputStream;
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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.Course;
import static unswexchange.OfficerReviewCoursesMatchController.secondarystage;

/**
 * FXML Controller class
 *
 * @author ismmedina
 */
public class OfficerCourseDecisionController implements Initializable {

    private CourseLogic courseLogic;
    Course course = null;
    private FileInputStream courseOutline;

    public OfficerCourseDecisionController() {
        courseLogic = new CourseLogic();
    }

    Stage stage;
    Parent root;
    //declare all needed GUI Components

    @FXML
    private Button cancelButton;
    @FXML
    private Button acceptButton;
    @FXML
    private Button acceptConditionButton;
     @FXML
    private Button acceptConditionSubmitButton;
    @FXML
    private Button declineButton;
    @FXML
    private Label foreignCodeLabel;
    @FXML
    private Label unswCodeLabel;
    @FXML
    private TextArea conditionText;

    @FXML
    private void acceptButtonPressed(ActionEvent event) throws IOException {
        stage = (Stage) unswCodeLabel.getScene().getWindow();
        stage.close();
        stage = new Stage();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("OfficerAcceptCourseConfirm.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void acceptConditionButtonPressed(ActionEvent event) throws IOException {
        stage = (Stage) unswCodeLabel.getScene().getWindow();
        stage.close();
        stage = new Stage();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("OfficerCourseAcceptCondition.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void submitAcceptConditionButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        int feedback;
        String condition = conditionText.getText();
        feedback = courseLogic.acceptCourseOnCondition(courseId, condition);
        if (feedback > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Course has been Accepted");
            alert.show();
            stage = (Stage) unswCodeLabel.getScene().getWindow();
            stage.close();
        }
        /*
         stage = secondarystage;
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("OfficerReviewCoursesMatch.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        */
    }

    @FXML
    private void submitButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        int feedback;
        feedback = courseLogic.acceptCourse(courseId);
        if (feedback > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText(" Course Has been Accepted");
            alert.show();
            stage = (Stage) unswCodeLabel.getScene().getWindow();
            stage.close();
        }       
    }
    @FXML
    private void declineButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        int feedback;
        feedback = courseLogic.declineCourse(courseId);
        if (feedback > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText(" Course Has been Declined");
            alert.show();
            stage = (Stage) unswCodeLabel.getScene().getWindow();
            stage.close();
        }       
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            course = courseLogic.findCoursesById(CourseLogic.courseId);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CourseEditController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String foreignCode = course.getForeignCode();
        String unswCode = course.getUnswCode();
        foreignCodeLabel.setText(foreignCode);
        unswCodeLabel.setText(unswCode);

        cancelButton.setOnAction((final ActionEvent e) -> {
            stage = (Stage) foreignCodeLabel.getScene().getWindow();
            stage.close();
        });
    }

}
