package inql.apbzd.shop.services;


import inql.apbzd.shop.commands.CategoryCommand;
import inql.apbzd.shop.converters.CategoryCommandToCategory;
import inql.apbzd.shop.converters.CategoryToCategoryCommand;
import inql.apbzd.shop.domain.Category;
import inql.apbzd.shop.repositories.CategoryRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final CategoryToCategoryCommand categoryToCategoryCommand;
    private final CategoryCommandToCategory categoryCommandToCategory;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryToCategoryCommand categoryToCategoryCommand, CategoryCommandToCategory categoryCommandToCategory) {
        this.categoryRepository = categoryRepository;
        this.categoryToCategoryCommand = categoryToCategoryCommand;
        this.categoryCommandToCategory = categoryCommandToCategory;
    }

    @Override
    public Set<Category> getCategories() {
        Set<Category> categorySet = new HashSet<>();
        categoryRepository.findAll().iterator().forEachRemaining(categorySet::add);
        return categorySet;
    }

    @Override
    public Set<CategoryCommand> getCategoryCommands() {
        Set<CategoryCommand> categoryCommands = new HashSet<>();
        getCategories().iterator().forEachRemaining(category -> categoryCommands.add(categoryToCategoryCommand.convert(category)));
        return categoryCommands;
    }

    @Override
    public Category findById(Long l) {
        Optional<Category> categoryOptional = categoryRepository.findById(l);

        if(!categoryOptional.isPresent()){
            try {
                throw new NotFoundException("Nie znaleziono kategorii dla ID: "+l.toString());
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }

        return categoryOptional.get();
    }

    @Override
    @Transactional
    public CategoryCommand findCommandById(Long l) {
        return categoryToCategoryCommand.convert(findById(l));
    }

    @Override
    @Transactional
    public CategoryCommand saveCategoryCommand(CategoryCommand categoryCommand) {
        Category detachedCategory = categoryCommandToCategory.convert(categoryCommand);
        Category savedCategory = categoryRepository.save(detachedCategory);
        return categoryToCategoryCommand.convert(savedCategory);
    }

    @Override
    public void deleteById(Long idToDelete) {
        categoryRepository.deleteById(idToDelete);
    }
}
