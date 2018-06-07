package inql.apbzd.shop.controllers;


import inql.apbzd.shop.commands.CartCommand;
import inql.apbzd.shop.services.CartService;
import inql.apbzd.shop.services.OrderService;
import inql.apbzd.shop.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@Controller
public class CartController {

    private final OrderService orderService;
    private final CartService cartService;
    private final ProductService productService;

    public CartController(OrderService orderService, CartService cartService, ProductService productService) {
        this.orderService = orderService;
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping("/orders/{orderId}/cart")
    public String listCarts(@PathVariable String orderId, Model model){
        model.addAttribute("order",orderService.findById(Long.valueOf(orderId)));

        return "orders/cart/items";
    }

    @GetMapping("/orders/{orderId}/cart/{productId}/update")
    public String updateCart(@PathVariable String orderId, @PathVariable String productId, Model model){
        CartCommand test = cartService.findByOrderIdAndProductId(Long.valueOf(orderId),Long.valueOf(productId));
        model.addAttribute("cart",test);
        model.addAttribute("products",productService.getProducts());
        return "/orders/cart/cartform";
    }

    @GetMapping("/orders/{orderId}/cart/new")
    public String newCart(@PathVariable String orderId, Model model){
        CartCommand cartCommand = new CartCommand();
        cartCommand.setOrderId(Long.valueOf(orderId));
        model.addAttribute("cart",cartCommand);
        model.addAttribute("products",productService.getProducts());
        return "/orders/cart/cartform";
    }

    @GetMapping("/orders/{orderId}/cart/{productId}/show")
    public String showCart(@PathVariable String orderId, @PathVariable String productId, Model model){
        model.addAttribute("cart",cartService.getCartByOrderIdAndProductId(Long.valueOf(orderId),Long.valueOf(productId)));
        return "orders/cart/show";
    }

    @PostMapping("carts")
    public String saveOrUpdate(@Valid @ModelAttribute("cart") CartCommand cartCommand, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> log.debug(objectError.toString()));
            model.addAttribute("products",productService.getProducts());
            return "/orders/cart/cartform";
        }
        CartCommand savedCommand = cartService.saveCartCommand(cartCommand);
        return "redirect:/orders/"+savedCommand.getOrderId()+"/cart";
    }

    @GetMapping("/orders/{orderId}/cart/{productId}/delete")
    public String deleteById(@PathVariable String orderId, @PathVariable String productId){

        cartService.deleteById(Long.valueOf(productId),Long.valueOf(orderId));
        return "redirect:/orders/"+orderId+"/cart";
    }
}
