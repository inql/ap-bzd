package inql.apbzd.shop.repositories;

import inql.apbzd.shop.domain.Address;
import inql.apbzd.shop.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Long> {
}
