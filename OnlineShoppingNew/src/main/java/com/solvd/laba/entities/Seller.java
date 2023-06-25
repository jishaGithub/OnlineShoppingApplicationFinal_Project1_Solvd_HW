package com.solvd.laba.entities;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Seller extends User {
    private static final Logger logger = LogManager.getLogger(Seller.class);
    private int productId = 1;
    
    public Seller(int userId, String name, String emailAddress) {
        this.userId = userId;
        this.name = name;
        this.emailAddress = emailAddress;
        sellerHashSet.add(this.userId + " " + this.emailAddress);
    }
    
    public Seller(Seller seller) {
        this.userId = seller.getUserId();
        this.name = seller.getName();
        this.emailAddress = seller.getEmailAddress();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        Seller seller = (Seller) obj;
        return (seller.userId == this.userId && seller.name == this.name);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + this.userId;
        return result;
    }

    @Override
    public String toString() {
        return this.userId + " " + this.userName + " " + this.password + " " + this.emailAddress;
    }

    @Override
    public void displayWelcomeMsg() {
        logger.log(Level.INFO, this.userId + " successfully called displayWelcomeMsg().");
        System.out.println("Welcome to seller portal");
    }

    @Override
    public void login(String userName, String password) throws SameValueException {
        if (isLoginCredentialsSet) {
            if (userName.equals(password)) {
                throw new SameValueException(userName);
            }
            if ((userName.equals(this.userName)) && (password.equals(this.password))) {
                System.out.println(this.userId + " logged in successfully");
                logger.log(Level.INFO, this.userId + " logged in successfully.");
                this.displayWelcomeMsg();
                isLoginSuccessful = true;
            }
        } else {
            System.out.println("Login Credentials are not set");
        }
    }

    public void setLoginCredentials(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.isLoginCredentialsSet = true;
    }
    
    public static void viewProductsForSale() {
        System.out.println("******* The Products List *******");
        for (Product product : productHashSet) {
            System.out.println(product);
        }
    }

    public static void viewSellers() {
        System.out.println("\n----SELLER LIST-----");
        for (String sellers : sellerHashSet) {
            System.out.println(sellers);
        }
        System.out.println();
    }
}
