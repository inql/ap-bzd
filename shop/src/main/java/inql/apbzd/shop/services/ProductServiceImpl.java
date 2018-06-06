package inql.apbzd.shop.services;

import inql.apbzd.shop.commands.ProductCommand;
import inql.apbzd.shop.converters.ProductCommandToProduct;
import inql.apbzd.shop.converters.ProductToProductCommand;
import inql.apbzd.shop.domain.Product;
import inql.apbzd.shop.repositories.ProductRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductToProductCommand productToProductCommand;
    private final ProductCommandToProduct productCommandToProduct;

    public ProductServiceImpl(ProductRepository productRepository, ProductToProductCommand productToProductCommand, ProductCommandToProduct productCommandToProduct) {
        this.productRepository = productRepository;
        this.productToProductCommand = productToProductCommand;
        this.productCommandToProduct = productCommandToProduct;
    }


    @Override
    public Set<Product> getProducts() {
        Set<Product> productSet = new HashSet<>();
        productRepository.findAll().iterator().forEachRemaining(productSet::add);
        return productSet;
    }

    @Override
    public Product findById(Long l) {

        Optional<Product> productOptional = productRepository.findById(l);
        if(!productOptional.isPresent()){
            try {
                throw new NotFoundException("Nie znaleziono produktu dla ID: "+l.toString());
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }

        return productOptional.get();

    }

    @Override
    @Transactional
    public ProductCommand findCommandById(Long l) {
        return productToProductCommand.convert(findById(l));
    }

    @Override
    @Transactional
    public ProductCommand saveProductCommand(ProductCommand productCommand){
        Product detachedProduct = productCommandToProduct.convert(productCommand);
        Product savedProduct = productRepository.save(detachedProduct);
        return productToProductCommand.convert(savedProduct);
    }

    @Override
    public void deleteById(Long idToDelete) {
        productRepository.deleteById(idToDelete);
    }
}
