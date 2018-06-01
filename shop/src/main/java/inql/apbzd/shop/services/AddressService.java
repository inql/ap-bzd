package inql.apbzd.shop.services;

import inql.apbzd.shop.commands.AddressCommand;
import inql.apbzd.shop.domain.Address;

import java.util.Set;

public interface AddressService {

    Set<Address> getAddresses();
    Address findById(Long l);
    AddressCommand findCommandById(Long l);
    AddressCommand saveAddressCommand(AddressCommand addressCommand);
    void deleteById(Long idToDelete);
}
