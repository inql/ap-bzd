package inql.apbzd.shop.converters;

import inql.apbzd.shop.commands.RoleCommand;
import inql.apbzd.shop.domain.Role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RoleToRoleCommand implements Converter<Role,RoleCommand> {

    @Override
    @Nullable
    @Transactional
    public RoleCommand convert(Role role) {
        if(role == null){
            return null;
        }

        final RoleCommand roleCommand = new RoleCommand();
        roleCommand.setId(role.getId());
        roleCommand.setName(role.getName());
        roleCommand.setDescription(role.getDescription());

        return roleCommand;
    }
}
