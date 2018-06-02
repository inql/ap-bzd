package inql.apbzd.shop.services;

import inql.apbzd.shop.commands.UserCommand;
import inql.apbzd.shop.domain.User;

import java.util.Set;

public interface UserService {

    Set<User> getUsers();
    User findById(Long l);
    UserCommand findCommandById(Long l);
    UserCommand saveUserCommand(UserCommand userCommand);
    void deleteById(Long idToDelete);

}
