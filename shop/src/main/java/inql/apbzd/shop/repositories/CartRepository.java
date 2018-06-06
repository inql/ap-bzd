package inql.apbzd.shop.repositories;

import inql.apbzd.shop.domain.Cart;
import inql.apbzd.shop.domain.CartId;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart,CartId> {
}
