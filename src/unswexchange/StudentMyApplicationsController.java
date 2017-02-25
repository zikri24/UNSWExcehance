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
import static dataAccess.AccountLogic.name;
import dataAccess.ApplicationLogic;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Applic;

/**
 * FXML Controller class
 *
 * @author CocoMango
 */
public class StudentMyApplicationsController implements Initializable {

    private final ApplicationLogic appLogic;
    private Applic application;
    private int applicationId;

    public StudentMyApplicationsController() {
        appLogic = new ApplicationLogic();
    }

    Stage stage;
    Parent root;
    //declare all needed GUI Components
    @FXML
    private TableView<Applic> applicationsTable;
    @FXML
    private TableColumn<Applic, String> exchangeToCell;
    @FXML
    private TableColumn<Applic, Date> dateSubmittedCell;
    @FXML
    private TableColumn<Applic, String> statusCell;
    @FXML
    TableColumn editColumn;
    @FXML
    TableColumn withdrawColumn;

    @FXML
    private Button backButton;
    @FXML
    private Label welcomeLabel;

    @FXML
    private void backButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        //scene switch to RegisterFormCompleteDet.FXML
        //get reference to the button's stage         
        stage = (Stage) backButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("StudentDashboard_1.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void submitButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) backButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("StudentApply.fxml"));
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

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        welcomeLabel.setText("Welcome " + name);
        //ArrayList<Applic> applications = appLogic.findApplicationsById();
        ObservableList<Applic> aplications = null;
        try {
            aplications = FXCollections.observableArrayList(appLogic.findApplicationsById());
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StudentMyApplicationsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        exchangeToCell.setCellValueFactory(new PropertyValueFactory<>("uniName"));
        dateSubmittedCell.setCellValueFactory(new PropertyValueFactory<>("submitDate"));
        statusCell.setCellValueFactory(new PropertyValueFactory<>("status"));
        applicationsTable.setItems(aplications);

        withdrawColumn.setCellFactory(col -> {
            Button withdrawButton = new Button("Withdraw");
            withdrawButton.setStyle("-fx-base: red;");
            TableCell<Applic, Applic> cell = new TableCell<Applic, Applic>() {
                @Override
                public void updateItem(Applic applic, boolean empty) {
                    super.updateItem(applic, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(withdrawButton);
                    }
                }
            };
            withdrawButton.setOnAction(e -> {
                int feedback = 0;
                application = applicationsTable.getSelectionModel().getSelectedItem();

                if (application == null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Please Select Application");
                    alert.show();
                    stage = (Stage) withdrawButton.getScene().getWindow();
                    try {
                        //load up OTHER FXML document
                        root = FXMLLoader.load(getClass().getResource("StudentMyApplications.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(OfficerAllAppsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure you would like to witdraw this application?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        ApplicationLogic.applicationId = application.getApplicationId();
                        try {
                            appLogic.deleteByAppliocationId(ApplicationLogic.applicationId);

                        } catch (ClassNotFoundException | SQLException ex) {
                            Logger.getLogger(StudentMyApplicationsController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        alert.close();
                    }
                    stage = (Stage) withdrawButton.getScene().getWindow();
                    try {
                        //load up OTHER FXML document
                        root = FXMLLoader.load(getClass().getResource("StudentMyApplications.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(StudentMyApplicationsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            });
            return cell;
        });

        editColumn.setCellFactory(col -> {
            Button editButton = new Button("View/Edit");
            editButton.setStyle("-fx-base: green;");
            TableCell<Applic, Applic> cell = new TableCell<Applic, Applic>() {
                @Override
                public void updateItem(Applic applic, boolean empty) {
                    super.updateItem(applic, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(editButton);
                    }
                }
            };
            editButton.setOnAction(e -> {

                int feedback = 0;
                application = applicationsTable.getSelectionModel().getSelectedItem();

                if (application == null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Please Select Application");
                    alert.show();
                    stage = (Stage) editButton.getScene().getWindow();
                    try {
                        //load up OTHER FXML document
                        root = FXMLLoader.load(getClass().getResource("StudentMyApplications.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(OfficerAllAppsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    ApplicationLogic.applicationId = application.getApplicationId();
                    stage = (Stage) editButton.getScene().getWindow();
                    try {
                        //load up OTHER FXML document
                        root = FXMLLoader.load(getClass().getResource("AppGenUpdate.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(OfficerAllAppsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            });
            return cell;
        });
        //actionCell.setCellValueFactory(new PropertyValueFactory<>(new Button()));
        /*
         applicationsTable.setItems(aplications);

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
