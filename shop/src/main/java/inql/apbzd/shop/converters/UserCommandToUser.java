package inql.apbzd.shop.converters;

import inql.apbzd.shop.commands.UserCommand;
import inql.apbzd.shop.domain.Address;
import inql.apbzd.shop.domain.User;
import inql.apbzd.shop.services.AddressService;
import inql.apbzd.shop.services.RoleService;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UserCommandToUser implements Converter<UserCommand, User> {

    private final RoleCommandToRole roleConverter;
    private final RoleService roleService;
    private final AddressService addressService;

    public UserCommandToUser(RoleCommandToRole roleConverter, RoleService roleService, AddressService addressService) {
        this.roleConverter = roleConverter;
        this.roleService = roleService;
        this.addressService = addressService;
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
        user.setEmail(userCommand.getEmail());
        Address address;
        if(userCommand.getAddressId()==null){
            address = new Address();
        }else{
            address = addressService.findById(userCommand.getAddressId());
        }

        address.setStreetName(userCommand.getStreetName());
        address.setPostalCode(userCommand.getPostalCode());
        address.setCity(userCommand.getCity());
        address.setHoNumber(userCommand.getHoNumber());
        address.setApaNumber(userCommand.getApaNumber());

        user.setAddress(address);
        user.setRole(roleConverter.convert(roleService.findCommandById(userCommand.getRole())));
        return user;
    }
}
