package com.solvd.laba.entities;

import com.solvd.laba.exceptions.PhoneNumberLengthException;
import com.solvd.laba.exceptions.SameValueException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Objects;

public class Customer extends User {
    private static final Logger logger = LogManager.getLogger(Customer.class);
    private static int customerCount = 0;

    public Customer(int customerId, String name, String emailAddress, String phoneNumber) throws PhoneNumberLengthException {
        try {
            long longPhoneNumber = Long.parseLong(phoneNumber);
        } catch (NumberFormatException nfe) {
            logger.error(nfe.getMessage());
        }
        try {
            if (phoneNumber.length() != 10) {
                throw new PhoneNumberLengthException("Phone number length error", phoneNumber);
            }
            if (!phoneNumber.contains("123456789") || phoneNumber.startsWith("000")) {
                throw new PhoneNumberLengthException("Phone number is zero or Not valid",phoneNumber);
            }
        } catch (PhoneNumberLengthException pe) {
            logger.info(pe.getMessage());
        }
        this.id = customerId;
        this.name = name;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        customerCount++;
        logger.log(Level.INFO, "Customer object created");
    }

    public Customer(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.emailAddress = customer.getEmailAddress();
        this.phoneNumber = customer.getPhoneNumber();
    }

    public static void displayCustomerCount() {
        logger.info("The total count of customers: " + customerCount);
    }

    public boolean isLoginSuccessful() {
        return isLoginSuccessful;
    }

    public void displayWelcomeMsg() {
        if (isLoginSuccessful()) {
            displayCustomMsg("Login Successful! Welcome customer: " + this.userName);
        } else {
            displayCustomMsg("Access to portal denied. Login first");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        Customer customer = (Customer) obj;
        return (customer.getId() == this.id && customer.name.equals(this.name) && customer.emailAddress.equals(this.emailAddress));
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + this.getId();
        return result;
    }

    public void login(String userName, String password) throws SameValueException {
        if (isLoginCredentialsSet) {
            logger.log(Level.INFO, "Customer " + this.getId() + " login attempt");
            if (userName.equals(password)) {
                logger.log(Level.INFO, "Customer " + this.getId() + " - SameValueException");
                throw new SameValueException(userName);
            }
            if (userName.equals(this.getUserName()) && Objects.equals(password, customerLoginCredentials.get(userName))) {
                logger.info(this.getUserName() + " logged in successfully");
                logger.log(Level.INFO, "Customer " + this.getId() + " - logged in successfully");
                isLoginSuccessful = true;
                this.displayWelcomeMsg();
            } else {
                logger.log(Level.INFO, "Customer " + this.getId() + " unsuccessful login. Try again!");
                isLoginSuccessful = false;
            }
        } else {
            logger.info("Login credentials are not set. Login unsuccessful!");
            isLoginSuccessful = false;
        }
    }

    public String getUserName() {
        return userName;
    }

    public boolean isLoginSuccess() {
        return isLoginSuccessful;
    }

    public void setLoginSuccess(boolean loginSuccess) {
        isLoginSuccessful = loginSuccess;
    }

    public static int getCustomerCount() {
        return customerCount;
    }

    public static void setCustomerCount(int customerCount) {
        Customer.customerCount = customerCount;
    }
}
