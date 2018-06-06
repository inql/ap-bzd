package inql.apbzd.shop.converters;

import inql.apbzd.shop.commands.ProductCommand;
import inql.apbzd.shop.domain.Product;
import inql.apbzd.shop.services.SubcategoryService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ProductCommandToProduct implements Converter<ProductCommand,Product> {

    private final SubcategoryCommandToSubcategory subcategoryCommandToSubcategory;
    private final SubcategoryService subcategoryService;
    public ProductCommandToProduct(SubcategoryCommandToSubcategory subcategoryCommandToSubcategory, SubcategoryService subcategoryService) {
        this.subcategoryCommandToSubcategory = subcategoryCommandToSubcategory;
        this.subcategoryService = subcategoryService;
    }

    @Nullable
    @Transactional
    @Override
    public Product convert(ProductCommand productCommand) {
        if(productCommand==null){
            return null;
        }

        final Product product = new Product();
        product.setId(productCommand.getId());
        product.setName(productCommand.getName());
        product.setDescription(productCommand.getDescription());
        product.setPrice(productCommand.getPrice());
        product.setSubcategory(subcategoryCommandToSubcategory.convert(subcategoryService.findCommandById(productCommand.getSubcategory())));
        return product;

    }
}
