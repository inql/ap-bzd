package inql.apbzd.shop.repositories;

import inql.apbzd.shop.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
}
