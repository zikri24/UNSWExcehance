/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unswexchange;

import static dataAccess.AccountLogic.name;
import static dataAccess.ApplicationLogic.applicationId;
import dataAccess.CourseLogic;
import static dataAccess.CourseLogic.courseId;
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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Applic;
import model.Course;

/**
 * FXML Controller class
 *
 * @author ismmedina
 */
public class AppCoursesEditController implements Initializable {

    private CourseLogic courseLogic;
    

    public AppCoursesEditController() {
        courseLogic = new CourseLogic();
    }

    Stage stage;
    Parent root;
    //declare all needed GUI Components
    @FXML
    private TableView<Course> coursesTable;
    @FXML
    private TableColumn<Course, String> courseCodeCell;
    @FXML
    private TableColumn<Course, String> courseNameCell;
    @FXML
    private TableColumn<Course, String> foreignCcourseCodeCell;
    @FXML
    private TableColumn<Course, String> foreignCcourseNameCell;

    @FXML
    private Button backButton;
    @FXML
    private Button addCourseButton;
   // @FXML
    //TableColumn editColumn = new TableColumn("");
    @FXML
    TableColumn removeColumn;

    @FXML
    private Label welcomeLabel;
    @FXML
    private ImageView logoImageView;

    @FXML
    private void addCourseButtonPressed(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        stage = (Stage) addCourseButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("EditCoursesAdd.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void backButtonPressed(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        stage = (Stage) addCourseButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("StudentMyApplications.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void submitPressed(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        stage = (Stage) addCourseButton.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("ApplicationQuestions.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
    
     @FXML
    private void generalHyperPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) backButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("AppGenUpdate.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
     @FXML
    private void attachmentsHyperPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) backButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("AppAttachmentsEdit.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
     @FXML
    private void questionsHyperPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) backButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("AppQuestionsEdit.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
     @FXML
    private void commentsHyperPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) backButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("AppCommentReview.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void logoutPressed(ActionEvent event) throws IOException {
        stage = (Stage) welcomeLabel.getScene().getWindow();
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
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         welcomeLabel.setText("Welcome " + name);

        //ArrayList<Applic> applications = appLogic.findApplicationsById();
        ObservableList<Course> courses = null;
        try {
            courses = FXCollections.observableArrayList(courseLogic.findCoursesByAppId(applicationId));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentMyApplicationsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StudentMyApplicationsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        courseCodeCell.setCellValueFactory(new PropertyValueFactory<>("unswCode"));
        courseNameCell.setCellValueFactory(new PropertyValueFactory<>("unswName"));
        foreignCcourseCodeCell.setCellValueFactory(new PropertyValueFactory<>("foreignCode"));
        foreignCcourseNameCell.setCellValueFactory(new PropertyValueFactory<>("foreignName"));
        coursesTable.setItems(courses);
        //coursesTable.getColumns().addAll(editColumn, removeColumn);
        // coursesTable.getColumns().addAll(editColumn, removeColumn);
/*
        editColumn.setCellFactory(col -> {
            Button editButton = new Button("Edit");
            editButton.setStyle("-fx-base: #2fa518;");
            TableCell<Course, Course> cell = new TableCell<Course, Course>() {
                @Override
                public void updateItem(Course course, boolean empty) {
                    super.updateItem(course, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(editButton);
                    }
                }
            };
            editButton.setOnAction((ActionEvent e) -> {
                int feedback = 0;
                Course course = coursesTable.getSelectionModel().getSelectedItem();

                if (course == null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Please Select Course");
                    alert.show();
                    stage = (Stage) editButton.getScene().getWindow();
                    try {
                        //load up OTHER FXML document
                        root = FXMLLoader.load(getClass().getResource("AppCoursesEdit.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(OfficerAllAppsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    courseId = course.getCourseId();
                    stage = (Stage) editButton.getScene().getWindow();
                    try {
                        //load up OTHER FXML document
                        root = FXMLLoader.load(getClass().getResource("CourseEdit_1.fxml"));
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
*/
        removeColumn.setCellFactory(col -> {
            Button removeButton = new Button("Remove");
            removeButton.setStyle("-fx-base: #2fa518;");
            TableCell<Course, Course> cell = new TableCell<Course, Course>() {
                @Override
                public void updateItem(Course course, boolean empty) {
                    super.updateItem(course, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(removeButton);
                    }
                }
            };
            removeButton.setOnAction((ActionEvent e) -> {
                int feedback = 0;
                Course course = coursesTable.getSelectionModel().getSelectedItem();

                if (course == null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Please Select Course");
                    alert.show();
                    stage = (Stage) removeButton.getScene().getWindow();
                    try {
                        //load up OTHER FXML document
                        root = FXMLLoader.load(getClass().getResource("AppCoursesEdit.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(OfficerAllAppsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    courseId = course.getCourseId();
                    try {
                        feedback = courseLogic.removeCourse(courseId);
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(CoursesController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (feedback > 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Course has been removed");
                        alert.show();
                    }
                    stage = (Stage) removeButton.getScene().getWindow();
                    try {
                        //load up OTHER FXML document
                        root = FXMLLoader.load(getClass().getResource("AppCoursesEdit.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(CoursesController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            });
            return cell;
        });
/*
        coursesTable.setRowFactory(tv -> {
            TableRow<Course> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Course rowData = row.getItem();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("The Course Number is " + rowData.getCourseId());
                    alert.show();
                    System.out.println(rowData.getCourseId());
                }
            });
            return row;
        });
        */
    }
}
