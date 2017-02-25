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

//list all imports
import static dataAccess.AccountLogic.currentId;
import static dataAccess.AccountLogic.name;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author CocoMango
 */
public class RegistrationCompleteController implements Initializable {

    Stage stage;
    Parent root;

    //declare all needed GUI Components
    @FXML
    private CheckBox termsAccept;
    @FXML
    private Button submitButton;

    @FXML
    private Button backButton;

    @FXML
    private Label welcomeLabel;
    
    @FXML
    private Label acceptLabel;
    //TODO: work out how to use jcaptcha (non-functional requirement)

    /*
     * method for 
     * 
     */
    @FXML
    private void submitButtonPressed(ActionEvent event) throws IOException {
        //TODO: UPDATE form details into student table where primary key = currentUser
        if (termsAccept.isSelected()) {
            //alert user registration was succesfull
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Your Registration has been successful");
            alert.showAndWait();
            stage = (Stage) submitButton.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("StudentDashboard_1.fxml"));
        } else {
                      acceptLabel.setVisible(true);
                acceptLabel.setStyle("-fx-text-fill: Red;");
                 acceptLabel.setText("You must accept the terms and conditions to register");
        
        }

        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        //TODO: Form Validation on submit
    }
    /*
     * method for 
     * 
     */

    @FXML
    private void registerBackButtonPressed(ActionEvent event) throws IOException {
               stage = (Stage) backButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("RegistrationCourse.fxml"));
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
    private void aboutPressed(ActionEvent event) throws IOException {
        stage = (Stage) welcomeLabel.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("aboutFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // welcomeLabel.setText("Welcom" + " " + name);
        termsAccept.focusedProperty().addListener(new ChangeListener<Boolean>()
{
    @Override
    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
    {
        if (newPropertyValue)
        {
            acceptLabel.setVisible(false);
        }
        else
        {
            if(termsAccept.getText().isEmpty()){
                acceptLabel.setVisible(true);
                acceptLabel.setStyle("-fx-text-fill: Red;");
                 acceptLabel.setText("You must accept the terms and conditions to register");
            }      
        }
        }
});

    }
}
