package com.solvd.laba.services;

import com.solvd.laba.entities.NotPositiveException;
import com.solvd.laba.entities.Product;
import com.solvd.laba.entities.Seller;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SellerServices extends Seller {
    private static final Logger logger = LogManager.getLogger(SellerServices.class);
    
    public SellerServices(Seller seller) {
        super(seller);
    }

    public Product addProducts(Product product) throws NotPositiveException {
        if (product.getProductStock() < 0 | product.getProductPrice() < 0) {
            throw new NotPositiveException(product.getProductStock(), product.getProductPrice());
        }
        productHashSet.add(product);
        logger.log(Level.INFO, "New product object created");
        return product;
    }

    public void filterProducts(double price) {
        List<Product> filterProductsByPrice = productHashSet.stream().filter(product -> product.getProductPrice() > price)
                .toList();
        System.out.println("The products that are priced greater than "+price+" are "+filterProductsByPrice);
    }

    public void getProductCount() {
        System.out.println("Product Count: "+productHashSet.stream().count());
    }

    public void anyProductOutOfStock() {
        Boolean result = productHashSet.stream().anyMatch(product -> product.getProductStock() == 0);
        System.out.println("If there any product that is out of stock: "+result);
    }

    public void getLeastExpensiveProduct() {
        Optional<Product> leastExpensiveProduct = productHashSet.stream().min(Comparator.comparing(Product::getProductPrice));
        if (leastExpensiveProduct.isPresent()) {
            System.out.println("The least expensive product: "+leastExpensiveProduct.get().getProductName());
        }
    }

    public void getMostExpensiveProduct() {
        Optional<Product> mostExpensiveProduct = productHashSet.stream().max(Comparator.comparing(Product::getProductPrice));
        if (mostExpensiveProduct.isPresent()) {
            System.out.println("The most expensive product is: " + mostExpensiveProduct);
        }
    }

    public void getAveragePriceOfProducts() {
        double avgPrice = productHashSet.stream().mapToDouble(Product::getProductPrice).average().getAsDouble();
        System.out.println("Average price of products: "+avgPrice);
    }

    public void showCustomCountProducts(int count) {
        List<Product> customNumberProducts = productHashSet.stream().limit(count).toList();
        System.out.println("The first "+count+" products is/are "+customNumberProducts);
    }

    public void getSortedProductsBasedOnPrice() {
        List<Product> sortedProducts = productHashSet.stream().sorted(Comparator.comparing(Product::getProductPrice))
                .collect(Collectors.toList());
        System.out.println("***Sorted products based on price***");
        sortedProducts.stream().forEach(product -> System.out.println(product.getProductName() +" "+ product.getProductPrice()));
    }
}
