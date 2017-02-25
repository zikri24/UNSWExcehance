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

import dataAccess.AccountLogic;

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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author Zikri
 */
public class RegistrationEmailController implements Initializable {

    //Declairing Root and Statge for 
    Stage stage;
    Parent root;
    //declair private filed 
    private final AccountLogic accountLogic;

    public RegistrationEmailController() {
        accountLogic = new AccountLogic();
    }
    //declare all needed GUI Components
    @FXML
    private TextField zIDTextField;
    @FXML
    private TextField zIDConfirmTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField confirmPasswordTextField;
    @FXML
    private Button nextButton;
    @FXML
    private Button backButton;
    @FXML
    private Label confirmLabel;
    @FXML
    private Label zIDVal;
    @FXML
    private Label zIDMatchLabel;
    @FXML
    private Label confirmZidLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label confirmPassLabel;
    @FXML
    private Label checkForm;

    @FXML
    private void registerButtonPressed(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        int feedback;
        String zID = zIDTextField.getText().toLowerCase();
        String confirmzID = zIDConfirmTextField.getText().toLowerCase();
        String password = passwordTextField.getText();
        String confirmPassword = confirmPasswordTextField.getText();
        confirmZidLabel.setVisible(false);
        checkForm.setVisible(false);
        zIDVal.setVisible(false);
        passwordLabel.setVisible(false);
        confirmPassLabel.setVisible(false);

        if (zID.isEmpty()) {
            checkForm.setVisible(true);
            checkForm.setStyle("-fx-text-fill: Red;");
            checkForm.setText("Please check your form");
            zIDVal.setVisible(true);
            zIDVal.setStyle("-fx-text-fill: Red;");
            zIDVal.setText("Please enter your Zid");
        } else if (confirmzID.isEmpty()) {
            checkForm.setVisible(true);
            checkForm.setStyle("-fx-text-fill: Red;");
            checkForm.setText("Please check your form");
            confirmZidLabel.setVisible(true);
            confirmZidLabel.setStyle("-fx-text-fill: Red;");
            confirmZidLabel.setText("Please confirm your Zid");
        } else if (password.isEmpty()) {
            checkForm.setVisible(true);
            checkForm.setStyle("-fx-text-fill: Red;");
            checkForm.setText("Please check your form");
            passwordLabel.setVisible(true);
            passwordLabel.setStyle("-fx-text-fill: Red;");
            passwordLabel.setText("Please enter your password");
        } else if (confirmPassword.isEmpty()) {
            checkForm.setVisible(true);
            checkForm.setStyle("-fx-text-fill: Red;");
            checkForm.setText("Please check your form");
            confirmPassLabel.setVisible(true);
            confirmPassLabel.setStyle("-fx-text-fill: Red;");
            confirmPassLabel.setText("Please enter your password");
        } else if (zIDTextField.getText().length() != 8) {
            zIDVal.setVisible(true);
            zIDVal.setStyle("-fx-text-fill: Red;");
            zIDVal.setText("Zid must be 8 spaces");
        } else if (zID == null ? confirmzID != null : !zID.equals(confirmzID)) {
            checkForm.setVisible(true);
            checkForm.setStyle("-fx-text-fill: Red;");
            checkForm.setText("Please check your form");
            confirmZidLabel.setVisible(true);
            confirmZidLabel.setStyle("-fx-text-fill: Red;");
            confirmZidLabel.setText("Your Zid's do not match");

        } else if (password == null ? confirmPassword != null : !password.equals(confirmPassword)) {
            checkForm.setVisible(true);
            checkForm.setStyle("-fx-text-fill: Red;");
            checkForm.setText("Please check your form");
            confirmPassLabel.setVisible(true);
            confirmPassLabel.setStyle("-fx-text-fill: Red;");
            confirmPassLabel.setText("Your passwords do not match");
        } else {

            feedback = accountLogic.register(zID, password, "Student");
            if (feedback > 0) {
                //get reference to the button's stage         
                stage = (Stage) nextButton.getScene().getWindow();
                //load up OTHER FXML document
                root = FXMLLoader.load(getClass().getResource("RegistrationPersonal.fxml"));
            }
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    //Register the back button
    @FXML
    private void registerBackButtonPressed(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        stage = (Stage) backButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        zIDTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    zIDVal.setVisible(false);
                } else {
                    if (zIDTextField.getText().isEmpty()) {
                        zIDVal.setVisible(true);
                        zIDVal.setStyle("-fx-text-fill: Red;");
                        zIDVal.setText("Please Enter Your zID");
                    }
                }
            }
        });
        zIDTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    zIDVal.setVisible(false);
                } else {
                    if (zIDTextField.getText().isEmpty()) {
                        zIDVal.setVisible(true);
                        zIDVal.setStyle("-fx-text-fill: Red;");
                        zIDVal.setText("Please Enter Your zID");
                    }
                }
            }
        });

        zIDTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    zIDVal.setVisible(false);
                } else {
                    if (zIDTextField.getText().length() != 8) {
                        zIDVal.setVisible(true);
                        zIDVal.setStyle("-fx-text-fill: Red;");
                        zIDVal.setText("Zid must be 8 spaces");
                    }
                }
            }

        });
        zIDTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    zIDVal.setVisible(false);
                } else {
                    String string = zIDTextField.getText();
                    if (string.startsWith("z")) {
                        zIDVal.setVisible(false);
                    } else {
                        zIDVal.setStyle("-fx-text-fill: Red;");
                        zIDVal.setText("Zid must start with a z");
                    }
                }
            }

        });

        zIDConfirmTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    confirmZidLabel.setVisible(false);
                } else {
                    if (zIDConfirmTextField.getText().isEmpty()) {
                        confirmZidLabel.setVisible(true);
                        confirmZidLabel.setStyle("-fx-text-fill: Red;");
                        confirmZidLabel.setText("Please Enter Your Confirmed Zid");
                    }
                }
            }
        });
        zIDConfirmTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {

                } else {
                    if (zIDConfirmTextField.getText().equals((zIDTextField.getText()))) {

                    } else {
                        zIDTextField.setStyle("-fx-text-fill: Red;");
                        zIDConfirmTextField.setStyle("-fx-text-fill: Red;");
                        zIDConfirmTextField.setStyle("-fx-text-fill: Red;");
                        confirmZidLabel.setVisible(true);
                        confirmZidLabel.setStyle("-fx-text-fill: Red;");
                        confirmZidLabel.setText("ZID does not match");
                    }
                }

            }

        });

        passwordTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    passwordLabel.setVisible(false);
                } else {
                    if (passwordTextField.getText().isEmpty()) {
                        passwordLabel.setVisible(true);
                        passwordLabel.setStyle("-fx-text-fill: Red;");
                        passwordLabel.setText("Please Enter Your Password");
                    }
                }

            }
        });

        confirmPasswordTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    confirmPassLabel.setVisible(false);
                } else {
                    if (confirmPasswordTextField.getText().isEmpty()) {
                        confirmPassLabel.setVisible(true);
                        confirmPassLabel.setStyle("-fx-text-fill: Red;");
                        confirmPassLabel.setText("Please Enter Your Confirmed Password");
                    }
                }

            }
        });
        confirmPasswordTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    confirmPassLabel.setVisible(false);
                } else {
                    if (confirmPasswordTextField.getText().equals((passwordTextField.getText()))) {

                    } else {
                        zIDTextField.setStyle("-fx-text-fill: Red;");
                        zIDConfirmTextField.setStyle("-fx-text-fill: Red;");
                        zIDConfirmTextField.setStyle("-fx-text-fill: Red;");
                        confirmPassLabel.setVisible(true);
                        confirmPassLabel.setStyle("-fx-text-fill: Red;");
                        confirmPassLabel.setText("Password Does Not Match");
                    }
                }

            }
        });
    }
}
