package com.solvd.laba.entities;

import com.solvd.laba.exceptions.NotValidZipException;
import com.solvd.laba.exceptions.SameValueException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.function.Consumer;

public abstract class User {
    private static final Logger logger = LogManager.getLogger(User.class);
    protected static HashSet<String> userHashSet = new HashSet<>();
    protected static HashSet<Address> userAddressHashSet = new HashSet<>();
    protected static HashSet<Product> productHashSet = new HashSet<>();
    protected static HashSet<String> sellerHashSet = new HashSet<>();
    protected static TreeMap<String,String> customerLoginCredentials = new TreeMap<>();
    protected static boolean isLoginSuccessful = false;
    protected String name;
    protected String emailAddress;
    protected String phoneNumber;
    protected Address address;
    protected int id;
    protected String userName;
    protected String password;
    protected boolean isLoginCredentialsSet = false;

    public abstract void displayWelcomeMsg();

    public abstract void login(String userName, String password) throws SameValueException;

    public final Address setUserAddress(String street, String city, String state, int zip) throws NotValidZipException {
        if (zip > 99999 || zip < 10000) {
            throw new NotValidZipException(zip);
        }
        address = new Address(street, city, state, zip);
        return address;
    }

    public void displayCustomMsg(String message){
        Consumer<String> msg = myMsg -> logger.info(myMsg);
        msg.accept(message);
    }

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public int getId() {
        return id;
    }

}
