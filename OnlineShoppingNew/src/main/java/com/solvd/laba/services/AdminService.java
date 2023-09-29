package com.solvd.laba.services;

import com.solvd.laba.entities.Admin;
import com.solvd.laba.exceptions.SameValueException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdminService extends Admin {
    private static final Logger logger = LogManager.getLogger(AdminService.class);
    private Admin admin;

    public AdminService(Admin admin) {
        super();
        this.admin = admin;
    }

    public AdminService() {}

    public void setLoginCredentials(String userName, String password) {
        logger.log(Level.INFO,"Set Login Credential");
        this.userName = userName;
        this.password = password;
        this.isLoginCredentialsSet = true;
    }

    public static void viewSellers() {
        if (isLoginSuccessful) {
            logger.info("----SELLER LIST-----");
            for (String sellers : sellerHashSet) {
                logger.info(sellers);
            }
        }
    }

    public static void displayCustomers() {
        if (isLoginSuccessful) {
            logger.info("----CUSTOMER LIST-----");
            for (String customers : userHashSet) {
                logger.info(customers);
            }
        }
    }

    public void login(String userName, String password) throws SameValueException {
        try {
            admin.login(userName, password);
            isLoginSuccessful = true;
        } catch (SameValueException sve) {
            logger.error(sve.getMessage());
        }
    }
    
    public void viewCustomersAddresses() {
        logger.info("-----CUSTOMER ADDRESSES------");
        userAddressHashSet.stream().forEach(Address -> logger.info(Address));
    }

}
