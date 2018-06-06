package inql.apbzd.shop.services;

import inql.apbzd.shop.commands.ProductCommand;
import inql.apbzd.shop.domain.Product;

import java.util.Set;

public interface ProductService {

    Set<Product> getProducts();
    Product findById(Long l);
    ProductCommand findCommandById(Long l);
    ProductCommand saveProductCommand(ProductCommand productCommand);
    void deleteById(Long idToDelete);

}
