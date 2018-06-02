package inql.apbzd.shop.converters;

import inql.apbzd.shop.commands.SubcategoryCommand;
import inql.apbzd.shop.domain.Subcategory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SubcategoryToSubcategoryCommand implements Converter<Subcategory,SubcategoryCommand> {

    private final CategoryToCategoryCommand categoryToCategoryCommand;

    public SubcategoryToSubcategoryCommand(CategoryToCategoryCommand categoryToCategoryCommand) {
        this.categoryToCategoryCommand = categoryToCategoryCommand;
    }

    @Nullable
    @Transactional
    @Override
    public SubcategoryCommand convert(Subcategory subcategory) {
        if(subcategory==null){
            return null;
        }

        final SubcategoryCommand subcategoryCommand = new SubcategoryCommand();
        subcategoryCommand.setId(subcategory.getId());
        subcategoryCommand.setName(subcategory.getName());
        subcategoryCommand.setDescription(subcategory.getDescription());
        subcategoryCommand.setCategory(categoryToCategoryCommand.convert(subcategory.getCategory()).getId());
        subcategoryCommand.setImage(subcategory.getImage());
        return subcategoryCommand;
    }
}
