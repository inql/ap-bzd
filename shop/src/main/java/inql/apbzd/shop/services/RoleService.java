package inql.apbzd.shop.services;

import inql.apbzd.shop.commands.RoleCommand;
import inql.apbzd.shop.domain.Role;

import java.util.Set;

public interface RoleService {

    Set<Role> getRoles();
    Role findById(Long l);
    RoleCommand findCommandById(Long l);
    RoleCommand saveRoleCommand(RoleCommand addressCommand);
    void deleteById(Long idToDelete);
}
