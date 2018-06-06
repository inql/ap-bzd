package inql.apbzd.shop.converters;

import inql.apbzd.shop.commands.CartCommand;
import inql.apbzd.shop.domain.Cart;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CartToCartCommand implements Converter<Cart,CartCommand> {

    @Override
    @Transactional
    @Nullable
    public CartCommand convert(Cart cart) {
        if(cart == null)
            return null;

        CartCommand cartCommand = new CartCommand();
        cartCommand.setOrderId((cart.getOrder()).getId());
        cartCommand.setProductId((cart.getProduct()).getId());
        cartCommand.setQuantity(cart.getQuantity());

        return cartCommand;
    }
}
