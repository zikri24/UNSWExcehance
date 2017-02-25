/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unswexchange;

import dataAccess.AccountLogic;
import dataAccess.ApplicationLogic;
import static dataAccess.ApplicationLogic.applicationId;
import dataAccess.LogsLogic;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Applic;
import model.Log;
import model.Profile;

/**
 * FXML Controller class
 *
 * @author ismmedina
 */
public class OfficerLogController implements Initializable {

    private final LogsLogic logsLogic;

    public OfficerLogController() {
        logsLogic = new LogsLogic();
    }

    Stage stage = null;
    Parent root = null;

    @FXML
    private TableView<Log> logssTable;
    @FXML
    private TableColumn<Log, String> descriptionColumn;
    @FXML
    private TableColumn<Log, Date> dateColumn;
    @FXML
    private TableColumn<Applic, String> nameColumn;
    @FXML
    private Button cancelButton;

    @FXML
    private void cancelButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<Log> logs = null;
        try {
            logs = FXCollections.observableArrayList(logsLogic.findLogsByAppId(applicationId));
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StudentMyApplicationsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));        
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("logDate"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        logssTable.setItems(logs);

    }

}
