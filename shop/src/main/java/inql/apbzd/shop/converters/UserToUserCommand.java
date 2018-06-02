package inql.apbzd.shop.converters;

import inql.apbzd.shop.commands.UserCommand;
import inql.apbzd.shop.domain.User;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UserToUserCommand implements Converter<User, UserCommand> {

    private final RoleToRoleCommand roleConverter;

    public UserToUserCommand(RoleToRoleCommand roleConverter) {
        this.roleConverter = roleConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public UserCommand convert(User user) {
        if(user==null){
            return null;
        }

        final UserCommand userCommand = new UserCommand();
        userCommand.setId(user.getId());
        userCommand.setLogin(user.getLogin());
        userCommand.setPassword(user.getPassword());
        userCommand.setName(user.getName());
        userCommand.setSurname(user.getSurname());
        userCommand.setEmail(user.getEmail());
        userCommand.setAddressId(user.getAddress().getId());
        userCommand.setStreetName(user.getAddress().getStreetName());
        userCommand.setApaNumber(user.getAddress().getApaNumber());
        userCommand.setHoNumber(user.getAddress().getHoNumber());
        userCommand.setCity(user.getAddress().getCity());
        userCommand.setPostalCode(user.getAddress().getPostalCode());
        userCommand.setRole(roleConverter.convert(user.getRole()).getId());
        return userCommand;
    }
}
