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
public class ApplicationStatus {
    private String status;
    private String comments;

    public ApplicationStatus() {
    }

    public String getStatus() {
        return status;
    }

    public ApplicationStatus(String status, String comments) {
        this.status = status;
        this.comments = comments;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    
    
}
