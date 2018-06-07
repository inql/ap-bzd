package inql.apbzd.shop.controllers;


import inql.apbzd.shop.commands.CategoryCommand;
import inql.apbzd.shop.services.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
public class CategoryController {

    private static final String CATEGORY_CATEGORYFORM_URL = "categories/categoryform";
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping({"/categories/categories_overview"})
    public String getRolePage(Model model){
        model.addAttribute("categories",categoryService.getCategories());
        return "/categories/categories_overview";
    }

    @GetMapping("/categories/{id}/show")
    public String showCategory(@PathVariable String id, Model model){
        model.addAttribute("category",categoryService.findCommandById(Long.valueOf(id)));
        return "categories/show";
    }

    @GetMapping("categories/new")
    public String newCategory(Model model){
        model.addAttribute("category",new CategoryCommand());
        return CATEGORY_CATEGORYFORM_URL;
    }

    @GetMapping("categories/{id}/update")
    public String updateCategory(@PathVariable String id, Model model){
        model.addAttribute("category",categoryService.findCommandById(Long.valueOf(id)));
        return CATEGORY_CATEGORYFORM_URL;
    }

    @PostMapping("categories")
    public String saveOrUpdate(@Valid @ModelAttribute("category") CategoryCommand categoryCommand, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });

            return CATEGORY_CATEGORYFORM_URL;
        }

        CategoryCommand savedCommand = categoryService.saveCategoryCommand(categoryCommand);

        return "redirect:/categories/categories_overview";
    }

    @GetMapping("categories/{id}/delete")
    public String deleteById(@PathVariable String id){
        log.debug("Deleting ID: "+id);
        categoryService.deleteById(Long.valueOf(id));
        return "redirect:/categories/categories_overview";
    }
}
