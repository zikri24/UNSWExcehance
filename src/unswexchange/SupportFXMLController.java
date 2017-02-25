/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unswexchange;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author Zikri
 */
public class SupportFXMLController implements Initializable {
    
     Stage stage;
    Parent root;

    @FXML
    private ComboBox catCombo;
    @FXML
    private TextField emailText;
    @FXML
    private TextArea messageText;

    /**
     * Initializes the controller class.
     */
    @FXML
    private void sendButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        String emailMessage = messageText.getText();
        String senderEmail = emailText.getText();
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
                    InternetAddress.parse(senderEmail));
            message.setSubject("Testing Subject");
            message.setText(emailMessage);
            Transport.send(message);
            System.out.println("Email sent successfully");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("Your Message has been sent");
        alert.showAndWait();

    }
    
     @FXML
    private void logoutPressed(ActionEvent event) throws IOException {
        stage = (Stage) catCombo.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("StudentDashboard_1.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> Category = FXCollections.observableArrayList("Scholarships", "Application", "Travel", "Application Error", "Other"
        );

        catCombo.setItems(Category);
    }
}
