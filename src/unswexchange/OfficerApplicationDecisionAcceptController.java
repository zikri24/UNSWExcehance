/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unswexchange;

import dataAccess.ApplicationLogic;
import static dataAccess.ApplicationLogic.applicationId;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Applic;
import model.Profile;

/**
 * FXML Controller class
 *
 * @author ismmedina
 */
public class OfficerApplicationDecisionAcceptController implements Initializable {

    private ApplicationLogic appLogic;
    private Applic application;

    private Profile profile;

    public OfficerApplicationDecisionAcceptController() {
        appLogic = new ApplicationLogic();
    }

    Stage stage = null;
    Stage decisionStage;
    Parent root = null;

    @FXML
    private Label nameLabel;
    @FXML
    private Label uniLabel;
    @FXML
    private Label startDateLabel;
    @FXML
    private Label endDateLabel;
    @FXML
    private Button cancelButton;
    @FXML
    private Button submitDecisionButton;

    @FXML
    private void submitButtonPressed(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {

        int feedback;
        feedback = appLogic.acceptApplication(applicationId);

        if (feedback > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Application has been Accepted");
            alert.show();
            stage = (Stage) submitDecisionButton.getScene().getWindow();
            stage.close();
            String email = appLogic.findEmailByAppliocationId(applicationId);
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

                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(email));
                message.setSubject("Testing Subject");
                message.setText("Dear Student,"
                        + "\n\n Your exchange application has been approved.\n Please login to your account for more details.");
                Transport.send(message);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Something went wrong");
            alert.show();
        }

    }

    @FXML
    private void cancelButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //welcomeLabel.setText("Welcom" + " " + name);
        try {
            profile = appLogic.findProfileByAppId(applicationId);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OfficerReviewAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
        nameLabel.setText(profile.getFirstName() + " " + profile.getLastName());
        uniLabel.setText(profile.getUniName());
        startDateLabel.setText(String.valueOf(profile.getStartDate()));
        endDateLabel.setText(String.valueOf(profile.getFinishDate()));

    }

}
