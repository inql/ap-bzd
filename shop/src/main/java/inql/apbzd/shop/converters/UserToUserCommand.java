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
    private final AddressToAddressCommand addressConverter;

    public UserToUserCommand(RoleToRoleCommand roleConverter, AddressToAddressCommand addressConverter) {
        this.roleConverter = roleConverter;
        this.addressConverter = addressConverter;
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
        userCommand.setAddress(addressConverter.convert(user.getAddress()));
        userCommand.setRole(roleConverter.convert(user.getRole()));
        return userCommand;
    }
}
