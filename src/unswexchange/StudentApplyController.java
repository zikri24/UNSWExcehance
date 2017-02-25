/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unswexchange;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ismmedina
 */
public class StudentApplyController implements Initializable {

    Stage stage;
    Parent root;
    @FXML
    private Button startButton;

    @FXML
        private Label welcomeLabel;

     
    /**
     * Initializes the controller class.
     */
    @FXML
    private void startButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) startButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("StudentApplyGeneral.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void backButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) startButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("StudentDashboard_1.fxml"));
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


    //create a new scene with root and set the stage
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}
