/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Zikri
 */
public class Education {
    private int accountId;
    private String degreeName;
    private String programCode;
    private String faculty;
    private String yearOfStudy;
    private int yearToComplete;
    private double wam;
    private String major;

    public Education() {
    }

    public Education(String degreeName, String programCode, String faculty, String yearOfStudy, int yearToComplete, double wam) {
        this.degreeName = degreeName;
        this.programCode = programCode;
        this.faculty = faculty;
        this.yearOfStudy = yearOfStudy;
        this.yearToComplete = yearToComplete;
        this.wam = wam;
    }

    public Education(int accountId, String degreeName, String programCode, String faculty, String yearOfStudy, int yearToComplete, double wam, String major) {
        this.accountId = accountId;
        this.degreeName = degreeName;
        this.programCode = programCode;
        this.faculty = faculty;
        this.yearOfStudy = yearOfStudy;
        this.yearToComplete = yearToComplete;
        this.wam = wam;
        this.major = major;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(String yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public int getYearToComplete() {
        return yearToComplete;
    }

    public void setYearToComplete(int yearToComplete) {
        this.yearToComplete = yearToComplete;
    }

    public double getWam() {
        return wam;
    }

    public void setWam(double wam) {
        this.wam = wam;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

}
