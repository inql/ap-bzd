package inql.apbzd.shop.controllers;


import inql.apbzd.shop.commands.OrderCommand;
import inql.apbzd.shop.services.OrderService;
import inql.apbzd.shop.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
public class OrderController {

    private static final String ORDERS_ORDERFORM_URL = "orders/orderform";
    private final OrderService orderService;
    private final UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @RequestMapping("/orders_overview")
    public String getOrdersPage(Model model){
        model.addAttribute("orders", orderService.getOrders());
        return "orders_overview";
    }

    @GetMapping("/orders/new")
    public String newOrder(Model model){
        OrderCommand orderCommand = new OrderCommand();
        model.addAttribute("order", orderCommand);
        model.addAttribute("users", userService.getUsers());
        return ORDERS_ORDERFORM_URL;
    }

    @GetMapping("/orders/{id}/update")
    public String updateOrder(@PathVariable String id, Model model){
        model.addAttribute("order", orderService.findCommandById(Long.valueOf(id)));
        model.addAttribute("users", userService.getUsers());
        return ORDERS_ORDERFORM_URL;
    }

    @PostMapping("orders")
    public String saveOrUpdate(@Valid @ModelAttribute("order") OrderCommand orderCommand, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> log.debug(objectError.toString()));
            model.addAttribute("users", userService.getUsers());
            return ORDERS_ORDERFORM_URL;
        }
        OrderCommand savedCommand = orderService.saveOrderCommand(orderCommand);
        return "redirect:/";
    }

    @GetMapping("orders/{id}/delete")
    public String deleteById(@PathVariable String id){
        log.debug("Deleting id: "+id);

        orderService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }



}
