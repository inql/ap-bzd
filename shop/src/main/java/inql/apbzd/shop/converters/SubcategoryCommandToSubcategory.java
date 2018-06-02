package inql.apbzd.shop.converters;

import inql.apbzd.shop.commands.SubcategoryCommand;
import inql.apbzd.shop.domain.Subcategory;
import inql.apbzd.shop.services.CategoryService;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class SubcategoryCommandToSubcategory implements Converter<SubcategoryCommand,Subcategory> {

    private final CategoryCommandToCategory categoryCommandToCategory;
    private final CategoryService categoryService;

    public SubcategoryCommandToSubcategory(CategoryCommandToCategory categoryCommandToCategory, CategoryService categoryService) {
        this.categoryCommandToCategory = categoryCommandToCategory;
        this.categoryService = categoryService;
    }

    @Synchronized
    @Nullable
    @Override
    public Subcategory convert(SubcategoryCommand subcategoryCommand) {
        if(subcategoryCommand==null)
            return null;
        final Subcategory subcategory = new Subcategory();
        subcategory.setId(subcategoryCommand.getId());
        subcategory.setName(subcategoryCommand.getName());
        subcategory.setDescription(subcategoryCommand.getDescription());
        subcategory.setCategory(categoryCommandToCategory.convert(categoryService.findCommandById(subcategoryCommand.getCategory())));
        subcategory.setImage(subcategoryCommand.getImage());
        return subcategory;
    }
}
