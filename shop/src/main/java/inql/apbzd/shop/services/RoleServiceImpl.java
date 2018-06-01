package inql.apbzd.shop.services;

import inql.apbzd.shop.commands.AddressCommand;
import inql.apbzd.shop.commands.RoleCommand;
import inql.apbzd.shop.converters.RoleCommandToRole;
import inql.apbzd.shop.converters.RoleToRoleCommand;
import inql.apbzd.shop.domain.Role;
import inql.apbzd.shop.repositories.RoleRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleToRoleCommand roleToRoleCommand;
    private final RoleCommandToRole roleCommandToRole;

    public RoleServiceImpl(RoleRepository roleRepository, RoleToRoleCommand roleToRoleCommand, RoleCommandToRole roleCommandToRole) {
        this.roleRepository = roleRepository;
        this.roleToRoleCommand = roleToRoleCommand;
        this.roleCommandToRole = roleCommandToRole;
    }

    @Override
    public Set<Role> getRoles() {
        Set<Role> roleSet = new HashSet<>();
        roleRepository.findAll().iterator().forEachRemaining(roleSet::add);
        return roleSet;
    }

    @Override
    public Role findById(Long l) {
        Optional<Role> addressOptional = roleRepository.findById(l);

        if(!addressOptional.isPresent()){
            try {
                throw new NotFoundException("Nie znaleziono roli dla ID: "+l.toString());
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }

        return addressOptional.get();
    }

    @Override
    @Transactional
    public RoleCommand findCommandById(Long l) {
        return roleToRoleCommand.convert(findById(l));
    }

    @Override
    @Transactional
    public RoleCommand saveRoleCommand(RoleCommand roleCommand) {
        Role detachedRole = roleCommandToRole.convert(roleCommand);
        Role savedRole = roleRepository.save(detachedRole);
        return roleToRoleCommand.convert(savedRole);
    }

    @Override
    public void deleteById(Long idToDelete) {
        roleRepository.deleteById(idToDelete);
    }
}
