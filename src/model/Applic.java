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
package model;

//list all imports
import java.io.FileInputStream;
import java.time.LocalDate;
import java.sql.Date;

/**
 * Application class
 *
 * @author CocoMango
 */
public class Applic {

    //Declare Application variables
    private int applicationId;
    private String studentId;
    private int accountId;
    private String uniName;
    private String uniCountry;
    private java.sql.Date startDate;
    private java.sql.Date finishDate;
    private boolean unswPartner;
    private boolean creditTransferToUnsw;
    private String supportQues;
    private String DemoQues;
    private String bringBackQues;
    private String status;
    private java.sql.Date submitDate;
    private FileInputStream transcript;

    //Application default constructor
    public Applic() {
    }

    /*
     * public constructor for appointment class with the parameters
     * defining an appointment.
     *
     * @param firstName         Insert description here          
     * @param lastName          Insert description here 
     * @param studentID         Insert description here  
     * @param sex               Insert description here 
     * @param email             Insert description here 
     * @param street            Insert description here 
     * @param city              Insert description here 
     * @param state             Insert description here 
     * @param postCode          Insert description here 
     * @param dob               Insert description here 
     * @param phone             Insert description here 
     * 
     */
    public Applic(int accountId, String uniName, String uniCountry, Date startDate, Date finishDate, boolean unswPartner, boolean creditTransferToUnsw, FileInputStream transcript) {
        this.accountId = accountId;
        this.uniName = uniName;
        this.uniCountry = uniCountry;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.unswPartner = unswPartner;
        this.creditTransferToUnsw = creditTransferToUnsw;
        this.transcript = transcript;
        //this.submitDate =  new java.sql.Date(Calendar.getInstance().getTime().getTime());
    }
     public Applic( String uniName, String uniCountry, Date startDate, Date finishDate, boolean unswPartner, boolean creditTransferToUnsw, FileInputStream transcript) {
      
        this.uniName = uniName;
        this.uniCountry = uniCountry;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.unswPartner = unswPartner;
        this.creditTransferToUnsw = creditTransferToUnsw;
        this.transcript = transcript;
        //this.submitDate =  new java.sql.Date(Calendar.getInstance().getTime().getTime());
    }

    public Applic(int applicationId, String uniName, Date submitDate, String status) {
        this.applicationId = applicationId;
        this.uniName = uniName;
        this.submitDate = submitDate;
        this.status = status;
    }

    public Applic(String studentId, String uniName, Date submitDate, String status, int applicationId) {
        this.studentId = studentId;
        this.uniName = uniName;
        this.submitDate = submitDate;
        this.status = status;
        this.applicationId = applicationId;
    }

    public Applic(String supportQues, String DemoQues, String bringBackQues) {
        this.supportQues = supportQues;
        this.DemoQues = DemoQues;
        this.bringBackQues = bringBackQues;
    }

    public Applic(String uniName, String uniCountry, Date startDate, Date finishDate, boolean unswPartner, boolean creditTransferToUnsw, String supportQues, String DemoQues, String bringBackQues) {
        this.uniName = uniName;
        this.uniCountry = uniCountry;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.unswPartner = unswPartner;
        this.creditTransferToUnsw = creditTransferToUnsw;
        this.supportQues = supportQues;
        this.DemoQues = DemoQues;
        this.bringBackQues = bringBackQues;
    }
    /*
     * public constructor for appointment class with the parameters
     * defining an appointment.
     *
     * @param degreeName             Insert description here          
     * @param programCode            Insert description here 
     * @param faculty                Insert description here  
     * @param yearOfStudy            Insert description here 
     * @param yearToComplete         Insert description here 
     * @param wam                    Insert description here 
     * @param uniName                Insert description here 
     * @param uniCountry             Insert description here 
     * @param startDate              Insert description here 
     * @param finishDate             Insert description here 
     * @param unswPartner            Insert description here 
     * @param creditTransferToUnsw   Insert description here 
     * 
     */

    //Getter and Setter methods for Application class

    /**
     *
     * @return applicationID
     */
    public int getApplicationId() {
        return applicationId;
    }

    /**
     *
     * @param applicationId
     */
    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    /**
     *
     * @return uniName
     */
    public String getUniName() {
        return uniName;
    }

    /**
     *
     * @param uniName
     */
    public void setUniName(String uniName) {
        this.uniName = uniName;
    }

    /**
     *
     * @return uniCountry
     */
    public String getUniCountry() {
        return uniCountry;
    }

    /**
     *
     * @param uniCountry
     */
    public void setUniCountry(String uniCountry) {
        this.uniCountry = uniCountry;
    }

    /**
     *
     * @return startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     *
     * @param startDate
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     *
     * @return finishDate
     */
    public Date getFinishDate() {
        return finishDate;
    }

    /**
     *
     * @param finishDate
     */
    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    /**
     *
     * @return unswPartner
     */
    public boolean isUnswPartner() {
        return unswPartner;
    }

    /**
     *
     * @param UnswPartner
     */
    public void setUnswPartner(boolean UnswPartner) {
        this.unswPartner = UnswPartner;
    }

    /**
     *
     * @return creditTransferToUnsw
     */
    public boolean isCreditTransferToUnsw() {
        return creditTransferToUnsw;
    }

    /**
     *
     * @param creditTransferToUnsw
     */
    public void setCreditTransferToUnsw(boolean creditTransferToUnsw) {
        this.creditTransferToUnsw = creditTransferToUnsw;
    }

    /**
     *
     * @return supportQues
     */
    public String getSupportQues() {
        return supportQues;
    }

    /**
     *
     * @param supportQues
     */
    public void setSupportQues(String supportQues) {
        this.supportQues = supportQues;
    }

    /**
     *
     * @return demoQues
     */
    public String getDemoQues() {
        return DemoQues;
    }

    /**
     *
     * @param DemoQues
     */
    public void setDemoQues(String DemoQues) {
        this.DemoQues = DemoQues;
    }

    /**
     *
     * @return bringBackQues
     */
    public String getBringBackQues() {
        return bringBackQues;
    }

    /**
     *
     * @param bringBackQues
     */
    public void setBringBackQues(String bringBackQues) {
        this.bringBackQues = bringBackQues;
    }

    /**
     *
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return submitDate
     */
    public Date getSubmitDate() {
        return submitDate;
    }

    /**
     *
     * @param submitDate
     */
    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }
    /*
     * method for 
     * 
     */

    public java.sql.Date convertToSqlDate(LocalDate localDate) {
        java.sql.Date date = new Date(
                localDate.getYear() - 1900,
                localDate.getMonthValue() - 1,
                localDate.getDayOfMonth()
        );
        return date;
    }

    public FileInputStream getTranscript() {
        return transcript;
    }

    public void setTranscript(FileInputStream transcript) {
        this.transcript = transcript;
    }

}
