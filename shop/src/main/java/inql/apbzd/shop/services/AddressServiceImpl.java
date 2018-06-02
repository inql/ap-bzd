package inql.apbzd.shop.services;

import inql.apbzd.shop.domain.Address;
import inql.apbzd.shop.repositories.AddressRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
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
    public void deleteById(Long idToDelete) {
        addressRepository.deleteById(idToDelete);
    }
}
