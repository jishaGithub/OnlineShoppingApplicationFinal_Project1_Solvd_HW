package com.solvd.laba.entities;

import com.solvd.laba.exceptions.SameValueException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Seller extends User {
    private static final Logger logger = LogManager.getLogger(Seller.class);
    private int productId = 1;

    public Seller(int id, String name, String emailAddress) {
        this.id = id;
        this.name = name;
        this.emailAddress = emailAddress;
        sellerHashSet.add(this.id + " " + this.emailAddress);
    }

    public Seller(Seller seller) {
        this.id = seller.getId();
        this.name = seller.getName();
        this.emailAddress = seller.getEmailAddress();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Seller)) {
            return false;
        }
        Seller seller = (Seller) obj;
        return this.name.equals(seller.name) && this.emailAddress.equals(seller.emailAddress);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + this.id;
        return result;
    }

    @Override
    public String toString() {
        return this.id + " " + this.userName + " " + this.password + " " + this.emailAddress;
    }

    @Override
    public void displayWelcomeMsg() {
        logger.info("Welcome to the seller portal");
    }

    @Override
    public void login(String userName, String password) throws SameValueException {
        if (isLoginCredentialsSet) {
            if (userName.equals(password)) {
                throw new SameValueException(userName);
            }
            if (userName.equals(this.userName) && password.equals(this.password)) {
                logger.log(Level.INFO, "Seller ID: " + this.id + " logged in successfully.");
                this.displayWelcomeMsg();
                isLoginSuccessful = true;
            }
        } else {
            logger.info("Login credentials are not set");
        }
    }
}
