package com.solvd.laba.services;

import com.solvd.laba.entities.*;
import com.solvd.laba.exceptions.NotPositiveException;
import com.solvd.laba.exceptions.NotValidCardNoException;
import com.solvd.laba.exceptions.NotValidZipException;
import com.solvd.laba.exceptions.PhoneNumberLengthException;
import com.solvd.laba.utils.linkedList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.text.DecimalFormat;

public class CustomerService extends Customer {
    private static final Logger logger = LogManager.getLogger(CustomerService.class);
    private Shipping shipping;
    private boolean isPaymentDone = false;
    private boolean isShippingInfoSet = false;
    private boolean isPaymentInfoSet = false;
    private boolean isAddressSet = false;
    private Payment payment;
    private final Cart cart;
    private Order order;
    private final DecimalFormat df = new DecimalFormat("#.##");
    private static final linkedList<Order> orderHistory = new linkedList<>();
    private final Customer customer;
    private String cardNo;

    public CustomerService(Customer customer) throws PhoneNumberLengthException {
        super(customer);
        this.cart = new Cart();
        this.customer = customer;
    }

    public static void getOrderHistory() {
        logger.info("-------ORDER HISTORY------");
        orderHistory.display();
    }

    public void setAddress(String street, String city, String state, int zip) throws NotValidZipException, NullPointerException {
        address = super.setUserAddress(street, city, state, zip);
        userHashSet.add(" ID: " + this.id + " NAME: " + this.name + " EMAIL: " + this.emailAddress + " PHONE: " + this.phoneNumber + " ADDRESS: " + address);
        userAddressHashSet.add(address);
        isAddressSet = true;
    }

    public void setLoginCredentials(String userName, String password) {
        this.userName = userName;
        this.password = password;
        customerLoginCredentials.put(userName, password);
        this.isLoginCredentialsSet = true;
    }

    public void addProductsToCart(Product product, int count) throws NotPositiveException {
        cart.addProducts(product,count);
    }

    public void removeProductsFromCart(Product product) {
        cart.removeProducts(product);
    }

    public void showCart() {
        logger.info("******* Cart of customer ID: " + this.getId() + " *********");
        cart.viewCart();
    }

    public Cart getCart() {
        return cart;
    }

    public Order placeOrder(Cart cart, Boolean newUser) {
        logger.info("-----Order Details----");
        if (isShippingInfoSet) {
            double currentTotalPrice = cart.getTotalPrice();
            logger.info("Total price without including shipping/taxes/discount - " + currentTotalPrice);
            Discount discount = new Discount(currentTotalPrice, newUser);
            double priceWithDiscount = discount.discountTotalPrice();
            logger.info("The total cost after applying discount : " + df.format(priceWithDiscount));
            double priceWithShipping = shipping.calculateShippingCost(shipping.getShipPreference(), priceWithDiscount);
            logger.info("The total cost including "+shipping.getShipPreference()+" shipping cost : " + df.format(priceWithShipping));
            order = new Order(customer, cart);
            order.setStatus(OrderStatus.ORDER_PLACED);
            double totalWithTax = order.addTaxes(priceWithShipping);
            logger.info("Final total including tax: "+df.format(totalWithTax)+"$");
            order.setOrderTotal(Double.parseDouble(df.format(totalWithTax)));
            orderHistory.add(order);
            logger.info(order);
            return order;
        }
        logger.info("Set shipping info first");
        return null;
    }

    public void makePurchase() {
        if (isPaymentInfoSet) {
            logger.info("Payment method selected: " + payment.getPayMethod());
            logger.info("Paid " + order.getOrderTotal() + "$ using " + payment.getCardType() + " card ending with " + this.cardNo.substring(12));
            logger.info("Purchase Successful! " + "Payment Confirmation No: " + payment.getPaymentId());
            order.setStatus(OrderStatus.PAYMENT_DONE);
            isPaymentDone = true;
        }
    }

    public void setShippingInfo(String shippingCompany, ShippingPreference shippingPreference) {
        shipping = new Shipping(shippingCompany, shippingPreference);
        isShippingInfoSet = true;
        logger.info("Shipping information set for customer ID:"+customer.getId());
    }

    public void displayShippingInfo(Order order) {
        if (isPaymentDone && isAddressSet) {
            shipping.displayShippingConfirmation(order);
            order.setStatus(OrderStatus.SHIPPED);
            return;
        }
        logger.info("Payment not done");
    }

    public Boolean setPaymentInformation(Payment payment, String cardNumber) throws NotValidCardNoException {
        this.payment = payment;
        this.cardNo = cardNumber;
        try {
            long cardNumberLong = Long.parseLong(cardNumber);
        } catch (NumberFormatException nfe) {
            logger.error("Card Number Format Error" + nfe.getMessage());
            return isPaymentInfoSet;
        }
        try {
            if (cardNumber.length() != 16) {
                throw new NotValidCardNoException("Error: Card number should be 16 digits - ", cardNumber);
            }
        } catch (NotValidCardNoException e) {
            logger.error(e.getMessage());
            return isPaymentInfoSet;
        }
        logger.info("Payment information set");
        return isPaymentInfoSet = true;
    }
}
