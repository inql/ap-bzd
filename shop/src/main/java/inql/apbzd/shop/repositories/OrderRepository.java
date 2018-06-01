package inql.apbzd.shop.repositories;

import inql.apbzd.shop.domain.Address;
import inql.apbzd.shop.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order,Long> {
}
