package inql.apbzd.shop.controllers;

import inql.apbzd.shop.commands.ProductCommand;
import inql.apbzd.shop.domain.Product;
import inql.apbzd.shop.services.ProductService;
import inql.apbzd.shop.services.SubcategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
public class ProductController {

    private static final String PRODUCTS_PRODUCTFORM_URL = "products/productform";
    private final ProductService productService;
    private final SubcategoryService subcategoryService;

    public ProductController(ProductService productService, SubcategoryService subcategoryService) {
        this.productService = productService;
        this.subcategoryService = subcategoryService;
    }

    @RequestMapping("/products_overview")
    public String getProductsPage(Model model){
        model.addAttribute("products",productService.getProducts());
        return "products_overview";
    }

    @GetMapping("/products/new")
    public String newProduct(Model model){
        ProductCommand productCommand = new ProductCommand();
        model.addAttribute("product", productCommand);
        model.addAttribute("subcategories",subcategoryService.getSubcategories());
        return PRODUCTS_PRODUCTFORM_URL;
    }

    @GetMapping("/products/{id}/update")
    public String updateProduct(@PathVariable String id, Model model){
        model.addAttribute("product",productService.findCommandById(Long.valueOf(id)));
        model.addAttribute("subcategories",subcategoryService.getSubcategories());
        return PRODUCTS_PRODUCTFORM_URL;
    }

    @PostMapping("products")
    public String saveOrUpdate(@Valid @ModelAttribute("product") ProductCommand productCommand, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> log.debug(objectError.toString()));
            model.addAttribute("subcategories",subcategoryService.getSubcategories());
            return PRODUCTS_PRODUCTFORM_URL;
        }
        ProductCommand savedCommand = productService.saveProductCommand(productCommand);
        return "redirect:/";
    }

    @GetMapping("products/{id}/delete")
    public String deleteById(@PathVariable String id){
        log.debug("Deleting id: "+id);

        productService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }
}
