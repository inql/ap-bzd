package inql.apbzd.shop.converters;

import inql.apbzd.shop.commands.AddressCommand;
import inql.apbzd.shop.domain.Address;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AddressToAddressCommand implements Converter<Address,AddressCommand> {

    @Override
    @Nullable
    @Transactional
    public AddressCommand convert(Address address) {
        if(address == null){
            return null;
        }

        final AddressCommand addressCommand = new AddressCommand();
        address.setId(addressCommand.getId());
        address.setStreetName(addressCommand.getStreetName());
        address.setApaNumber(addressCommand.getApaNumber());
        address.setHoNumber(addressCommand.getHoNumber());
        address.setCity(addressCommand.getCity());
        address.setPostalCode(addressCommand.getPostalCode());

        return addressCommand;
    }
}
