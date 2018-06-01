package inql.apbzd.shop.controllers;


import inql.apbzd.shop.commands.UserCommand;
import inql.apbzd.shop.services.AddressService;
import inql.apbzd.shop.services.RoleService;
import inql.apbzd.shop.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String newRole(Model model){
        model.addAttribute("user", new UserCommand());
        model.addAttribute("roles", roleService.getRoles());

        return "users/userform";
    }
}
