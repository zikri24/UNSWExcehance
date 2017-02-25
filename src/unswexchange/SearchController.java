/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unswexchange;

import static dataAccess.AccountLogic.name;
import dataAccess.ApplicationLogic;
import static dataAccess.ApplicationLogic.applicationId;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Applic;
import model.Search;

/**
 * FXML Controller class
 *
 * @author ismmedina
 */
public class SearchController implements Initializable {

    private ApplicationLogic appLogic;
    private Applic application;
   

    public SearchController() {
        appLogic = new ApplicationLogic();
    }

    Stage stage = null;
    Parent root = null;

    @FXML
    private TableView<Applic> applicationsTable;
    @FXML
    private TableColumn<Applic, String> studentNoCell;
    @FXML
    private TableColumn<Applic, String> exchangeToCell;
    @FXML
    private TableColumn<Applic, Date> dateSubmittedCell;
    @FXML
    private TableColumn<Applic, String> statusCell;
    @FXML
    TableColumn viewColumn;

    @FXML
    private TextField firstNameText;
    @FXML
    private TextField lastNameText;
    @FXML
    private TextField studentIdText;
    @FXML
    private DatePicker fromPicker;
    @FXML
    private DatePicker toPicker;
    @FXML
    private ComboBox statusCombo;
    @FXML
    private Label welcomeLabel;
    @FXML
    private Button searchButton;

    @FXML
    private void searchButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        
        String firstName = firstNameText.getText();
        String lastName = lastNameText.getText();
         String studentId = studentIdText.getText();
        
        if (firstName.equals("")) {
            firstName = null;
        }
         if (lastName.equals("")) {
            lastName = null;
        }
          if (studentId.equals("")) {
            studentId = null;
        }      
       
        LocalDate localDateFrom = fromPicker.getValue();
        LocalDate localDateTo = toPicker.getValue();
        java.sql.Date toDate = null;
        java.sql.Date fromDate = null;
        if (localDateFrom == null) {
            fromDate = null;
        } else {
            fromDate = convertToSqlDate(fromPicker.getValue());
        }
        if (localDateTo == null) {
            toDate = null;
        } else {
            toDate = convertToSqlDate(toPicker.getValue());
        }
        String status;
        if (statusCombo.getValue() == null ||statusCombo.getValue().equals("Any") ) {
            status = null;
        } else {
            status = statusCombo.getValue().toString();
        }
        Search search = new Search(firstName, lastName, studentId, fromDate, toDate, status);
        ObservableList<Applic> aplications = null;
        try {
            aplications = FXCollections.observableArrayList(appLogic.searchApplications(search));
        } catch (SQLException ex) {
            Logger.getLogger(StudentMyApplicationsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        studentNoCell.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        exchangeToCell.setCellValueFactory(new PropertyValueFactory<>("uniName"));
        dateSubmittedCell.setCellValueFactory(new PropertyValueFactory<>("submitDate"));
        statusCell.setCellValueFactory(new PropertyValueFactory<>("status"));
        applicationsTable.setItems(aplications);
        
           viewColumn.setCellFactory(col -> {
            Button viewColumnButton = new Button("View");
            viewColumnButton.setStyle("-fx-base: grey;");
            TableCell<Applic, Applic> cell = new TableCell<Applic, Applic>() {
                @Override
                public void updateItem(Applic applic, boolean empty) {
                    super.updateItem(applic, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(viewColumnButton);
                    }
                }
            };
            viewColumnButton.setOnAction(e -> {

                 application = applicationsTable.getSelectionModel().getSelectedItem();
                if (application == null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Please Select an Application");
                    alert.show();
                    stage = (Stage) viewColumnButton.getScene().getWindow();
                    try {
                        //load up OTHER FXML document
                        root = FXMLLoader.load(getClass().getResource("OfficerMyApps.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(OfficerAllAppsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                ApplicationLogic.applicationId = application.getApplicationId();
                stage = (Stage) welcomeLabel.getScene().getWindow();
                try {
                    root = FXMLLoader.load(getClass().getResource("OfficerReviewApp.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(OfficerMyAppsController.class.getName()).log(Level.SEVERE, null, ex);
                }

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            });
            return cell;
        });
        
        
    }

    @FXML
    private void backButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) searchButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("OfficerDashboard.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void logoutPressed(ActionEvent event) throws IOException {
        stage = (Stage) searchButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
        welcomeLabel.setText("Welcom" + " " + name);
        ObservableList<String> status = FXCollections.observableArrayList("Accepted", "Declined", "Processing", "Pending", "Any");
        statusCombo.setItems(status);
    }

}
