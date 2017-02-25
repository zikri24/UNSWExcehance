/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Zikri
 */
public class Search {

    private String firstName;
    private String lastName;
    private String studentNo;
    private java.sql.Date fromDate;
    private java.sql.Date toDate;
    private String status;

    public Search() {
    }

    public Search(String firstName, String lastName, String studentNo, Date fromDate, Date toDate, String status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentNo = studentNo;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    

}
