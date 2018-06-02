package inql.apbzd.shop.controllers;


import inql.apbzd.shop.services.CategoryService;
import inql.apbzd.shop.services.SubcategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class SubcategoryController {

    private static final String SUBCATEGORY_SUBCATEGORYFORM_URL = "subcategories/subcategoryform";
    private final SubcategoryService subcategoryService;
    private final CategoryService categoryService;

    public SubcategoryController(SubcategoryService subcategoryService, CategoryService categoryService) {
        this.subcategoryService = subcategoryService;
        this.categoryService = categoryService;
    }

    @RequestMapping("/subcategories_overview")
    public String getSubcategoriesPage(Model model){
        model.addAttribute("subcategories",subcategoryService.getSubcategories());
        return "subcategories_overview";
    }
}
