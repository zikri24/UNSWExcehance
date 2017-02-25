/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import javafx.scene.image.Image;

/**
 *
 * @author Zikri
 */
public class Account {

    private int accountId;
    private String password;
    private String role;
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
    private Image image;

    public Account() {
    }
    
   
     public Account(String firstName, String lastName, String email, String sex,  String street, String city, String state, String postcode, java.sql.Date dob, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.sex = sex;
       
        this.street = street;
        this.city = city;
        this.state = state;
        this.postcode = postcode;
        this.dob = dob;
        this.phone = phone;
    }
    public Account(String firstName, String lastName, String studentId, String sex, String email, String street, String city, String state, String postcode, java.sql.Date dob, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentId = studentId;
        this.sex = sex;
        this.email = email;
        this.street = street;
        this.city = city;
        this.state = state;
        this.postcode = postcode;
        this.dob = dob;
        this.phone = phone;
    }

    public Account(String password) {
        super();
        this.password = password;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    
    

}
