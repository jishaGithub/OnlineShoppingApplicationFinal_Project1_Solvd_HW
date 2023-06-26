package com.solvd.laba;

import com.solvd.laba.entities.*;
import com.solvd.laba.exceptions.*;
import com.solvd.laba.services.AdminService;
import com.solvd.laba.services.CustomerService;
import com.solvd.laba.services.SellerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Seller seller = new Seller(1, "Sam", "sam@gmail.com");
        Product productApple = new Product(1, "Apples", ProductCategory.FRUITS, 2.0, 20);
        Product productMM = new Product(2, "M&M", ProductCategory.CANDY, 3.0, 20);
        Product productGrapes = new Product(3, "Grapes", ProductCategory.FRUITS, 4.0, 20);

        try {
            SellerService sellerService1 = new SellerService(seller);
            sellerService1.setLoginCredentials("seller1", "789");
            sellerService1.login("seller1", "789");
            sellerService1.addProducts(productApple);
            sellerService1.addProducts(productMM);
            sellerService1.addProducts(productGrapes);
            sellerService1.viewProductsForSale();
            sellerService1.filterProductsByPrice(5);
            sellerService1.getProductCount();
            sellerService1.anyProductOutOfStock();
            sellerService1.getLeastExpensiveProduct();
            sellerService1.getMostExpensiveProduct();
            sellerService1.getAveragePriceOfProducts();
            sellerService1.showCustomCountProducts(2);
            sellerService1.getSortedProductsBasedOnPrice();
            sellerService1.productValue(productApple);
        } catch (NotPositiveException npe) {
            logger.error(npe.getMessage());
        } catch (SameValueException e) {
            logger.error(e.getMessage());
        }

        try {
            Customer customer1 = new Customer(1, "Sunny", "sunny@gmail.com", "1234564534");
            CustomerService customerService1 = new CustomerService(customer1);
            customerService1.setLoginCredentials("cust1", "678");
            customerService1.login("cust1", "678");
            customerService1.setAddress("west lake street", "atlanta", "GA", 45632);
            customerService1.addProductsToCart(productApple, 2);
            customerService1.addProductsToCart(productMM, 20);
            customerService1.addProductsToCart(productGrapes, 7);
            customerService1.showCart();
            customerService1.removeProductsFromCart(productGrapes);
            customerService1.showCart();
            Cart cart1 = customerService1.getCart();
            customerService1.setShippingInfo("Fedex", ShippingPreference.Domestic);
            Order order = customerService1.placeOrder(cart1, false);
            customerService1.setPaymentInformation(new Payment("VISA", PaymentMethod.CREDIT_CARD), "1234");
            customerService1.setPaymentInformation(new Payment("VISA", PaymentMethod.CREDIT_CARD), "1234567823454567");
            customerService1.makePurchase();
            customerService1.displayShippingInfo(order);
        } catch (SameValueException | PhoneNumberLengthException | NotValidZipException | NotPositiveException | NotValidCardNoException sve) {
            logger.error(sve.getMessage());
        }

        try {
            Customer customer2 = new Customer(2, "Mandy", "mandy@gmail.com", "3458942345");
            CustomerService customerService2 = new CustomerService(customer2);
            customerService2.setLoginCredentials("cust2", "abc");
            customerService2.setAddress("tray lane rd", "newyork", "NY", 34563);
            customerService2.login("cust2", "acb");
            customerService2.login("cust2", "abc");
            customerService2.addProductsToCart(productApple, 2);
            customerService2.addProductsToCart(productGrapes, 7);
            Cart cart2 = customerService2.getCart();
            customerService2.setShippingInfo("UPS", ShippingPreference.International);
            customerService2.placeOrder(cart2, true);
            customerService2.setPaymentInformation(new Payment("VISA", PaymentMethod.DEBIT_CARD), "1234234556783478");
            customerService2.makePurchase();
            CustomerService.getOrderHistory();
        } catch (SameValueException | PhoneNumberLengthException | NotPositiveException | NotValidZipException | NotValidCardNoException e) {
            logger.error(e.getMessage());
        }

        try {
            Customer customer3 = new Customer(1, "Sunny", "sunny@gmail.com", "0001235678");
        } catch (PhoneNumberLengthException e) {
            logger.error(e.getMessage());
        }

        try {
            Admin admin1 = new Admin(1);
            AdminService adminService = new AdminService(admin1);
            adminService.setLoginCredentials("admin2000", "hello");
            adminService.login("admin2000", "hello");
            adminService.viewCustomersAddresses();
            AdminService.displayCustomers();
            AdminService.viewSellers();
        } catch (SameValueException sme) {
            logger.error(sme.getMessage());
        }

        try {
            logger.info("Reflection Demo");
            Admin adminObj = new Admin(2, 2);
            Class<?> classObj = adminObj.getClass();
            Field field1 = classObj.getDeclaredField("adminDummyCount");
            field1.setAccessible(true);
            field1.set(adminObj, 3);
            int adminObjDummyCount = (int) field1.get(adminObj);
            logger.info("********* Field  *****************");
            logger.info("The updated field adminDummyCount is " + adminObjDummyCount);
            int modifiers = field1.getModifiers();
            String modifier1 = Modifier.toString(modifiers);
            logger.info("The modifier of the field adminDummyCount is " + modifier1);
            AdminService adminServiceObj = new AdminService();
            Method method1 = adminServiceObj.getClass().getMethod("setLoginCredentials", String.class, String.class);
            logger.info("*************  Method  *************");
            logger.info("Method modifier: " + Modifier.toString(method1.getModifiers()));
            logger.info("Method Name: " + method1.getName());
            logger.info("Method return type: " + method1.getReturnType());
            logger.info("Method parameter Count: " + method1.getParameterCount());
            logger.info("***********  Constructor  ***************");
            Constructor<?>[] constructors = classObj.getDeclaredConstructors();
            for (Constructor<?> constructor : constructors) {
                logger.info("Constructor Name: " + constructor.getName());
                logger.info("Parameters: " + constructor.getParameterCount());
                logger.info("Modifier: " + Modifier.toString(constructor.getModifiers()));
            }
            logger.info("******* Creating object and calling method using reflection **********");
            Class<?> classObj2 = Admin.class;
            Object instanceObj2 = classObj2.getDeclaredConstructor().newInstance();
            Method displayCustomersMethod = classObj2.getDeclaredMethod("getAdminDummyCount", int.class);
            displayCustomersMethod.invoke(instanceObj2, 6);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
