package inql.apbzd.shop.converters;

import inql.apbzd.shop.commands.RoleCommand;
import inql.apbzd.shop.domain.Role;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RoleCommandToRole implements Converter<RoleCommand,Role> {


    @Override
    @Nullable
    @Synchronized
    public Role convert(RoleCommand roleCommand) {
        if(roleCommand == null){
            return null;
        }

        final Role role = new Role();
        role.setId(roleCommand.getId());
        role.setName(roleCommand.getName());
        role.setDescription(roleCommand.getDescription());

        return role;
    }
}
