package inql.apbzd.shop.services;

import inql.apbzd.shop.commands.CartCommand;
import inql.apbzd.shop.converters.CartCommandToCart;
import inql.apbzd.shop.converters.CartToCartCommand;
import inql.apbzd.shop.domain.Cart;
import inql.apbzd.shop.domain.CartId;
import inql.apbzd.shop.domain.Order;
import inql.apbzd.shop.repositories.CartRepository;
import inql.apbzd.shop.repositories.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class CartServiceImpl implements CartService {

    private final CartToCartCommand cartToCartCommand;
    private final CartCommandToCart cartCommandToCart;
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    public CartServiceImpl(CartToCartCommand cartToCartCommand, CartCommandToCart cartCommandToCart, OrderRepository orderRepository, CartRepository cartRepository) {
        this.cartToCartCommand = cartToCartCommand;
        this.cartCommandToCart = cartCommandToCart;
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    @Transactional
    public CartCommand findByOrderIdAndProductId(Long orderId, Long productId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if(!orderOptional.isPresent()){
            log.error("nie znaleziono");
        }
        Order order = orderOptional.get();
        Optional<CartCommand> cartCommandOptional = order.getCartSet().stream()
                .filter(cart -> cart.getProduct().getId().equals(productId))
                .map(cartToCartCommand::convert).findFirst();

        if(!cartCommandOptional.isPresent()){
            log.error("nie znaleziono");
        }

        return cartCommandOptional.get();
    }

    @Override
    public Cart getCartByOrderIdAndProductId(Long orderId, Long productId){
        return cartCommandToCart.convert(findByOrderIdAndProductId(orderId,productId));
    }

    @Override
    @Transactional
    public CartCommand saveCartCommand(CartCommand cartCommand) {
        Cart detachedCart = cartCommandToCart.convert(cartCommand);
        Cart savedCart = cartRepository.save(detachedCart);
        return cartToCartCommand.convert(savedCart);
    }

    @Override
    public void deleteById(Long idToDelete, Long orderId) {
        Cart toDelete = cartCommandToCart.convert(findByOrderIdAndProductId(orderId,idToDelete));
        cartRepository.delete(toDelete);
    }
}
