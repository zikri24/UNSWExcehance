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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Applic;

/**
 * FXML Controller class
 *
 * @author Zikri
 */
public class OfficerMyAppsController implements Initializable {

    private final ApplicationLogic appLogic;
    //private Applic application;
    private int applicationNo;

    public OfficerMyAppsController() {
        appLogic = new ApplicationLogic();
    }

    Stage stage;
    Parent root;
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
    TableColumn viewColumn ;
    @FXML
    TableColumn unassignColumn ;
    @FXML
    private Button backButton;
    @FXML
    private Label welcomeLabel;
     @FXML
    private ComboBox sortByCombo;

    /**
     * Initializes the controller class.
     */
    @FXML
    private void backButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        //scene switch to RegisterFormCompleteDet.FXML
        //get reference to the button's stage         
        stage = (Stage) backButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("OfficerDashboard.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
      @FXML
    private void myDashPressed(ActionEvent event) throws IOException {
        stage = (Stage) welcomeLabel.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("OfficerDashboard.fxml"));
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

    @FXML
    private void logoutPressed(ActionEvent event) throws IOException {
        stage = (Stage) backButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void sortPressed(ActionEvent event) throws IOException {
        String sortBy = sortByCombo.getValue().toString();
        ObservableList<Applic> aplications = null;
        try {
            aplications = FXCollections.observableArrayList(appLogic.sortAppsByStaffId(sortBy));
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StudentMyApplicationsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        studentNoCell.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        exchangeToCell.setCellValueFactory(new PropertyValueFactory<>("uniName"));
        dateSubmittedCell.setCellValueFactory(new PropertyValueFactory<>("submitDate"));
        statusCell.setCellValueFactory(new PropertyValueFactory<>("status"));
        applicationsTable.setItems(aplications);
        
        
        unassignColumn.setCellFactory(col -> {
            Button unassignButton = new Button("Unassign");
            unassignButton.setStyle("-fx-base: #2fa518;");
            TableCell<Applic, Applic> cell = new TableCell<Applic, Applic>() {
                @Override
                public void updateItem(Applic applic, boolean empty) {
                    super.updateItem(applic, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(unassignButton);
                    }
                }
            };
            unassignButton.setOnAction((ActionEvent e) -> {
                int feedback = 0;
                Applic application = applicationsTable.getSelectionModel().getSelectedItem();

                if (application == null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Please Select an Application");
                    alert.show();
                    stage = (Stage) unassignButton.getScene().getWindow();
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
                applicationNo = application.getApplicationId();
                try {
                    feedback = appLogic.unAssignApp(applicationNo);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(OfficerAllAppsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (feedback > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("The Application has been successfully unassigned");
                    alert.show();
                }
                stage = (Stage) unassignButton.getScene().getWindow();
                try {
                    //load up OTHER FXML document
                    root = FXMLLoader.load(getClass().getResource("OfficerMyApps.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(OfficerAllAppsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            });
            return cell;
        });

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

                Applic application = applicationsTable.getSelectionModel().getSelectedItem();
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
                applicationId = application.getApplicationId();
                stage = (Stage) backButton.getScene().getWindow();
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
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        welcomeLabel.setText("Welcom" + " " + name);
        ObservableList<Applic> aplications = null;
        try {
            aplications = FXCollections.observableArrayList(appLogic.findAppsByStaffId());
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StudentMyApplicationsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        studentNoCell.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        exchangeToCell.setCellValueFactory(new PropertyValueFactory<>("uniName"));
        dateSubmittedCell.setCellValueFactory(new PropertyValueFactory<>("submitDate"));
        statusCell.setCellValueFactory(new PropertyValueFactory<>("status"));
        applicationsTable.setItems(aplications);
        
        
        unassignColumn.setCellFactory(col -> {
            Button unassignButton = new Button("Unassign");
            unassignButton.setStyle("-fx-base: #2fa518;");
            TableCell<Applic, Applic> cell = new TableCell<Applic, Applic>() {
                @Override
                public void updateItem(Applic applic, boolean empty) {
                    super.updateItem(applic, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(unassignButton);
                    }
                }
            };
            unassignButton.setOnAction((ActionEvent e) -> {
                int feedback = 0;
                Applic application = applicationsTable.getSelectionModel().getSelectedItem();

                if (application == null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Please Select an Application");
                    alert.show();
                    stage = (Stage) unassignButton.getScene().getWindow();
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
                applicationNo = application.getApplicationId();
                try {
                    feedback = appLogic.unAssignApp(applicationNo);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(OfficerAllAppsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (feedback > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("The Application has been successfully unassigned");
                    alert.show();
                }
                stage = (Stage) unassignButton.getScene().getWindow();
                try {
                    //load up OTHER FXML document
                    root = FXMLLoader.load(getClass().getResource("OfficerMyApps.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(OfficerAllAppsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            });
            return cell;
        });

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

                Applic application = applicationsTable.getSelectionModel().getSelectedItem();
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
                applicationId = application.getApplicationId();
                stage = (Stage) backButton.getScene().getWindow();
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
        ObservableList<String> status = FXCollections.observableArrayList("Processing", "Accepted", "Declined"
        );
        //set comboBox components
        sortByCombo.setItems(status);
    }

}
