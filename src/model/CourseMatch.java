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
public class CourseMatch {

    private String foreignCode;
    private String foreignName;
    private String unswCode;
    private String unswName;
    private String status;
    private java.sql.Date dateReviewed;
    private String firstName;
    private String lastName;
    private String uniName;

    public CourseMatch() {
    }

    public CourseMatch(String foreignCode, String foreignName, String unswCode, String unswName, String status, Date dateReviewed, String firstName, String lastName, String uniName) {
        this.foreignCode = foreignCode;
        this.foreignName = foreignName;
        this.unswCode = unswCode;
        this.unswName = unswName;
        this.status = status;
        this.dateReviewed = dateReviewed;
        this.firstName = firstName;
        this.lastName = lastName;
        this.uniName = uniName;
    }

    public String getForeignCode() {
        return foreignCode;
    }

    public void setForeignCode(String foreignCode) {
        this.foreignCode = foreignCode;
    }

    public String getForeignName() {
        return foreignName;
    }

    public void setForeignName(String foreignName) {
        this.foreignName = foreignName;
    }

    public String getUnswCode() {
        return unswCode;
    }

    public void setUnswCode(String unswCode) {
        this.unswCode = unswCode;
    }

    public String getUnswName() {
        return unswName;
    }

    public void setUnswName(String unswName) {
        this.unswName = unswName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateReviewed() {
        return dateReviewed;
    }

    public void setDateReviewed(Date dateReviewed) {
        this.dateReviewed = dateReviewed;
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

    public String getUniName() {
        return uniName;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }

}
