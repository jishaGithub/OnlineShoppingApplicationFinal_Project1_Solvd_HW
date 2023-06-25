package com.solvd.laba.services;

import com.solvd.laba.entities.*;
import com.solvd.laba.utils.linkedList;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.text.DecimalFormat;
import java.util.List;

public class CustomerService extends Customer {
    private static final Logger logger = LogManager.getLogger(CustomerService.class);
    private static linkedList<Order> orderHistory = new linkedList<>();
    private Shipping shipping;
    private boolean ifPurchaseCompleted = false;
    private boolean isShippingInfoSet = false;
    private double totalWithTax;
    private Payment payment;
    private Cart cart;
    private Order order;
    private Discount discount;
    private DecimalFormat df = new DecimalFormat("#.##");
    
    public CustomerService(Customer customer) throws PhoneNumberLengthException {
        super(customer);
        this.cart = new Cart();
    }
    
    public void addProductsToCart(Product product, int count) throws NotPositiveException {
        cart.addProducts(product, count);
    }

    public void removeProductsFromCart(Product product) {
        cart.removeProducts(product);
    }

    public void showCart() {
        System.out.println("\n******* Cart of customer ID: " + this.getUserId() + " *********");
        cart.viewCart();
    }

    public Cart getCart() {
        return cart;
    }

    public Order placeOrder(Customer customer,Cart cart) {
        order = new Order(customer,cart);
        displayOrderDetails(order);
        logger.log(Level.INFO, "Order placed");
        orderHistory.add(order);
        return order;
    }

    public static void getOrderHistory() {
        System.out.println("\n-------ORDER HISTORY------");
        orderHistory.display();
    }

    public void displayOrderDetails(Order order) {
        System.out.println("-----Order Details----");
        System.out.println(order);
        if (isShippingInfoSet && order.getStatus() == OrderStatus.ORDER_PLACED) {
            double totalPrice = order.getOrderTotal();
            System.out.println("The total cost before shipping and discount : " + totalPrice);
            double priceWithShipping = shipping.calculateShippingCost(ShippingPreference.Domestic, totalPrice);
            System.out.println("The total cost including shipping cost : " + df.format(priceWithShipping));
            discount = new Discount(1, true);
            double priceWithDiscount = discount.discountTotalPrice(priceWithShipping, false);
            System.out.println("The total cost after applying discount : " + df.format(priceWithDiscount));
            totalWithTax = order.addTaxes(priceWithDiscount);
            System.out.println("Final total including tax: " + df.format(totalWithTax)+"$");
            logger.log(Level.INFO, "Display order details");
        } else {
            System.out.println("Shipping info is not set. Please add shipping details");
        }
    }

    public void makePurchase(String cardType, String cardNo) throws NotValidCardNoException {
        if(order.getStatus() == OrderStatus.ORDER_PLACED) {
            if (cardNo.length() != 16) {
                throw new NotValidCardNoException("Card number should be 16 digits", cardNo);
            }
            try {
                int intCardNo = Integer.parseInt(cardNo);
            } catch (NumberFormatException nfe) {
                logger.log(Level.INFO, "Card number format error");
            }
            payment = new Payment(cardType, cardNo,PaymentMethod.CREDIT_CARD);
            payment.makePurchase(Double.parseDouble(df.format(totalWithTax)));
            order.setStatus(OrderStatus.PAYMENT_DONE);
        }
    }

    public void setShippingInfo(int id, String shippingCompany) {
        shipping = new Shipping(1, shippingCompany);
        isShippingInfoSet = true;
    }

    public void displayShippingInfo(){
        if(order.getStatus() == OrderStatus.ORDER_PLACED) {
            shipping.displayShippingConfirmation();
            order.setStatus(OrderStatus.SHIPPED);
        }
    }
}
