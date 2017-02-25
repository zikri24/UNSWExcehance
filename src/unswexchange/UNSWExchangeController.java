/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unswexchange;

import dataAccess.ApplicationLogic;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.Applic;

/**
 *
 * @author Zikri
 */
public class UNSWExchangeController implements Initializable {

    private ApplicationLogic appLogic = new ApplicationLogic();
    private Applic application;

    @FXML
    private TextField firstNameText;
    @FXML
    private TextField lastNameText;
    @FXML
    private TextField studentIdText;
    @FXML
    private RadioButton sexRadio;
    @FXML
    private TextField emailText;
    @FXML
    private TextField streetText;
    @FXML
    private TextField cityText;
    @FXML
    private ComboBox stateCombo;
    @FXML
    private TextField postcodeText;
    @FXML
    private DatePicker dobDatePic;
    @FXML
    private TextField phoneText;

    @FXML
    private void handleButtonAction(ActionEvent event) throws ClassNotFoundException, SQLException {
        /*String firstName = firstNameText.getText();
        String lastName = lastNameText.getText();
        String studentId = studentIdText.getText();
        String sex = sexRadio.getText();
        String email = emailText.getText();
        String street = streetText.getText();
        String city = cityText.getText();
        String state = stateCombo.getValue().toString();
        String postcode = postcodeText.getText();
        LocalDate date = dobDatePic.getValue();
        java.sql.Date dob = convertToSqlDate(date);
        String phone = phoneText.getText();
        application = new Applic(firstName, lastName, studentId, sex, email, street, city, state, postcode, dob, phone);
        
        appLogic.insertApplication1(application);
                */
    }
    
     private java.sql.Date convertToSqlDate(LocalDate localDate) {
        java.sql.Date date = new Date(
                localDate.getYear() - 1900,
                localDate.getMonthValue() - 1,
                localDate.getDayOfMonth()
        );
        return date;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> states = FXCollections.observableArrayList("NSW", "NT", "SA", "WA", "TAS", "VIC", "QLD", "ACT"
        );
         stateCombo.setItems(states);
    }

}
