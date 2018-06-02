package inql.apbzd.shop.services;

import inql.apbzd.shop.commands.CategoryCommand;
import inql.apbzd.shop.domain.Category;

import java.util.Set;

public interface CategoryService {

    Set<Category> getCategories();
    Set<CategoryCommand> getCategoryCommands();
    Category findById(Long l);
    CategoryCommand findCommandById(Long l);
    CategoryCommand saveCategoryCommand(CategoryCommand categoryCommand);
    void deleteById(Long idToDelete);


}
