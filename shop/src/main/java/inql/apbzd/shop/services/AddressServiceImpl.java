package inql.apbzd.shop.services;

import inql.apbzd.shop.commands.AddressCommand;
import inql.apbzd.shop.converters.AddressCommandToAddress;
import inql.apbzd.shop.converters.AddressToAddressCommand;
import inql.apbzd.shop.domain.Address;
import inql.apbzd.shop.repositories.AddressRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressCommandToAddress addressCommandToAddress;
    private final AddressToAddressCommand addressToAddressCommand;

    public AddressServiceImpl(AddressRepository addressRepository, AddressCommandToAddress addressCommandToAddress, AddressToAddressCommand addressToAddressCommand) {
        this.addressRepository = addressRepository;
        this.addressCommandToAddress = addressCommandToAddress;
        this.addressToAddressCommand = addressToAddressCommand;
    }

    @Override
    public Set<Address> getAddresses() {
        Set<Address> addressSet = new HashSet<>();
        addressRepository.findAll().iterator().forEachRemaining(addressSet::add);
        return addressSet;
    }

    @Override
    public Address findById(Long l) {

        Optional<Address> addressOptional = addressRepository.findById(l);

        if(!addressOptional.isPresent()){
            try {
                throw new NotFoundException("Nie znaleziono adresu dla ID: "+l.toString());
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }

        return addressOptional.get();
    }

    @Override
    @Transactional
    public AddressCommand findCommandById(Long l) {
        return addressToAddressCommand.convert(findById(l));
    }

    @Override
    @Transactional
    public AddressCommand saveAddressCommand(AddressCommand addressCommand) {
        Address detachedAddress = addressCommandToAddress.convert(addressCommand);
        Address savedAddress = addressRepository.save(detachedAddress);
        return addressToAddressCommand.convert(savedAddress);
    }

    @Override
    public void deleteById(Long idToDelete) {
        addressRepository.deleteById(idToDelete);
    }
}
