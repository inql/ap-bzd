package inql.apbzd.shop.controllers;


import inql.apbzd.shop.commands.SubcategoryCommand;
import inql.apbzd.shop.services.CategoryService;
import inql.apbzd.shop.services.ImageService;
import inql.apbzd.shop.services.SubcategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Slf4j
@Controller
public class SubcategoryController {

    private static final String SUBCATEGORY_SUBCATEGORYFORM_URL = "subcategories/subcategoryform";
    private final SubcategoryService subcategoryService;
    private final CategoryService categoryService;
    private final ImageService imageService;

    public SubcategoryController(SubcategoryService subcategoryService, CategoryService categoryService, ImageService imageService) {
        this.subcategoryService = subcategoryService;
        this.categoryService = categoryService;
        this.imageService = imageService;
    }

    @RequestMapping("/subcategories_overview")
    public String getSubcategoriesPage(Model model){
        model.addAttribute("subcategories",subcategoryService.getSubcategories());
        return "subcategories_overview";
    }

    @GetMapping("subcategories/new")
    public String newSubcategory(Model model){
        SubcategoryCommand subcategoryCommand = new SubcategoryCommand();
        model.addAttribute("subcategory", subcategoryCommand);
        model.addAttribute("categories", categoryService.getCategories());

        return SUBCATEGORY_SUBCATEGORYFORM_URL;
    }

    @GetMapping("subcategories/{id}/update")
    public String updateUser(@PathVariable String id, Model model){
        model.addAttribute("subcategory", subcategoryService.findCommandById(Long.valueOf(id)));
        model.addAttribute("categories",categoryService.getCategoryCommands());
        return SUBCATEGORY_SUBCATEGORYFORM_URL;
    }

    @PostMapping("subcategories")
    public String saveOrUpdate(@Valid @ModelAttribute("subcategory") SubcategoryCommand subcategoryCommand, BindingResult bindingResult, @RequestParam("imagefile") MultipartFile file, Model model){
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> log.debug(objectError.toString()));
            model.addAttribute("categories",categoryService.getCategoryCommands());
            return SUBCATEGORY_SUBCATEGORYFORM_URL;
        }
        SubcategoryCommand savedCommand = subcategoryService.saveSubcategoryCommand(subcategoryCommand);
        imageService.saveImageFile(savedCommand.getId(),file);

        return "redirect:/";

    }
}
