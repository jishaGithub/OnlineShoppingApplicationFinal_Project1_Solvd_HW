package com.solvd.laba;

import com.solvd.laba.entities.*;
import com.solvd.laba.services.CustomerService;
import com.solvd.laba.services.SellerServices;
import java.lang.reflect.*;

public class Main {
    public static void main(String[] args) throws SameValueException {
        System.out.println("--------");
        Seller seller = new Seller(1, "Sam", "sam@gmail.com");
            seller.setLoginCredentials("seller1", "789");
        try {
            seller.login("seller1", "789");
        } catch (SameValueException sve) {
            System.out.println(sve.getMessage());
        }
        Product productApple = new Product(1,"Apples", ProductCategory.FRUITS, 3.99, 20);
        Product productMM = new Product(2,"M&M", ProductCategory.CANDY, 5.99, 20);
        Product productGrapes = new Product(3,"Grapes", ProductCategory.FRUITS, 1.99, 20);
        try {
            SellerServices sellerServices1 = new SellerServices(seller);
            sellerServices1.addProducts(productApple);
            sellerServices1.addProducts(productMM);
            sellerServices1.addProducts(productGrapes);
            sellerServices1.filterProducts(5);
            sellerServices1.getProductCount();
            sellerServices1.anyProductOutOfStock();
            sellerServices1.getLeastExpensiveProduct();
            sellerServices1.getMostExpensiveProduct();
            sellerServices1.getAveragePriceOfProducts();
            sellerServices1.showCustomCountProducts(2);
            sellerServices1.getSortedProductsBasedOnPrice();
        } catch (NotPositiveException npe) {
            System.out.println(npe.getMessage());
        }
        try {
            Customer customer1 = new Customer(1, "Sunny", "sunny@gmail.com", "1234564534");
            customer1.setAddress("west lake street", "atlanta", "GA", 45632);
            customer1.setLoginCredentials("cust1", "678");
            customer1.login("cust1", "678");
            Customer customer2 = new Customer(2, "Mandy", "mandy@gmail.com", "3458942345");
            customer2.setAddress("tray lane rd", "newyork", "NY", 34563);
            customer2.setLoginCredentials("cust2","abc");
            customer2.login("cust2","abc");
            Customer.viewCustomersAddresses();
            CustomerService customerService1 = new CustomerService(customer1);
            customerService1.addProductsToCart(productApple, 2);
            customerService1.addProductsToCart(productMM, 20);
            customerService1.addProductsToCart(productGrapes, 7);
            customerService1.showCart();
            customerService1.removeProductsFromCart(productGrapes);
            customerService1.showCart();
            Cart cart1 = customerService1.getCart();
            customerService1.setShippingInfo(1, "Fedex");
            customerService1.placeOrder(customer1,cart1);
            customerService1.makePurchase( "VISA", "1234124512561238");
            customerService1.displayShippingInfo();
            CustomerService customerService2 = new CustomerService(customer2);
            customerService2.addProductsToCart(productApple, 5);
            customerService2.addProductsToCart(productGrapes, 7);
            Cart cart2 = customerService2.getCart();
            customerService2.placeOrder(customer2, cart2);
            CustomerService.getOrderHistory();
        } catch (SameValueException sve) {
            System.out.println(sve.getMessage());
        } catch (PhoneNumberLengthException pnle) {
            System.out.println(pnle.getMessage());
        } catch (NotValidZipException nvze) {
            System.out.println(nvze.getMessage());
        } catch (NotValidCardNoException nvce) {
            System.out.println(nvce.getMessage());
        } catch (NotPositiveException npe) {
            System.out.println(npe.getMessage());
        }
        Admin admin1 = new Admin(1);
        admin1.setLoginCredentials("admin2000", "hello");
        admin1.login("admin2000", "hello");
        admin1.displayCustomers();
        admin1.displayProducts();
        admin1.displaySellers();
        try {
            System.out.println("Reflection Demo");
            Admin adminObj = new Admin(2, 2);
            Class<?> classObj = adminObj.getClass();
            Field field1 = classObj.getDeclaredField("adminDummyCount");
            field1.setAccessible(true);
            field1.set(adminObj, 3);
            int adminObjDummyCount = (int) field1.get(adminObj);
            System.out.println("********* Field  *****************");
            System.out.println("The updated field adminDummyCount is " + adminObjDummyCount);
            int modifiers = field1.getModifiers();
            String modifier1 = Modifier.toString(modifiers);
            System.out.println("The modifier of the field adminDummyCount is " + modifier1);
            Method method1 = classObj.getMethod("setLoginCredentials", String.class, String.class);
            System.out.println("*************  Method  *************");
            System.out.println("Method modifier: " + Modifier.toString(method1.getModifiers()));
            System.out.println("Method Name: " + method1.getName());
            System.out.println("Method return type: " + method1.getReturnType());
            System.out.println("Method parameter Count: " + method1.getParameterCount());
            System.out.println("***********  Constructor  ***************");
            Constructor[] constructors = classObj.getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                System.out.println("Constructor Name: " + constructor.getName());
                System.out.println("Parameters: " + constructor.getParameterCount());
                System.out.println("Modifier: " + Modifier.toString(constructor.getModifiers()));
            }
            System.out.println("******* Creating object and calling method using reflection **********");
            Class<?> classObj2 = Admin.class;
            Object instanceObj2 = classObj2.getDeclaredConstructor().newInstance();
            Method displayCustomersMethod = classObj2.getDeclaredMethod("getAdminDummyCount", int.class);
            displayCustomersMethod.invoke(instanceObj2, 6);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
