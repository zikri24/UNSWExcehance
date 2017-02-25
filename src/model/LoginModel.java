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
public class LoginModel {
    
    private boolean login;
    private int accountId;
    private String role;
    private String name;

    public LoginModel() {
    } 

    public LoginModel(boolean login,int accountId, String role, String name) {
        this.login = login;
        this.accountId = accountId;
        this.role = role;
        this.name = name;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
    
}
