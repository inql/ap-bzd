package inql.apbzd.shop.services;

import inql.apbzd.shop.commands.UserCommand;
import inql.apbzd.shop.converters.UserCommandToUser;
import inql.apbzd.shop.converters.UserToUserCommand;
import inql.apbzd.shop.domain.User;
import inql.apbzd.shop.repositories.AddressRepository;
import inql.apbzd.shop.repositories.UserRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Slf4j
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final UserToUserCommand userToUserCommand;
    private final UserCommandToUser userCommandToUser;

    public UserServiceImpl(UserRepository userRepository, AddressRepository addressRepository, UserToUserCommand userToUserCommand, UserCommandToUser userCommandToUser) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.userToUserCommand = userToUserCommand;
        this.userCommandToUser = userCommandToUser;
    }

    @Override
    public Set<User> getUsers() {
        Set<User> userSet = new HashSet<>();
        userRepository.findAll().iterator().forEachRemaining(userSet::add);
        return userSet;
    }

    @Override
    public User findById(Long l) {

        Optional<User> userOptional = userRepository.findById(l);

        if(!userOptional.isPresent()){
            try {
                throw new NotFoundException("Nie znaleziono użytkownika dla ID: "+l.toString());
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }

        return userOptional.get();

    }

    @Override
    @Transactional
    public UserCommand findCommandById(Long l) {
        return userToUserCommand.convert(findById(l));
    }

    @Override
    @Transactional
    public UserCommand saveUserCommand(UserCommand userCommand) {
        User detachedUser = userCommandToUser.convert(userCommand);
        addressRepository.save(detachedUser.getAddress());
        User savedUser = userRepository.save(detachedUser);
        return  userToUserCommand.convert(savedUser);
    }

    @Override
    public void deleteById(Long idToDelete) {
        userRepository.deleteById(idToDelete);
    }
}
