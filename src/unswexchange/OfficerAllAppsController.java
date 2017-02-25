/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unswexchange;

import static dataAccess.AccountLogic.name;
import dataAccess.ApplicationLogic;
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
public class OfficerAllAppsController implements Initializable {

    private final ApplicationLogic appLogic;
    //private Applic application;
    private int applicationNo;
    private int feedback;

    public OfficerAllAppsController() {
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
    TableColumn assignToMeColumn; 

    @FXML
    private Button backButton;
    @FXML
    private Label welcomeLabel;
    @FXML
    private Button assignButton;

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
    private void logoutPressed(ActionEvent event) throws IOException {
        stage = (Stage) backButton.getScene().getWindow();
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
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        welcomeLabel.setText("Welcom" + " " + name);
        ObservableList<Applic> aplications = null;
        try {
            aplications = FXCollections.observableArrayList(appLogic.findPendingApplications());
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StudentMyApplicationsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        studentNoCell.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        exchangeToCell.setCellValueFactory(new PropertyValueFactory<>("uniName"));
        dateSubmittedCell.setCellValueFactory(new PropertyValueFactory<>("submitDate"));
        statusCell.setCellValueFactory(new PropertyValueFactory<>("status"));
        applicationsTable.setItems(aplications);
        

        assignToMeColumn.setCellFactory(col -> {
            Button assignToMeButton = new Button("Assign to Me");
            assignToMeButton.setStyle("-fx-text-fill: white;-fx-base: #54c910;");
            TableCell<Applic, Applic> cell = new TableCell<Applic, Applic>() {
                @Override
                public void updateItem(Applic applic, boolean empty) {
                    super.updateItem(applic, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(assignToMeButton);
                    }
                }

            };
            assignToMeButton.setOnAction(e -> {
                Applic application = applicationsTable.getSelectionModel().getSelectedItem();
                if (application == null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Please Select an Application");
                    alert.show();
                    stage = (Stage) assignButton.getScene().getWindow();
                    try {
                        //load up OTHER FXML document
                        root = FXMLLoader.load(getClass().getResource("OfficerAllApps.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(OfficerAllAppsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                }
                applicationNo = application.getApplicationId();
                try {
                    feedback = appLogic.assignAppToMe(applicationNo);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(OfficerAllAppsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (feedback > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("The Application has been successfully assigned to you");
                    alert.show();
                }
                stage = (Stage) assignButton.getScene().getWindow();
                try {
                    //load up OTHER FXML document
                    root = FXMLLoader.load(getClass().getResource("OfficerAllApps.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(OfficerAllAppsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            });
            /*
             assignButton.setOnAction(e -> {
             Applic application = applicationsTable.getSelectionModel().getSelectedItem();
             if (application == null) {
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Information Dialog");
             alert.setHeaderText(null);
             alert.setContentText("Please Seiect an Application");
             alert.show();
             stage = (Stage) assignButton.getScene().getWindow();
             try {
             //load up OTHER FXML document
             root = FXMLLoader.load(getClass().getResource("OfficerAllApps.fxml"));
             } catch (IOException ex) {
             Logger.getLogger(OfficerAllAppsController.class.getName()).log(Level.SEVERE, null, ex);
             }
             Scene scene = new Scene(root);
             stage.setScene(scene);
             stage.show();

             }
             applicationNo = application.getApplicationId();
             try {
             feedback = appLogic.assignAppToMe(applicationNo);
             } catch (ClassNotFoundException | SQLException ex) {
             Logger.getLogger(OfficerAllAppsController.class.getName()).log(Level.SEVERE, null, ex);
             }
             if (feedback > 0) {
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Information Dialog");
             alert.setHeaderText(null);
             alert.setContentText("The Application has been successfully assigned to you");
             alert.show();
             }
             stage = (Stage) assignButton.getScene().getWindow();
             try {
             //load up OTHER FXML document
             root = FXMLLoader.load(getClass().getResource("OfficerAllApps.fxml"));
             } catch (IOException ex) {
             Logger.getLogger(OfficerAllAppsController.class.getName()).log(Level.SEVERE, null, ex);
             }
             Scene scene = new Scene(root);
             stage.setScene(scene);
             stage.show();

             });
             /*
             applicationsTable.setRowFactory(tv -> {
             TableRow<Applic> row = new TableRow<>();
             row.setOnMouseClicked(event -> {

             assignToMeButton.setOnAction(e -> {
             Applic rowData = row.getItem();
             applicationNo = rowData.getApplicationId();
             try {
             feedback = appLogic.assignAppToMe(applicationNo);
             } catch (ClassNotFoundException | SQLException ex) {
             Logger.getLogger(OfficerAllAppsController.class.getName()).log(Level.SEVERE, null, ex);
             }
             if (feedback > 0) {
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Information Dialog");
             alert.setHeaderText(null);
             alert.setContentText("The Application has been successfully assigned to you");
             alert.show();
             }

             });

             });
             return row;
             });
             */
            /*   assignToMeButton.setOnAction(e -> {

             //applicationsTable.setRowFactory(tv -> {
             TableRow<Applic> row = new TableRow<>();
             Applic rowData = row.getItem();
             applicationNo = rowData.getApplicationId();
             // return row;
             // });
             try {
             feedback = appLogic.assignAppToMe(applicationNo);
             } catch (ClassNotFoundException | SQLException ex) {
             Logger.getLogger(OfficerAllAppsController.class.getName()).log(Level.SEVERE, null, ex);
             }
             if (feedback > 0) {
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Information Dialog");
             alert.setHeaderText(null);
             alert.setContentText("The Application has been successfully assigned to you");
             alert.show();
             }
             });*/
             return cell;
             });

            /*

             applicationsTable.setRowFactory(tv -> {
             TableRow<Applic> row = new TableRow<>();
             row.setOnMouseClicked(event -> {
             if (event.getClickCount() == 2 && (!row.isEmpty())) {
             Applic rowData = row.getItem();
             applicationId = rowData.getApplicationId();

             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Information Dialog");
             alert.setHeaderText(null);
             alert.setContentText("The Application Number is " + rowData.getApplicationId());
             alert.show();

             System.out.println(rowData.getApplicationId());
             }
             });
             return row;
             });
             */
        }
    }
