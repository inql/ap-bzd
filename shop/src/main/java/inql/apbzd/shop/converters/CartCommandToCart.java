package inql.apbzd.shop.converters;

import inql.apbzd.shop.commands.CartCommand;
import inql.apbzd.shop.domain.Cart;
import inql.apbzd.shop.services.OrderService;
import inql.apbzd.shop.services.ProductService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CartCommandToCart implements Converter<CartCommand, Cart> {

    private final OrderCommandToOrder orderCommandToOrder;
    private final ProductCommandToProduct productCommandToProduct;
    private final ProductService productService;
    private final OrderService orderService;

    public CartCommandToCart(OrderCommandToOrder orderCommandToOrder, ProductCommandToProduct productCommandToProduct, ProductService productService, OrderService orderService) {
        this.orderCommandToOrder = orderCommandToOrder;
        this.productCommandToProduct = productCommandToProduct;
        this.productService = productService;
        this.orderService = orderService;
    }

    @Override
    @Transactional
    @Nullable
    public Cart convert(CartCommand cartCommand) {
        if(cartCommand == null)
            return null;

        Cart cart = new Cart();
        cart.setOrder(orderCommandToOrder.convert(orderService.findCommandById(cartCommand.getOrderId())));
        cart.setProduct(productCommandToProduct.convert(productService.findCommandById(cartCommand.getProductId())));
        cart.setQuantity(cartCommand.getQuantity());
        return cart;
    }
}
