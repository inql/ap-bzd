package inql.apbzd.shop.converters;

import inql.apbzd.shop.commands.UserCommand;
import inql.apbzd.shop.domain.User;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UserCommandToUser implements Converter<UserCommand, User> {

    private final RoleCommandToRole roleConverter;
    private final AddressCommandToAddress addressConverter;

    public UserCommandToUser(RoleCommandToRole roleConverter, AddressCommandToAddress addressConverter) {
        this.roleConverter = roleConverter;
        this.addressConverter = addressConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public User convert(UserCommand userCommand) {
        if(userCommand==null){
            return null;
        }

        final User user = new User();
        user.setId(userCommand.getId());
        user.setLogin(userCommand.getLogin());
        user.setPassword(userCommand.getPassword());
        user.setName(userCommand.getName());
        user.setSurname(userCommand.getSurname());
        user.setAddress(addressConverter.convert(userCommand.getAddress()));
        user.setRole(roleConverter.convert(userCommand.getRole()));
        return user;
    }
}
