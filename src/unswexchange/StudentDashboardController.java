/**
 *
 * INFS3605 Assignment Two - Group Assignment - UNSW Exchange The Student
 * Exchange application allows exchange officers and student central
 * administrators to handle the CRUD functionality related to a students
 * outbound exchange application. As such a student can register for the system
 * and submit an application. At this point, an exchange officer can search,
 * view, self-assign, accept or decline and application. The application will
 * also handle reporting and email notification functionality.
 *
 * @author CocoMango
 *
 * @version 1.0
 */
package unswexchange;

import static dataAccess.AccountLogic.name;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author owenw
 */
public class StudentDashboardController implements Initializable {

    Stage stage;
    Parent root;
    @FXML
    private Label welcomeLabel;
    @FXML
    private Button appSubmitButton;
    @FXML
    private ImageView logoImageView;
    @FXML
    private ImageView editProfileImage;
    @FXML
    private ImageView submitAppImage;
    @FXML
    private ImageView viewAppImage;
    @FXML
    private ImageView supportImage;
    @FXML
    private Button myAppButton;
    @FXML
    private Button profileButton;
    @FXML
    private Button supportButton;

    @FXML
    private void appSubmitButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
//Take user to their dashboard               
        //get reference to the button's stage

        stage = (Stage) logoImageView.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("StudentApply.fxml"));
        // }
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
        stage.setScene(scene);
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        stage.show();
    }

    @FXML
    private void supportButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
//Take user to their dashboard               
        //get reference to the button's stage         
        stage = (Stage) supportButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("supportFXML.fxml"));
        // }
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
        stage.setScene(scene);
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        stage.show();
    }

    @FXML

    private void myAppButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
//Take user to their dashboard               
        //get reference to the button's stage         
        stage = (Stage) logoImageView.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("StudentMyApplications.fxml"));
        // }
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
        stage.setScene(scene);
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        stage.show();
    }

    @FXML
    private void profileButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
//Take user to their dashboard               
        //get reference to the button's stage         
        stage = (Stage) logoImageView.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("StudentMyProfile.fxml"));
        // }
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
        stage.setScene(scene);
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        stage.show();
    }

    @FXML
    private void logoutPressed(ActionEvent event) throws IOException {
        stage = (Stage) logoImageView.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        welcomeLabel.setText("Welcome " + name);
        submitAppImage.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //get reference to the button's stage         
                stage = (Stage) submitAppImage.getScene().getWindow();
                try {
                    //load up OTHER FXML document
                    root = FXMLLoader.load(getClass().getResource("StudentApply.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(StudentDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                }
                //create a new scene with root and set the stage
                Scene scene = new Scene(root);
                stage.setScene(scene);
                Screen screen = Screen.getPrimary();
                javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
                stage.setScene(scene);
                stage.setX(bounds.getMinX());
                stage.setY(bounds.getMinY());
                stage.setWidth(bounds.getWidth());
                stage.setHeight(bounds.getHeight());
                stage.show();
            }
        });

        viewAppImage.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //get reference to the button's stage         
                stage = (Stage) viewAppImage.getScene().getWindow();
                try {
                    //load up OTHER FXML document
                    root = FXMLLoader.load(getClass().getResource("StudentMyApplications.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(StudentDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                }

                //create a new scene with root and set the stage
                Scene scene = new Scene(root);
                stage.setScene(scene);
                Screen screen = Screen.getPrimary();
                javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
                stage.setScene(scene);
                stage.setX(bounds.getMinX());
                stage.setY(bounds.getMinY());
                stage.setWidth(bounds.getWidth());
                stage.setHeight(bounds.getHeight());
                stage.show();
                event.consume();
            }
        });

        supportImage.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //get reference to the button's stage         
                stage = (Stage) supportImage.getScene().getWindow();
                try {
                    //load up OTHER FXML document
                    root = FXMLLoader.load(getClass().getResource("supportFXML.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(StudentDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                }
                //create a new scene with root and set the stage
                Scene scene = new Scene(root);
                stage.setScene(scene);
                Screen screen = Screen.getPrimary();
                javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
                stage.setScene(scene);
                stage.setX(bounds.getMinX());
                stage.setY(bounds.getMinY());
                stage.setWidth(bounds.getWidth());
                stage.setHeight(bounds.getHeight());
                stage.show();
                event.consume();
            }
        });

        editProfileImage.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //get reference to the button's stage         
                stage = (Stage) editProfileImage.getScene().getWindow();
                try {
                    //load up OTHER FXML document
                    root = FXMLLoader.load(getClass().getResource("StudentMyProfile.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(StudentDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                }
                //create a new scene with root and set the stage
                Scene scene = new Scene(root);
                stage.setScene(scene);
                Screen screen = Screen.getPrimary();
                javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
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
