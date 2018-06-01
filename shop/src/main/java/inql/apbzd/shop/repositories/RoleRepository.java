package inql.apbzd.shop.repositories;

import inql.apbzd.shop.domain.Category;
import inql.apbzd.shop.domain.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role,Long> {
}
