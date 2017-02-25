/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unswexchange;

import com.itextpdf.text.BaseColor;
import static com.itextpdf.text.BaseColor.BLACK;
import static com.itextpdf.text.BaseColor.DARK_GRAY;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import static dataAccess.ApplicationLogic.applicationId;
import dataAccess.CourseLogic;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Course;
import model.CourseMatch;
import static unswexchange.CoursesController.courseId;

public class OfficerReviewCoursesMatchController implements Initializable {

    private CourseLogic courseLogic;
    public static int courseId;
    private CourseMatch courseMatch;
    File file;
    private String path;
    private Desktop desktop = Desktop.getDesktop();

    public OfficerReviewCoursesMatchController() {
        courseLogic = new CourseLogic();
    }

    Stage stage;
    Parent root;
    public static Stage secondarystage;
    //declare all needed GUI Components
    @FXML
    private TableView<Course> coursesTable;
    @FXML
    private TableColumn<Course, String> UNSWcourseCodeCell;
    @FXML
    private TableColumn<Course, String> foreignCourseCodeCell;
    @FXML
    private TableColumn<Course, String> statusCell;
    @FXML
    TableColumn actionColumn;
    @FXML
    TableColumn downloadOutlineColumn;
    @FXML
    TableColumn previouslyMatchedColumn;
    @FXML
    private Label welcomeLabel;
    @FXML
    private Button backButton;

    @FXML
    private void generalHyperPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) welcomeLabel.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("OfficerReviewApp.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void questionsHyperPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) welcomeLabel.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("OfficerReviewQuest.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void attachmentsHyperPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) welcomeLabel.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("OfficerReviewAttachments.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void reviewHyperPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        stage = (Stage) welcomeLabel.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("OfficerApplicationDecision.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void backButtonPressed(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        //scene switch to RegisterFormCompleteDet.FXML
        //get reference to the button's stage         
        stage = (Stage) welcomeLabel.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource("OfficerMyApps.fxml"));
        //create a new scene with root and set the stage
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

        // welcomeLabel.setText("Welcome " + name);
        //ArrayList<Applic> applications = appLogic.findApplicationsById();
        ObservableList<Course> courses = null;
        try {
            courses = FXCollections.observableArrayList(courseLogic.findCoursesByAppId(applicationId));
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StudentMyApplicationsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        UNSWcourseCodeCell.setCellValueFactory(new PropertyValueFactory<>("unswCode"));
        foreignCourseCodeCell.setCellValueFactory(new PropertyValueFactory<>("foreignCode"));
        statusCell.setCellValueFactory(new PropertyValueFactory<>("status"));
        coursesTable.setItems(courses);

        downloadOutlineColumn.setCellFactory(col -> {
            Button downloadButton = new Button("Download");
            downloadButton.setStyle("-fx-base: grey;");
            TableCell<Course, Course> cell = new TableCell<Course, Course>() {
                @Override
                public void updateItem(Course course, boolean empty) {
                    super.updateItem(course, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(downloadButton);
                    }
                }
            };
            downloadButton.setOnAction((ActionEvent e) -> {
                int feedback = 0;
                Course course = coursesTable.getSelectionModel().getSelectedItem();

                if (course == null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Please Select Course");
                    alert.show();
                    stage = (Stage) welcomeLabel.getScene().getWindow();
                    try {
                        //load up OTHER FXML document
                        root = FXMLLoader.load(getClass().getResource("OfficerReviewCoursesMatch.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(OfficerAllAppsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    courseId = course.getCourseId();

                    try {
                        path = courseLogic.downloadCourseOutline(courseId);
                        if (path == null) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Information Dialog");
                            alert.setHeaderText(null);
                            alert.setContentText("No Files attached!");
                            alert.show();
                            stage = (Stage) welcomeLabel.getScene().getWindow();
                            //load up OTHER FXML document
                            root = FXMLLoader.load(getClass().getResource("OfficerReviewAttachments.fxml"));
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                        }
                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(OfficerReviewAttachmentsController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(OfficerReviewAttachmentsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    file = new File(path);
                    try {
                        desktop.open(file);
                    } catch (IOException ex) {
                        Logger.getLogger(OfficerReviewAttachmentsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            return cell;
        });

        previouslyMatchedColumn.setCellFactory((Object col) -> {

            Button createPDFButton = new Button("Download PDF");
            createPDFButton.setStyle("-fx-base: grey;");
            TableCell<Course, Course> cell = new TableCell<Course, Course>() {
                @Override
                public void updateItem(Course course, boolean empty) {
                    super.updateItem(course, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(createPDFButton);
                    }
                }
            };
            createPDFButton.setOnAction(new EventHandler<ActionEvent>() {

                public void handle(ActionEvent e) {
                    int feedback = 0;
                    Course course = coursesTable.getSelectionModel().getSelectedItem();

                    String foreignCode = course.getForeignCode();
                    String unswCode = course.getUnswCode();
                    try {
                        courseMatch = courseLogic.findCourseMatchDetails(foreignCode, unswCode);
                        if (courseMatch != null) {
                            String foreignCourseCode = courseMatch.getForeignCode();
                            String foreignName = courseMatch.getForeignName();
                            String unswCourseCode = courseMatch.getUnswCode();
                            String unswName = courseMatch.getUnswName();
                            String status = courseMatch.getStatus();
                            java.sql.Date dateReviewed = courseMatch.getDateReviewed();
                            String date = dateReviewed.toString();
                            String firstName = courseMatch.getFirstName();
                            String lastName = courseMatch.getFirstName();
                            String uniName = courseMatch.getUniName();
                            Document document = new Document();

                            File courseMatchFile = new File("courseMatchFile.pdf");

                            //write data to the file
                            FileOutputStream pdfFileout = new FileOutputStream(courseMatchFile);
                            PdfWriter.getInstance(document, pdfFileout);

                            //open the document
                            document.open();
                            //add data to the document
                            Paragraph pageTitle = new Paragraph();
                            pageTitle.setAlignment(Element.ALIGN_CENTER);
                            pageTitle.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, DARK_GRAY));
                            pageTitle.add("Course Match History");

                            // a table with two columns
                            PdfPTable table = new PdfPTable(2);
                            PdfPCell cell = new PdfPCell();

                            //Space
                            cell = new PdfPCell();
                            cell.setColspan(4);
                            cell.setFixedHeight(20f);
                            cell.setBorderColor(BaseColor.WHITE);
                            table.addCell(cell);

                            //Space
                            cell = new PdfPCell();
                            cell.setColspan(4);
                            cell.setFixedHeight(20f);
                            cell.setBorderColor(BaseColor.WHITE);
                            table.addCell(cell);

                            //Table Header
                            Font tableHeader = new Font();
                            tableHeader.setColor(BaseColor.WHITE);

                            tableHeader.setSize(12);

                            //Table Info
                            Font tableInfo = new Font();
                            tableInfo.setColor(BaseColor.DARK_GRAY);
                            tableInfo.setSize(12);

                            cell = new PdfPCell(new Phrase("Foreign Course ", tableHeader));
                            cell.setFixedHeight(20f);
                            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            cell.setVerticalAlignment(Element.ALIGN_CENTER);
                            cell.setBorderColor(BaseColor.BLACK);
                            cell.setBackgroundColor(BaseColor.DARK_GRAY);
                            table.addCell(cell);

                            cell = new PdfPCell(new Phrase(foreignCourseCode + ": " + foreignName, tableInfo));
                            cell.setFixedHeight(20f);
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cell.setVerticalAlignment(Element.ALIGN_CENTER);
                            cell.setBorderColor(BaseColor.BLACK);
                            table.addCell(cell);

                            cell = new PdfPCell(new Phrase("UNSW Course ", tableHeader));
                            cell.setFixedHeight(20f);
                            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            cell.setVerticalAlignment(Element.ALIGN_CENTER);
                            cell.setBorderColor(BaseColor.BLACK);
                            cell.setBackgroundColor(BaseColor.DARK_GRAY);
                            table.addCell(cell);

                            cell = new PdfPCell(new Phrase(unswCourseCode + ": " + unswName, tableInfo));
                            cell.setFixedHeight(20f);
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cell.setVerticalAlignment(Element.ALIGN_CENTER);
                            cell.setBorderColor(BaseColor.BLACK);
                            table.addCell(cell);

                            cell = new PdfPCell(new Phrase("University ", tableHeader));
                            cell.setFixedHeight(20f);
                            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            cell.setVerticalAlignment(Element.ALIGN_CENTER);
                            cell.setBorderColor(BaseColor.BLACK);
                            cell.setBackgroundColor(BaseColor.DARK_GRAY);
                            table.addCell(cell);

                            cell = new PdfPCell(new Phrase(uniName, tableInfo));
                            cell.setFixedHeight(20f);
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cell.setVerticalAlignment(Element.ALIGN_CENTER);
                            cell.setBorderColor(BaseColor.BLACK);
                            table.addCell(cell);

                            cell = new PdfPCell(new Phrase("Previously Matched Status ", tableHeader));
                            cell.setFixedHeight(20f);
                            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            cell.setVerticalAlignment(Element.ALIGN_CENTER);
                            cell.setBorderColor(BaseColor.BLACK);
                            cell.setBackgroundColor(BaseColor.DARK_GRAY);
                            table.addCell(cell);

                            Font accepted = new Font();
                            accepted.setColor(BaseColor.GREEN);
                            accepted.setStyle("bold");
                            accepted.setSize(12);

                            Font acceptedCondition = new Font();
                            acceptedCondition.setColor(BaseColor.ORANGE);
                            accepted.setStyle("bold");
                            acceptedCondition.setSize(12);

                            Font declined = new Font();
                            declined.setColor(BaseColor.RED);
                            accepted.setStyle("bold");
                            declined.setSize(12);

                            if (status.equals("Accepted")) {
                                cell = new PdfPCell(new Phrase(status, accepted));
                                cell.setFixedHeight(20f);
                                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                cell.setVerticalAlignment(Element.ALIGN_CENTER);
                                cell.setBorderColor(BaseColor.BLACK);
                                table.addCell(cell);
                            } else if (status.equals("Accepted on Condition")) {
                                cell = new PdfPCell(new Phrase(status, acceptedCondition));
                                cell.setFixedHeight(20f);
                                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                cell.setVerticalAlignment(Element.ALIGN_CENTER);
                                cell.setBorderColor(BaseColor.BLACK);
                                table.addCell(cell);

                            } else {

                                cell = new PdfPCell(new Phrase(status, declined));
                                cell.setFixedHeight(20f);
                                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                cell.setVerticalAlignment(Element.ALIGN_CENTER);
                                cell.setBorderColor(BaseColor.BLACK);
                                table.addCell(cell);

                            }

                            cell = new PdfPCell(new Phrase("Exchange Officer ", tableHeader));
                            cell.setFixedHeight(20f);
                            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            cell.setVerticalAlignment(Element.ALIGN_CENTER);
                            cell.setBorderColor(BaseColor.BLACK);
                            cell.setBackgroundColor(BaseColor.DARK_GRAY);
                            table.addCell(cell);

                            cell = new PdfPCell(new Phrase(firstName + " " + lastName, tableInfo));
                            cell.setFixedHeight(20f);
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cell.setVerticalAlignment(Element.ALIGN_CENTER);
                            cell.setBorderColor(BaseColor.BLACK);
                            table.addCell(cell);

                            cell = new PdfPCell(new Phrase("Review Date ", tableHeader));
                            cell.setFixedHeight(20f);
                            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            cell.setVerticalAlignment(Element.ALIGN_CENTER);
                            cell.setBorderColor(BaseColor.BLACK);
                            cell.setBackgroundColor(BaseColor.DARK_GRAY);
                            table.addCell(cell);

                            cell = new PdfPCell(new Phrase(date, tableInfo));
                            cell.setFixedHeight(20f);
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cell.setVerticalAlignment(Element.ALIGN_CENTER);
                            cell.setBorderColor(BaseColor.BLACK);
                            table.addCell(cell);

                            //add the title to the document
                            document.add(pageTitle);
                            document.add(table);

                            document.close();
                            pdfFileout.close();
                            try {
                                desktop.open(courseMatchFile);
                            } catch (IOException ex) {
                                Logger.getLogger(OfficerReviewAttachmentsController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Information Dialog");
                            alert.setHeaderText(null);
                            alert.setContentText("These courses haven't been matched before");
                            alert.show();
                        }
                    } catch (SQLException | ClassNotFoundException | DocumentException ex) {
                        Logger.getLogger(OfficerReviewCoursesMatchController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(OfficerReviewCoursesMatchController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(OfficerReviewCoursesMatchController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            return cell;
        });
        actionColumn.setCellFactory(col -> {
            Button actionButton = new Button("Review");
            actionButton.setStyle("-fx-base: #2fa518;");
            TableCell<Course, Course> cell = new TableCell<Course, Course>() {
                @Override
                public void updateItem(Course course, boolean empty) {
                    super.updateItem(course, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(actionButton);
                    }
                }
            };
            actionButton.setOnAction((ActionEvent e) -> {
                int feedback = 0;
                Course course = coursesTable.getSelectionModel().getSelectedItem();
                if (course == null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Please Select Course");
                    alert.show();
                    stage = (Stage) welcomeLabel.getScene().getWindow();
                    try {
                        //load up OTHER FXML document
                        root = FXMLLoader.load(getClass().getResource("OfficerReviewCoursesMatch.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(OfficerAllAppsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    CourseLogic.courseId = course.getCourseId();
                    stage = new Stage();
                    stage.setTitle("Review Course");
                    stage.getIcons().add(new Image("Images/icon.png"));
                    try {
                        //load up OTHER FXML document
                        root = FXMLLoader.load(getClass().getResource("OfficerCourseDecision.fxml"));
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

    }

}


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
