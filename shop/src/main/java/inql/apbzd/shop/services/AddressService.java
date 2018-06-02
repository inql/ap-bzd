package inql.apbzd.shop.services;

import inql.apbzd.shop.domain.Address;

import java.util.Set;

public interface AddressService {

    Set<Address> getAddresses();
    Address findById(Long l);
    void deleteById(Long idToDelete);
}
