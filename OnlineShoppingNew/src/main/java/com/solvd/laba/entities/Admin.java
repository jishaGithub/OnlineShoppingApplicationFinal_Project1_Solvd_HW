package com.solvd.laba.entities;

import com.solvd.laba.exceptions.SameValueException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Admin extends User {
    private static final Logger logger = LogManager.getLogger(Admin.class);
    private Seller seller;
    private int adminDummyCount;

    public Admin(int adminId) {
        this.id = adminId;
    }

    public Admin(int adminId, int adminDummyCount) {
        this.id = adminId;
        this.adminDummyCount = adminDummyCount;
    }

    public Admin() {
        this.id = 0;
        this.adminDummyCount = 1;
    }

    @Override
    public void displayWelcomeMsg() {
        logger.log(Level.INFO, "Display welcome msg");
        if (isLoginSuccessful()) {
            displayCustomMsg("Login Successful! Welcome Admin: " + this.userName);
        } else {
            displayCustomMsg("Access to portal denied. Login first");
        }
    }

    public boolean isLoginSuccessful() {
        return isLoginSuccessful;
    }

    @Override
    public void login(String userName, String password) throws SameValueException {
        if (isLoginCredentialsSet) {
            logger.log(Level.INFO, "Admin " + this.id + " login attempt");
            if (userName.equals(password)) {
                logger.log(Level.INFO, "Admin " + this.id + " - SameValueException");
                throw new SameValueException(userName);
            }
            if ((userName.equals(this.userName)) && (password.equals(this.password))) {
                logger.info("Admin:" + this.id + " logged in successfully");
                isLoginSuccessful = true;
                displayWelcomeMsg();
            } else {
                logger.log(Level.INFO, "Admin " + this.id + " unsuccessful login");
                logger.info("Try again");
            }
        }
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public void getAdminDummyCount(int i) {
        logger.info("Dummy count = " + i);
    }

    public void setAdminDummyCount(int adminDummyCount) {
        this.adminDummyCount = adminDummyCount;
    }
}
