/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.FileInputStream;

/**
 *
 * @author Zikri
 */
public class Course {
    private int courseId;
    private String foreignCode;
    private String foreignName;
    private String unswCode;
    private String unswName;   
     private String status;   
    private FileInputStream courseOutline;
    private int applicationNo;
    private boolean previouslyMatch;
    private String conditions;

    public Course() {
    }
      public Course(String foreignCode, String foreignName, String unswCode, String unswName) {
      
        this.foreignCode = foreignCode;
        this.foreignName = foreignName;
        this.unswCode = unswCode;
        this.unswName = unswName;        
    }
      public Course(int courseId,String foreignCode, String foreignName, String unswCode, String unswName ) {
        this.courseId = courseId;
        this.foreignCode = foreignCode;
        this.foreignName = foreignName;
        this.unswCode = unswCode;
        this.unswName = unswName;          
    }

    public Course(int courseId,String foreignCode, String foreignName, String unswCode, String unswName,boolean previouslyMatch ,String status ) {
        this.courseId = courseId;
        this.foreignCode = foreignCode;
        this.foreignName = foreignName;
        this.unswCode = unswCode;
        this.unswName = unswName;     
        this.previouslyMatch = previouslyMatch;
        this.status = status;
    }

    public Course(String foreignCode, String foreignName, String unswCode, String unswName, FileInputStream courseOutline, int applicationNo) {
        this.foreignCode = foreignCode;
        this.foreignName = foreignName;
        this.unswCode = unswCode;
        this.unswName = unswName;     
         this.courseOutline = courseOutline; 
        this.applicationNo = applicationNo;
    }
    

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public FileInputStream getCourseOutline() {
        return courseOutline;
    }

    public void setCourseOutline(FileInputStream courseOutline) {
        this.courseOutline = courseOutline;
    }

    public void setUnswName(String unswName) {
        this.unswName = unswName;
    }

    public int getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(int applicationNo) {
        this.applicationNo = applicationNo;
    }

    public FileInputStream getOutline() {
        return courseOutline;
    }

    public void setOutline(FileInputStream outline) {
        this.courseOutline = outline;
    }

    public boolean isPreviouslyMatch() {
        return previouslyMatch;
    }

    public void setPreviouslyMatch(boolean previouslyMatch) {
        this.previouslyMatch = previouslyMatch;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }
    
    
}

