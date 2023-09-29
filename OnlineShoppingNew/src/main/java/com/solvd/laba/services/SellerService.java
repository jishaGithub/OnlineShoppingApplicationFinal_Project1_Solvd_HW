package com.solvd.laba.services;

import com.solvd.laba.exceptions.NotPositiveException;
import com.solvd.laba.entities.Product;
import com.solvd.laba.entities.Seller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class SellerService extends Seller {
    private static final Logger logger = LogManager.getLogger(SellerService.class);
    
    public SellerService(Seller seller) {
        super(seller);
    }

    public Product addProducts(Product product) throws NotPositiveException {
        if (product.getProductStock() < 0 | product.getProductPrice() < 0) {
            throw new NotPositiveException(product.getProductStock(), product.getProductPrice());
        }
        productHashSet.add(product);
        logger.info("Product:"+product.getProductName()+" added by seller:"+this.id);
        return product;
    }

    public void filterProductsByPrice(double price) {
        List<Product> filterProductsByPrice = productHashSet.stream().filter(product -> product.getProductPrice() > price)
                .toList();
        logger.info("The products that are priced greater than "+price+" are "+filterProductsByPrice);
    }

    public void getProductCount() {
        logger.info("Product Count: "+productHashSet.stream().count());
    }

    public void anyProductOutOfStock() {
        Boolean result = productHashSet.stream().anyMatch(product -> product.getProductStock() == 0);
        logger.info("Any product out of stock status: "+result);
    }

    public void getLeastExpensiveProduct() {
        Optional<Product> leastExpensiveProduct = productHashSet.stream().min(Comparator.comparing(Product::getProductPrice));
        if (leastExpensiveProduct.isPresent()) {
            logger.info("The least expensive product: " + leastExpensiveProduct.get().getProductName());
        }
    }

    public void getMostExpensiveProduct() {
        Optional<Product> mostExpensiveProduct = productHashSet.stream().max(Comparator.comparing(Product::getProductPrice));
        if (mostExpensiveProduct.isPresent()) {
            logger.info("The most expensive product is: "+ mostExpensiveProduct);
        }
    }

    public void getAveragePriceOfProducts() {
        double avgPrice = productHashSet.stream().mapToDouble(Product::getProductPrice).average().getAsDouble();
        logger.info("Average price of products: "+avgPrice);
    }

    public void showCustomCountProducts(int count) {
        List<Product> customNumberProducts = productHashSet.stream().limit(count).toList();
        logger.info("The first " + count + " products is/are " + customNumberProducts);
    }

    public void getSortedProductsBasedOnPrice() {
        List<Product> sortedProducts = productHashSet.stream().sorted(Comparator.comparing(Product::getProductPrice))
                .toList();
        logger.info("***Sorted products based on price***");
        sortedProducts.stream().forEach(product->System.out.println(product.getProductName()+" "+product.getProductPrice()));
    }

    public void setLoginCredentials(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.isLoginCredentialsSet = true;
    }

    public void viewProductsForSale() {
        logger.info("******* The Products List for Seller ID:"+this.id+" ********");
        for (Product product : productHashSet){
            logger.info(product);
        }
    }

    public double productValue(Product product) {
        Function<Product, Double> calculateTotalValue = p -> p.getProductPrice() * p.getProductStock();
        double totalValue = calculateTotalValue.apply(product);
        logger.info("The total value for " + product.getProductName() + " is: " + totalValue);
        return totalValue;
    }
    
}
