/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unswexchange;

import static dataAccess.AccountLogic.name;
import dataAccess.ApplicationLogic;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Zikri
 */
public class OfficerDashboardController implements Initializable {

    private final ApplicationLogic appLogic;

    public OfficerDashboardController() {
        appLogic = new ApplicationLogic();
    }

    Stage stage;
    Parent root;

    @FXML
    private Label welcomeLabel;
    @FXML
    private ImageView logoImageView;
    @FXML
    private Button allAppButton;
    @FXML
    private Button myAppButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button profileButton;
    @FXML
    private ImageView allAppsImage;
    @FXML
    private ImageView myAppsImage;
    @FXML
    private ImageView searchImage;
    @FXML
    private ImageView profileImage;
    @FXML
    private ImageView allAppsNotImage;
    @FXML
    private Label allAppsNotLabel;
    @FXML
    private Label myAppsNotLabel;
    @FXML
    private ImageView myAppsNotImage;
    @FXML
    private Label searchNotLabel;
    @FXML
    private ImageView searchNotImage;
    @FXML
    private Label profileNotLabel;
    @FXML
    private ImageView profileNotImage;

    /**
     * Initializes the controller class.
     */
    @FXML
    private void allAppButtonPressed(ActionEvent event) throws IOException {
        stage = (Stage) allAppButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("OfficerAllApps.fxml"));
        Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        stage.show();
    }

    @FXML
    private void myAppButtonPressed(ActionEvent event) throws IOException {
        stage = (Stage) allAppButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("OfficerMyApps.fxml"));
        Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        stage.show();
    }

    @FXML
    private void searchButtonPressed(ActionEvent event) throws IOException {
        stage = (Stage) allAppButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("Search.fxml"));
        Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        stage.show();
    }

    @FXML
    private void profileButtonPressed(ActionEvent event) {

    }

    @FXML
    private void logoutPressed(ActionEvent event) throws IOException {
        stage = (Stage) allAppButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
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

        welcomeLabel.setText("Welcome " + name);
        int appCount = 0;
        int myAppCount = 0;
        try {
            appCount = appLogic.countPendingAppliocation();
            myAppCount = appLogic.countMyPendingAppliocation();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OfficerDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (appCount > 0) {
            allAppsNotLabel.setVisible(true);
            allAppsNotImage.setVisible(true);
            allAppsNotLabel.setText(String.valueOf(appCount));
        } 
        if (myAppCount > 0) {
            myAppsNotLabel.setVisible(true);
            myAppsNotImage.setVisible(true);
            myAppsNotLabel.setText(String.valueOf(myAppCount));
        }

        allAppsImage.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //get reference to the button's stage         
                stage = (Stage) allAppsImage.getScene().getWindow();
                try {
                    //load up OTHER FXML document
                    root = FXMLLoader.load(getClass().getResource("OfficerAllApps.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(StudentDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                }

                //create a new scene with root and set the stage
                Screen screen = Screen.getPrimary();
                javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setX(bounds.getMinX());
                stage.setY(bounds.getMinY());
                stage.setWidth(bounds.getWidth());
                stage.setHeight(bounds.getHeight());
                stage.show();
                event.consume();
            }
        });

        myAppsImage.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //get reference to the button's stage         
                stage = (Stage) myAppsImage.getScene().getWindow();
                try {
                    //load up OTHER FXML document
                    root = FXMLLoader.load(getClass().getResource("OfficerMyApps.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(StudentDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                }

                //create a new scene with root and set the stage
                Screen screen = Screen.getPrimary();
                javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setX(bounds.getMinX());
                stage.setY(bounds.getMinY());
                stage.setWidth(bounds.getWidth());
                stage.setHeight(bounds.getHeight());
                stage.show();
                event.consume();
            }
        });
        searchImage.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //get reference to the button's stage         
                stage = (Stage) searchImage.getScene().getWindow();
                try {
                    //load up OTHER FXML document
                    root = FXMLLoader.load(getClass().getResource("Search.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(StudentDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                }
                //create a new scene with root and set the stage
                Screen screen = Screen.getPrimary();
                javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setX(bounds.getMinX());
                stage.setY(bounds.getMinY());
                stage.setWidth(bounds.getWidth());
                stage.setHeight(bounds.getHeight());
                stage.show();
                event.consume();
            }
        });
        profileImage.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //get reference to the button's stage         
                stage = (Stage) profileImage.getScene().getWindow();
                try {
                    //load up OTHER FXML document
                    root = FXMLLoader.load(getClass().getResource("StudentMyProfile.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(StudentDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                }
                //create a new scene with root and set the stage
                Screen screen = Screen.getPrimary();
                javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setX(bounds.getMinX());
                stage.setY(bounds.getMinY());
                stage.setWidth(bounds.getWidth());
                stage.setHeight(bounds.getHeight());
                stage.show();
                event.consume();
            }
        });
    }

}
