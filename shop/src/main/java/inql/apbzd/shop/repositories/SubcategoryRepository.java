package inql.apbzd.shop.repositories;

import inql.apbzd.shop.domain.Address;
import inql.apbzd.shop.domain.Subcategory;
import org.springframework.data.repository.CrudRepository;

public interface SubcategoryRepository extends CrudRepository<Subcategory,Long> {
}
