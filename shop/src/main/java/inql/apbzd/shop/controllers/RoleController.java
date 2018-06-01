package inql.apbzd.shop.controllers;


import inql.apbzd.shop.commands.RoleCommand;
import inql.apbzd.shop.services.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
public class RoleController {

    private static final String ROLE_ROLEFORM_URL = "roles/roleform";
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping({"/roles_overview"})
    public String getRolePage(Model model){
        model.addAttribute("roles",roleService.getRoles());
        return "roles_overview";
    }

    @GetMapping("roles/new")
    public String newRole(Model model){
        model.addAttribute("role", new RoleCommand());

        return "roles/roleform";
    }

    @GetMapping("roles/{id}/update")
    public String updateRole(@PathVariable String id, Model model){
        model.addAttribute("role",roleService.findCommandById(Long.valueOf(id)));
        return ROLE_ROLEFORM_URL;
    }

    @PostMapping("roles")
    public String saveOrUpdate(@Valid @ModelAttribute("role") RoleCommand roleCommand, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });

            return ROLE_ROLEFORM_URL;
        }

        RoleCommand savedCommand = roleService.saveRoleCommand(roleCommand);

        return "redirect:/";

    }

    @GetMapping("roles/{id}/delete")
    public String deleteById(@PathVariable String id){
        log.debug("Deleting id: "+id);

        roleService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }

}
