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
public class Profile {

    private String firstName;
    private String lastName;
    private String studentId;
    private String sex;
    private String email;
    private String street;
    private String city;
    private String state;
    private String postcode;
    private java.sql.Date dob;
    private String phone;
    private String degreeName;
    private String programCode;
    private String faculty;
    private String yearOfStudy;
    private int yearToComplete;
    private double wam;
    private String major;

    private String uniName;
    private String uniCountry;
    private java.sql.Date startDate;
    private java.sql.Date finishDate;
    private boolean unswPartner;
    private boolean creditTransferToUnsw;

    public Profile() {
    }

    public Profile(String firstName, String lastName, String studentId, String sex, String email, String street, String city, String state, String postcode,java.sql.Date DOB, String phone, String degreeName, String programCode, String faculty, String yearOfStudy, int yearToComplete, double wam, String major) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentId = studentId;
        this.sex = sex;
        this.email = email;
        this.street = street;
        this.city = city;
        this.state = state;
        this.postcode = postcode;
        this.dob = DOB;
        this.phone = phone;
        this.degreeName = degreeName;
        this.programCode = programCode;
        this.faculty = faculty;
        this.yearOfStudy = yearOfStudy;
        this.yearToComplete = yearToComplete;
        this.wam = wam;
        this.major = major;
    }

    public Profile(String firstName, String lastName, String studentId,
            String email, String phone, String degreeName, String programCode, String yearOfStudy, int yearToComplete, double wam, String major, String uniName, String country, boolean unswPartner, boolean creditTransferToUnsw, Date startDate, Date finishDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentId = studentId;
        this.email = email;
        this.phone = phone;
        this.degreeName = degreeName;
        this.programCode = programCode;

        this.yearOfStudy = yearOfStudy;
        this.yearToComplete = yearToComplete;
        this.wam = wam;
        this.major = major;
        this.uniName = uniName;
        this.uniCountry = country;
        this.unswPartner = unswPartner;
        this.creditTransferToUnsw = creditTransferToUnsw;
        this.startDate = startDate;
        this.finishDate = finishDate;
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

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getUniName() {
        return uniName;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }

    public String getUniCountry() {
        return uniCountry;
    }

    public void setUniCountry(String uniCountry) {
        this.uniCountry = uniCountry;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public boolean isUnswPartner() {
        return unswPartner;
    }

    public void setUnswPartner(boolean unswPartner) {
        this.unswPartner = unswPartner;
    }

    public boolean isCreditTransferToUnsw() {
        return creditTransferToUnsw;
    }

    public void setCreditTransferToUnsw(boolean creditTransferToUnsw) {
        this.creditTransferToUnsw = creditTransferToUnsw;
    }

}
