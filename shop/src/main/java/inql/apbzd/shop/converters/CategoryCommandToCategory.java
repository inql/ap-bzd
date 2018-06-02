package inql.apbzd.shop.converters;

import inql.apbzd.shop.commands.CategoryCommand;
import inql.apbzd.shop.domain.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand,Category> {

    @Transactional
    @Nullable
    @Override
    public Category convert(CategoryCommand categoryCommand) {
        if(categoryCommand == null){
            return null;
        }

        Category category = new Category();
        category.setId(categoryCommand.getId());
        category.setName(categoryCommand.getName());
        category.setDescription(categoryCommand.getDescription());

        return category;
    }
}
