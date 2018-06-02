package inql.apbzd.shop.services;

import inql.apbzd.shop.commands.SubcategoryCommand;
import inql.apbzd.shop.domain.Subcategory;

import java.util.Set;

public interface SubcategoryService {

    Set<Subcategory> getSubcategories();
    Subcategory findById(Long l);
    SubcategoryCommand findCommandById(Long l);
    SubcategoryCommand saveUserCommand(SubcategoryCommand subcategoryCommand);
    void deleteById(Long l);

}
