package inql.apbzd.shop.converters;

import inql.apbzd.shop.commands.AddressCommand;
import inql.apbzd.shop.domain.Address;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AddressCommandToAddress implements Converter<AddressCommand,Address> {


    @Override
    @Nullable
    @Synchronized
    public Address convert(AddressCommand addressCommand) {
        if(addressCommand == null){
            return null;
        }

        final Address address = new Address();
        address.setId(addressCommand.getId());
        address.setStreetName(addressCommand.getStreetName());
        address.setApaNumber(addressCommand.getApaNumber());
        address.setHoNumber(addressCommand.getHoNumber());
        address.setCity(addressCommand.getCity());
        address.setPostalCode(addressCommand.getPostalCode());

        return address;
    }
}
