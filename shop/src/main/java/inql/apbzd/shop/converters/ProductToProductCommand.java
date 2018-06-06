package inql.apbzd.shop.converters;

import inql.apbzd.shop.commands.ProductCommand;
import inql.apbzd.shop.domain.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ProductToProductCommand implements Converter<Product,ProductCommand> {

    private final SubcategoryToSubcategoryCommand subcategoryToSubcategoryCommand;

    public ProductToProductCommand(SubcategoryToSubcategoryCommand subcategoryToSubcategoryCommand) {
        this.subcategoryToSubcategoryCommand = subcategoryToSubcategoryCommand;
    }

    @Nullable
    @Transactional
    @Override
    public ProductCommand convert(Product product) {
        if(product==null){
            return null;
        }

        final ProductCommand productCommand = new ProductCommand();
        productCommand.setId(product.getId());
        productCommand.setName(product.getName());
        productCommand.setDescription(product.getDescription());
        productCommand.setPrice(product.getPrice());
        productCommand.setSubcategory(subcategoryToSubcategoryCommand.convert(product.getSubcategory()).getId());
        return productCommand;

    }
}
