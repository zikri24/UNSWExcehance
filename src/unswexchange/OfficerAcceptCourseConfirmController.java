/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unswexchange;

import dataAccess.CourseLogic;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Course;

/**
 * FXML Controller class
 *
 * @author ismmedina
 */
public class OfficerAcceptCourseConfirmController implements Initializable {

    private CourseLogic courseLogic;
    Course course = null;
    private FileInputStream courseOutline;

    public OfficerAcceptCourseConfirmController() {
        courseLogic = new CourseLogic();
    }

    Stage stage;
    Parent root;

    @FXML
    private Button cancelButton;
    @FXML
    private Button submitButton;

    @FXML
    private Label foreignCodeLabel;
    @FXML
    private Label unswCodeLabel;

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
            stage = new Stage();
            try {
                //load up OTHER FXML document
                root = FXMLLoader.load(getClass().getResource("OfficerCourseDecision.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(OfficerAllAppsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        });
    }

}
