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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
public class ApplicationQuestionsController implements Initializable {

    private ApplicationLogic appLogic;
    private Applic application;
    private int applicationId;

    public ApplicationQuestionsController() {
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
    private Label qOneLabel;
    @FXML
    private Label qTwoLabel;
    @FXML
    private Label qThreeLabel;
    @FXML
    private Label checkForm;

    @FXML
    private Label welcomeLabel;

    @FXML
    private void nextButtonAction(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        int feedback = 0;
        String q1 = q1Text.getText();
        String q2 = q2Text.getText();
        String q3 = q3Text.getText();
        application = new Applic(q1, q2, q3);

        if (q1.isEmpty()) {
            checkForm.setVisible(true);
            checkForm.setStyle("-fx-text-fill: Red;");
            checkForm.setText("Please check your form");
            qOneLabel.setVisible(true);
            qOneLabel.setStyle("-fx-text-fill: Red;");
            qOneLabel.setText("Please answer the above question");
        } else if (q2.isEmpty()) {
            checkForm.setVisible(true);
            checkForm.setStyle("-fx-text-fill: Red;");
            checkForm.setText("Please check your form");
            qTwoLabel.setVisible(true);
            qTwoLabel.setStyle("-fx-text-fill: Red;");
            qTwoLabel.setText("Please answer the above question");
        } else if (q3.isEmpty()) {
            checkForm.setVisible(true);
            checkForm.setStyle("-fx-text-fill: Red;");
            checkForm.setText("Please check your form");
            qThreeLabel.setVisible(true);
            qThreeLabel.setStyle("-fx-text-fill: Red;");
            qThreeLabel.setText("Please answer the above question");
        } else {
            feedback = appLogic.insertAppQuestions(application);

            stage = (Stage) backButton.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("Attachments.fxml"));
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

    @FXML
    private void logoutPressed(ActionEvent event) throws IOException {
        stage = (Stage) welcomeLabel.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        welcomeLabel.setText("Welcome " + name);

        q1Text.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    qOneLabel.setVisible(false);
                } else {
                    if (q1Text.getText().isEmpty()) {
                        qOneLabel.setVisible(true);
                        qOneLabel.setStyle("-fx-text-fill: Red;");
                        qOneLabel.setText("Please answer the question above");
                    }
                }
            }
        });

        q2Text.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    qTwoLabel.setVisible(false);
                } else {
                    if (q2Text.getText().isEmpty()) {
                        qTwoLabel.setVisible(true);
                        qTwoLabel.setStyle("-fx-text-fill: Red;");
                        qTwoLabel.setText("Please answer the question above");
                    }
                }
            }
        });

        q3Text.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    qThreeLabel.setVisible(false);
                } else {
                    if (q3Text.getText().isEmpty()) {
                        qThreeLabel.setVisible(true);
                        qThreeLabel.setStyle("-fx-text-fill: Red;");
                        qThreeLabel.setText("Please answer the question above");
                    }
                }
            }
        });
    }

}
