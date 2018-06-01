package inql.apbzd.shop.repositories;

import inql.apbzd.shop.domain.Address;
import inql.apbzd.shop.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address,Long> {
}
