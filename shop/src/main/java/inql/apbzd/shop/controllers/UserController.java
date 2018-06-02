package inql.apbzd.shop.controllers;


import inql.apbzd.shop.commands.UserCommand;
import inql.apbzd.shop.domain.User;
import inql.apbzd.shop.services.AddressService;
import inql.apbzd.shop.services.RoleService;
import inql.apbzd.shop.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
public class UserController {

    private static final String USER_USERFORM_URL = "users/userform";
    private final UserService userService;
    private final RoleService roleService;
    private final AddressService addressService;

    public UserController(UserService userService, RoleService roleService, AddressService addressService) {
        this.userService = userService;
        this.roleService = roleService;
        this.addressService = addressService;
    }

    @RequestMapping("/users_overview")
    public String getUsersPage(Model model){
        model.addAttribute("users",userService.getUsers());
        return "users_overview";
    }

    @GetMapping("users/new")
    public String newUser(Model model){
        UserCommand userCommand = new UserCommand();
        model.addAttribute("user", userCommand);
        model.addAttribute("roles", roleService.getRoles());

        return "users/userform";
    }

    @GetMapping("users/{id}/update")
    public String updateUser(@PathVariable String id, Model model){
        model.addAttribute("user", userService.findCommandById(Long.valueOf(id)));
        model.addAttribute("roles",roleService.getRoleCommands());
        return USER_USERFORM_URL;
    }

    @PostMapping("users")
    public String saveOrUpdate(@Valid @ModelAttribute("user") UserCommand userCommand, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> log.debug(objectError.toString()));
            model.addAttribute("roles",roleService.getRoleCommands());
            return USER_USERFORM_URL;
        }
        UserCommand savedCommand = userService.saveUserCommand(userCommand);

        return "redirect:/";

    }

    @GetMapping("users/{id}/delete")
    public String deleteById(@PathVariable String id){
        log.debug("Deleting id: "+id);

        Long addressId = userService.findById(Long.valueOf(id)).getAddress().getId();
        userService.deleteById(Long.valueOf(id));
        addressService.deleteById(addressId);
        return "redirect:/";
    }
}
