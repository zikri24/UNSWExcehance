/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unswexchange;

import dataAccess.AccountLogic;
import static dataAccess.AccountLogic.name;
import static dataAccess.AccountLogic.school;
import dataAccess.ApplicationLogic;
import java.io.FileInputStream;
import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
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
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Applic;

/**
 * FXML Controller class
 *
 * @author ismmedina
 */
public class ApplyDeclairationController implements Initializable {

    private final ApplicationLogic appLogic;
    private final AccountLogic accountLogic;
    private Applic application;
    private int applicationId;
    ArrayList<String> emails;
    String email;

    public ApplyDeclairationController() {
        appLogic = new ApplicationLogic();
        accountLogic = new AccountLogic();
    }

    Stage stage;
    Parent root;

    @FXML
    private Button submitButton;
    @FXML
    private CheckBox termsAccept;
     @FXML
    private Label welcomeLabel;
     @FXML
     private Label checkForm;
     @FXML 
     private Label acceptLabel;

    @FXML
    private void submitButtonPressed(ActionEvent event) throws IOException, ClassNotFoundException, SQLException, MessagingException {
        if (termsAccept.isSelected()) {
            //alert user registration was succesfull
           
            String school = appLogic.findSchoolByAccountId();
            emails = appLogic.findEmailsBySchool(school);
            // Recipient's email ID needs to be mentioned.
            final String username = "unswexchange@gmail.com";
            final String password = "CocoMango";

            Properties props = new Properties();
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session;
            session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("unsexchange@gmail.com"));
                for (int i = 0; i < emails.size(); i++) {
                    email = emails.get(i);
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse(email));
                    message.setSubject("Testing Subject");
                    message.setText("Dear User,"
                            + "\n\n A new student application for exchange has been submitted."
                            + "\n To view the application please open the 'All Applications'"
                            + "\n section from your dashboard"
                            + "\n\n Thanks,"
                            + "/n UNSW Exchange Team");

                    Transport.send(message);
                }
                System.out.println("Email sent successfully");

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Your Registration has been successful");
            alert.showAndWait();
            stage = (Stage) submitButton.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("ApplyComplete.fxml"));

        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void backButtonPressed(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        stage = (Stage) submitButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("Courses.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void logoutPressed(ActionEvent event) throws IOException {
        stage = (Stage) submitButton.getScene().getWindow();
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



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       welcomeLabel.setText("Welcome "+ name);
       
         termsAccept.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    acceptLabel.setVisible(false);
                } else {
                    if (termsAccept.isSelected()) {
                        acceptLabel.setVisible(true);
                        acceptLabel.setStyle("-fx-text-fill: Red;");
                        acceptLabel.setText("You must accept the conditions to submit the application");
                    }
                }
            }
        });

    }

}
