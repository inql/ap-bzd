package inql.apbzd.shop.converters;

import inql.apbzd.shop.commands.CategoryCommand;
import inql.apbzd.shop.domain.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {


    @Override
    @Nullable
    @Transactional
    public CategoryCommand convert(Category category) {
        if(category == null){
            return null;
        }

        final CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(category.getId());
        categoryCommand.setName(category.getName());
        categoryCommand.setDescription(category.getDescription());

        return categoryCommand;
    }
}
