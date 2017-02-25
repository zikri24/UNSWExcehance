/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unswexchange;

import dataAccess.AccountLogic;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Account;

/**
 * FXML Controller class
 *
 * @author Zikri
 */
public class ForgotPasswordController implements Initializable {

    private AccountLogic accountLogic;
    Stage stage;
    Parent root;

    public ForgotPasswordController() {
        accountLogic = new AccountLogic();
    }

    @FXML
    private TextField zIdText;
    @FXML
    private Button sendButton;

    @FXML
    private void sendButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        String zId = zIdText.getText().toLowerCase();
        Account account = accountLogic.findPassword(zId);
        if (account != null) {
            String email = account.getEmail();
            String studentPassword = account.getPassword();

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
                message.setSubject("UNSW Exchange");
                message.setText("Dear Student,"
                        + "\n\n Your Password is \n" + studentPassword + "\n UNSW Student Exchange Office");
                Transport.send(message);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Forgot your password");
            alert.setHeaderText(null);
            alert.setContentText("An  email has been sent to you");
            alert.show();
            stage = (Stage) zIdText.getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Forgot your password");
            alert.setHeaderText(null);
            alert.setContentText("We couldn't find your account!");
            alert.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
