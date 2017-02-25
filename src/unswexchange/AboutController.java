/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unswexchange;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author owenw
 */
public class AboutController implements Initializable {

    Stage stage;
    Parent root;
    /**
     * Initializes the controller class.
     */
    @FXML
    private Label homeLabel;
    @FXML
    private Label loginLabel;

    @FXML
    private void logoutPressed(ActionEvent event) throws IOException {
        stage = (Stage) homeLabel.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        homeLabel.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 1) {
                try {
                    Stage stage = (Stage) homeLabel.getScene().getWindow();
                    root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(LoginPageController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        loginLabel.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 1) {
                try {
                    Stage stage = (Stage) loginLabel.getScene().getWindow();
                    root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(LoginPageController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

}
