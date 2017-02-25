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
import com.itextpdf.awt.geom.Rectangle2D;
import dataAccess.AccountLogic;
import dataAccess.LoginLogic;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.LoginModel;

/**
 * FXML Controller class
 *
 * @author CocoMango
 */
public class LoginPageController implements Initializable {

    private final AccountLogic accountLogic;
    //public static int accountId;
    Stage stage;
    Parent root;

    public LoginPageController() {
        accountLogic = new AccountLogic();
    }
    //declare all needed GUI Components
    @FXML
    private TextField userTextField;
    @FXML
    private TextField PassField;
    @FXML
    private Button loginButton;
    @FXML
    private Label regoLabel;
    @FXML
    private Label aboutLabel;

    /*
     * method for 
     * 
     */
    @FXML
    private void loginButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        //declare variables to store user and password from the user input

        String zID = userTextField.getText().toLowerCase();
        String candidate = PassField.getText();
        LoginModel model = accountLogic.login(zID, candidate);

        if (model.isLogin() && model.getRole().equals("Student")) {
            //get reference to the button's stage         
            stage = (Stage) loginButton.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("StudentDashboard_1.fxml"));

        } else if (model.isLogin() && model.getRole().equals("Admin")) {
            stage = (Stage) loginButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("StudentCentralDashboard.fxml"));
        } else if (model.isLogin() && model.getRole().equals("Officer")) {
            stage = (Stage) loginButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("OfficerDashboard.fxml"));
        } else {
            stage = (Stage) loginButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Wrong Username or Password!");
            alert.show();
            stage = (Stage) loginButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        }
        Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();

        Scene scene = new Scene(root);
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        stage.setScene(scene);
        stage.show();
    }

    //Go to Register Page
    @FXML
    private void registerButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        stage = (Stage) loginButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("RegistrationEmail.fxml"));

        Screen screen = Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void forgotPasswordButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = new Stage();
        stage.setTitle("Forgot Password");
        stage.getIcons().add(new Image("Images/icon.png"));
        root = FXMLLoader.load(getClass().getResource("ForgotPassword.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
     @FXML
    private void myDashPressed(ActionEvent event) throws IOException {
        stage = (Stage) userTextField.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("StudentDashboard_1.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void aboutPressed(ActionEvent event) throws IOException {
        stage = (Stage) userTextField.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("aboutFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       /* aboutLabel.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 1) {

                try {
                    stage = (Stage) aboutLabel.getScene().getWindow();
                    root = FXMLLoader.load(getClass().getResource("aboutFXML.fxml"));
                    Screen screen = Screen.getPrimary();
                    javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setX(bounds.getMinX());
                    stage.setY(bounds.getMinY());
                    stage.setWidth(bounds.getWidth());
                    stage.setHeight(bounds.getHeight());
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(LoginPageController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
*/
    }

}
