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
import dataAccess.AccountLogic;
import static dataAccess.AccountLogic.currentId;
import dataAccess.ProfileLogic;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.Account;
import model.Profile;

/**
 * FXML Controller class
 *
 * @author owenw
 */
public class RegistrationPersonalController implements Initializable {

    //declair private filed 
    private final AccountLogic accountLogic;
    private ProfileLogic profileLogic;

    public RegistrationPersonalController() {
        accountLogic = new AccountLogic();
        profileLogic = new ProfileLogic();
    }
    private Account account;
    private Profile profile;

    Stage stage;
    Parent root;

//declare all needed GUI Components
    @FXML
    private TextField familyName;
    @FXML
    private TextField givenNameText;
    @FXML
    private TextField emailText;
    @FXML
    private RadioButton maleRadio;
    @FXML
    private RadioButton femaleRadio;
    @FXML
    private TextField streetAddress;
    @FXML
    private TextField suburb;
    @FXML
    private ComboBox stateCombo;
    @FXML
    private TextField postCode;
    @FXML
    private DatePicker dobPick;
    @FXML
    private TextField contactNo;

    @FXML
    private Button nextButton;
    @FXML
    private Button backButton;
    @FXML
    private Label familyLabel;
    @FXML
    private Label givenLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label suburbLabel;
    @FXML
    private Label postcodeLabel;
    @FXML
    private Label stateLabel;
    @FXML
    private Label genderLabel;
    @FXML
    private Label dobLabel;
    @FXML
    private Label numberLabel;
    @FXML
    private Label checkForm;

    


    /*
     * method for 
     * 
     */
    @FXML
    private void registerNextButtonPressed(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        int feedback = 0;
        
        LocalDate dobLocal = dobPick.getValue();
         java.sql.Date dob;
        if (dobLocal == null) {
            dob = null;
        }else{
            dob =  convertToSqlDate(dobPick.getValue());
        }
        final ToggleGroup group = new ToggleGroup();
        maleRadio.isSelected();
        maleRadio.setToggleGroup(group);
        maleRadio.setUserData("male");
        femaleRadio.setToggleGroup(group);
        femaleRadio.setUserData("female");

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {

            }
        });
        String sex = group.getSelectedToggle().getUserData().toString();
        //collectiong the values from the UI and store them in local variables
        AccountLogic.name = givenNameText.getText();
        String firstName = givenNameText.getText();
        String lastName = familyName.getText();
        String email = emailText.getText();
        String street = streetAddress.getText();
        String city = suburb.getText();
        String state = stateCombo.getValue().toString();
        String postcode = postCode.getText();
        
        String phone = contactNo.getText();

        //Creating an instance of Account
        account = new Account(firstName, lastName, email, sex, street,
                city, state, postcode, dob, phone);

        if (firstName.isEmpty()) {
            checkForm.setVisible(true);
            checkForm.setStyle("-fx-text-fill: Red;");
            checkForm.setText("Please check your form");
            familyLabel.setVisible(true);
            familyLabel.setStyle("-fx-text-fill: Red;");
            familyLabel.setText("Please enter your family name");
        } else if (firstName.isEmpty()) {
            checkForm.setVisible(true);
            checkForm.setStyle("-fx-text-fill: Red;");
            checkForm.setText("Please check your form");
            givenLabel.setVisible(true);
            givenLabel.setStyle("-fx-text-fill: Red;");
            givenLabel.setText("Please enter your given name");
        } else if (street.isEmpty()) {
            checkForm.setVisible(true);
            checkForm.setStyle("-fx-text-fill: Red;");
            checkForm.setText("Please check your form");
            streetLabel.setVisible(true);
            streetLabel.setStyle("-fx-text-fill: Red;");
            streetLabel.setText("Please enter your address");
        } else if (city.isEmpty()) {
            checkForm.setVisible(true);
            checkForm.setStyle("-fx-text-fill: Red;");
            checkForm.setText("Please check your form");
            suburbLabel.setVisible(true);
            suburbLabel.setStyle("-fx-text-fill: Red;");
            suburbLabel.setText("Please enter your address");
        } else if (state.isEmpty()) {
            checkForm.setVisible(true);
            checkForm.setStyle("-fx-text-fill: Red;");
            checkForm.setText("Please check your form");
            stateLabel.setVisible(true);
            stateLabel.setStyle("-fx-text-fill: Red;");
            stateLabel.setText("Please enter your state");
        } else if (postcode.isEmpty()) {
            checkForm.setVisible(true);
            checkForm.setStyle("-fx-text-fill: Red;");
            checkForm.setText("Please check your form");
            postcodeLabel.setVisible(true);
            postcodeLabel.setStyle("-fx-text-fill: Red;");
            postcodeLabel.setText("Please enter your post code");
        } else if (phone.isEmpty()) {
            checkForm.setVisible(true);
            checkForm.setStyle("-fx-text-fill: Red;");
            checkForm.setText("Please check your form");
            numberLabel.setVisible(true);
            numberLabel.setStyle("-fx-text-fill: Red;");
            numberLabel.setText("Please enter your post code");
        } else if (postcode.length() != 4) {
            checkForm.setVisible(true);
            checkForm.setStyle("-fx-text-fill: Red;");
            checkForm.setText("Please check your form");
            postcodeLabel.setVisible(true);
            postcodeLabel.setStyle("-fx-text-fill: Red;");
            postcodeLabel.setText("{Postcode must contain 4 numbers");
        } else if (postcode.matches("[a-zA-Z]+")) {
            checkForm.setStyle("-fx-text-fill: Red;");
            checkForm.setText("Please check your form");
            postcodeLabel.setVisible(true);
            postcodeLabel.setStyle("-fx-text-fill: Red;");
            postcodeLabel.setText("Postcode can not contain text");
        } else if (phone.length() != 10) {
            checkForm.setText("Please check your form");
            numberLabel.setVisible(true);
            numberLabel.setStyle("-fx-text-fill: Red;");
            numberLabel.setText("Contact number must be 10 digits");
        } else if (phone.matches("[a-zA-Z]+")) {
            checkForm.setText("Please check your form");
            numberLabel.setVisible(true);
            numberLabel.setStyle("-fx-text-fill: Red;");
            numberLabel.setText("Contact number must not contain text");
        } else {
            //Creating and instance of AccountLogic and calling InsertAccount method       
            feedback = accountLogic.insertAccount(account);
            if (feedback > 0) {
                //get reference to the button's stage         
                stage = (Stage) nextButton.getScene().getWindow();
                //load up OTHER FXML document
                root = FXMLLoader.load(getClass().getResource("RegistrationCourse.fxml"));
                //create a new scene with root and set the stage
            }
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private void backtButtonPressed(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        stage = (Stage) backButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("RegistrationEmail.fxml"));
        //create a new scene with root and set the stage
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
    /*
     * method for 
     * 
     */

    private java.sql.Date convertToSqlDate(LocalDate localDate) {
        java.sql.Date date = new Date(
                localDate.getYear() - 1900,
                localDate.getMonthValue() - 1,
                localDate.getDayOfMonth()
        );
        return date;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            account = accountLogic.findAccountById();
            if (account.getFirstName() != null) {
                familyName.setText(account.getLastName());
                givenNameText.setText(account.getFirstName());
                emailText.setText(account.getEmail());
                streetAddress.setText(account.getStreet());
                suburb.setText(account.getCity());
                stateCombo.setValue(account.getState());
                postCode.setText(account.getPostcode());
                contactNo.setText(account.getPhone());
                String gender = account.getSex();
                if (gender.equals("male")) {
                    maleRadio.setSelected(true);
                } else {
                    femaleRadio.setSelected(true);
                }
                dobPick.setValue(account.getDob().toLocalDate());
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RegistrationPersonalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        familyName.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    familyLabel.setVisible(false);
                } else {
                    if (familyName.getText().isEmpty()) {
                        familyLabel.setVisible(true);
                        familyLabel.setStyle("-fx-text-fill: Red;");
                        familyLabel.setText("Please fill in your Family Name");
                    }
                }
            }
        });

        givenNameText.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    givenLabel.setVisible(false);
                } else {
                    if (givenNameText.getText().isEmpty()) {
                        givenLabel.setVisible(true);
                        givenLabel.setStyle("-fx-text-fill: Red;");
                        givenLabel.setText("Please fill in your given name");
                    }
                }
            }
        });

        emailText.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    emailLabel.setVisible(false);
                } else {
                    if (emailText.getText().isEmpty()) {
                        emailLabel.setVisible(true);
                        emailLabel.setStyle("-fx-text-fill: Red;");
                        emailLabel.setText("Please fill in your email");
                    }
                }
            }
        });
        emailText.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    emailLabel.setVisible(false);
                } else {
                    if (emailText.getText().length() > 1 && emailText.getText().endsWith("@student.unsw.edu.au")) {
                    } else {
                        emailLabel.setVisible(true);
                        emailLabel.setStyle("-fx-text-fill: Red;");
                        emailLabel.setText("emails must end in '@student.unsw.edu.au'");
                    }
                }
            }
        });

        streetAddress.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    streetLabel.setVisible(false);
                } else {
                    if (streetAddress.getText().isEmpty()) {
                        streetLabel.setVisible(true);
                        streetLabel.setStyle("-fx-text-fill: Red;");
                        streetLabel.setText("Please fill in your address");
                    }
                }
            }
        });

        suburb.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    suburbLabel.setVisible(false);
                } else {
                    if (suburb.getText().isEmpty()) {
                        suburbLabel.setVisible(true);
                        suburbLabel.setStyle("-fx-text-fill: Red;");
                        suburbLabel.setText("Please fill in your suburb");
                    }
                }
            }
        });

        stateCombo.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    stateLabel.setVisible(false);
                } else {
                    if (stateCombo.getValue() == null) {
                        stateLabel.setVisible(true);
                        stateLabel.setStyle("-fx-text-fill: Red;");
                        stateLabel.setText("Please select your suburb");
                    }
                }
            }
        });

        postCode.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    postcodeLabel.setVisible(false);
                } else {
                    if (postCode.getText().isEmpty()) {
                        postcodeLabel.setVisible(true);
                        postcodeLabel.setStyle("-fx-text-fill: Red;");
                        postcodeLabel.setText("Please select your postcode");
                    }
                }
            }
        });

        postCode.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    postcodeLabel.setVisible(false);
                } else {
                    if (postCode.getText().matches("[a-zA-Z]+")) {

                        postcodeLabel.setVisible(true);
                        postcodeLabel.setStyle("-fx-text-fill: Red;");
                        postcodeLabel.setText("postcode can only accept numbers");
                    }
                }
            }
        });

        postCode.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    postcodeLabel.setVisible(false);
                } else {
                    if (postCode.getText().length() != 4) {
                        postcodeLabel.setVisible(true);
                        postcodeLabel.setStyle("-fx-text-fill: Red;");
                        postcodeLabel.setText("postcode must only be 4 numbers");
                    }
                }
            }
        });

        contactNo.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    numberLabel.setVisible(false);
                } else {
                    if (contactNo.getText().isEmpty()) {
                        numberLabel.setVisible(true);
                        numberLabel.setStyle("-fx-text-fill: Red;");
                        numberLabel.setText("Please enter your contact number");
                    }
                }
            }
        });

        contactNo.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    numberLabel.setVisible(false);
                } else {
                    if (contactNo.getText().matches("[a-zA-Z]+")) {
                        numberLabel.setVisible(true);
                        numberLabel.setStyle("-fx-text-fill: Red;");
                        numberLabel.setText("Contact number can only access numbers");
                    }
                }
            }
        });

        contactNo.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    numberLabel.setVisible(false);
                } else {
                    if (contactNo.getText().length() != 10) {
                        numberLabel.setVisible(true);
                        numberLabel.setStyle("-fx-text-fill: Red;");
                        numberLabel.setText("contact number must be 10 numbers");

                    }
                }
            }
        });
        //declare comboBox dropdown components
        ObservableList<String> states = FXCollections.observableArrayList("NSW", "NT", "SA", "WA", "TAS", "VIC", "QLD", "ACT"
        );
        //set comboBox components
        stateCombo.setItems(states);

    }
}
