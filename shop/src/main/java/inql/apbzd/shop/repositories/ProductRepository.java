package inql.apbzd.shop.repositories;

import inql.apbzd.shop.domain.Order;
import inql.apbzd.shop.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product,Long> {
}
