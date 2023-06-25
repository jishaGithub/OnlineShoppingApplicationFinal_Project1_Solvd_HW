package com.solvd.laba.entities;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Admin extends User {
    private static final Logger logger = LogManager.getLogger(Admin.class);
    private Seller seller;
    private boolean isLoginSuccess = false;
    private int adminDummyCount;

    public Admin(int adminId) {
        this.userId = adminId;
    }
    
    public Admin(int adminId, int adminDummyCount) {
        this.userId = adminId;
        this.adminDummyCount = adminDummyCount;
    }

    public Admin(){
        this.userId = 0;
        this.adminDummyCount = 1;
    }

    public void setLoginCredentials(String userName, String password) {
        logger.log(Level.INFO,"Set Login Credential");
        this.userName = userName;
        this.password = password;
        this.isLoginCredentialsSet = true;
    }

    @Override
    public void displayWelcomeMsg() {
        logger.log(Level.INFO,"Display welcome msg");
        if (isLoginSuccessful()) {
            displayCustomMsg("Login Successful!" + " Welcome customer: " + this.userName);
        } else {
            displayCustomMsg("Access to portal denied. Login first");
        }
    }

    public boolean isLoginSuccessful() {
        return isLoginSuccess;
    }

    @Override
    public void login(String userName, String password) throws SameValueException {
        if (isLoginCredentialsSet) {
            logger.log(Level.INFO, "Admin " + this.userId + " login attempt");
            if (userName.equals(password)) {
                logger.log(Level.INFO, "Admin " + this.userId + " - SameValueException");
                throw new SameValueException(userName);
            }
            if ((userName.equals(this.userName)) && (password.equals(this.password))) {
                System.out.println(this.userId + " logged in successfully");
                logger.log(Level.INFO, "Admin " + this.userId + " - logged in successfully");
                isLoginSuccess = true;
                displayWelcomeMsg();
            } else {
                logger.log(Level.INFO, "Admin " + this.userId + " unsuccessful login");
                System.out.println("Try again");
            }
        }
    }

    public void displaySellers() {
        logger.log(Level.INFO,"Display seller function");
        if (isLoginSuccess) {
            Seller.viewSellers();
        }
    }

    public void displayProducts() {
        logger.log(Level.INFO,"Display Products function");
        if (isLoginSuccess) {
            Seller.viewProductsForSale();
        }
    }

    public void displayCustomers() {
        logger.log(Level.INFO,"Display customers function");
        if (isLoginSuccess) {
            Customer.viewCustomers();
        }
    }

    public boolean isLoginSuccess() {
        return isLoginSuccess;
    }

    public void setLoginSuccess(boolean loginSuccess) {
        isLoginSuccess = loginSuccess;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
    public void getAdminDummyCount(int i) {
        System.out.println("Dummy count = "+ i);
    }

    public void setAdminDummyCount(int adminDummyCount) {
        this.adminDummyCount = adminDummyCount;
    }
    
}
