package inql.apbzd.shop.services;

import inql.apbzd.shop.commands.CartCommand;
import inql.apbzd.shop.domain.Cart;

import java.util.Set;

public interface CartService {

    CartCommand findByOrderIdAndProductId(Long orderId, Long productId);
    CartCommand saveCartCommand(CartCommand cartCommand);
    void deleteById(Long idToDelete, Long orderId);

}
