package com.solvd.laba.entities;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Objects;

public class Customer extends User {
    private static final Logger logger = LogManager.getLogger(Customer.class);
    private static int customerCount = 0;
    private boolean isLoginSuccess = false;

    public Customer(int customerId, String name, String emailAddress, String phoneNumber) throws PhoneNumberLengthException {
        try {
            long longPhoneNumber = Long.valueOf(phoneNumber);
        } catch(NumberFormatException nfe) {
            System.out.println(nfe.getMessage());
        }
        if (phoneNumber.length() != 10) {
            throw new PhoneNumberLengthException(phoneNumber, "Phone number length error: " + phoneNumber);
        }
        this.userId = customerId;
        this.name = name;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        customerCount++;
        logger.log(Level.INFO, "Customer object created");
    }

    public Customer(Customer customer) {
        this.userId = customer.getUserId();
        this.name = customer.getName();
        this.emailAddress = customer.getEmailAddress();
        this.phoneNumber = customer.getPhoneNumber();
    }

    public static void displayCustomerCount() {
        System.out.println("The total count of customers: " + customerCount);
    }

    public static void viewCustomersAddresses() {
        System.out.println("\n-----CUSTOMER ADDRESSES------");
        userAddressHashSet.stream().forEach(Address -> System.out.println(Address));
    }

    public static void viewCustomers() {
        System.out.println("\n----CUSTOMER LIST-----");
        for (String customers : userHashSet) {
            System.out.println(customers);
        }
        System.out.println();
    }

    public void setLoginCredentials(String userName, String password) {
        this.userName = userName;
        this.password = password;
        customerLoginCredentials.put(userName, password);
        this.isLoginCredentialsSet = true;
    }

    public boolean isLoginSuccessful() {
        return isLoginSuccess;
    }

    public void displayWelcomeMsg() {
        if (isLoginSuccessful()) {
            displayCustomMsg("Login Successful!"+" Welcome customer: "+this.userName);
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
        return (customer.getUserId() == this.userId && customer.name == this.name && customer.emailAddress == this.emailAddress);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + this.getUserId();
        return result;
    }

    public void login(String userName, String password) throws SameValueException {
        if (isLoginCredentialsSet) {
            logger.log(Level.INFO, "Customer " + this.getUserId() + " login attempt");
            if (userName.equals(password)) {
                logger.log(Level.INFO, "Customer " + this.getUserId() + " - SameValueException");
                isLoginSuccess = false;
                throw new SameValueException(userName);
            }
            if ((userName.equals(this.getUserName())) && (Objects.equals(password, customerLoginCredentials.get(userName)))) {
                System.out.println(this.getUserName() + " logged in successfully");
                logger.log(Level.INFO, "Customer " + this.getUserId() + " - logged in successfully");
                isLoginSuccess = true;
                this.displayWelcomeMsg();
            } else {
                logger.log(Level.INFO, "Customer " + this.getUserId() + " unsuccessful login");
                System.out.println("Try again");
                isLoginSuccess = false;
            }
        } else {
            System.out.println("Login credentials are not set. Login unsuccessful!");
            isLoginSuccess = false;
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setAddress(String street, String city, String state, int zip) throws NotValidZipException, NullPointerException {
        address = super.setUserAddress(street, city, state, zip);
        userHashSet.add(" ID: " + this.userId + " NAME: " + this.name + " EMAIL: " + this.emailAddress + " PHONE: " + this.phoneNumber + " ADDRESS: " + address);
        userAddressHashSet.add(address);
    }

    public boolean isLoginSuccess() {
        return isLoginSuccess;
    }

    public void setLoginSuccess(boolean loginSuccess) {
        isLoginSuccess = loginSuccess;
    }

    public static int getCustomerCount() {
        return customerCount;
    }

    public static void setCustomerCount(int customerCount) {
        Customer.customerCount = customerCount;
    }
}
