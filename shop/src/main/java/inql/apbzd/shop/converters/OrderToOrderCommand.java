package inql.apbzd.shop.converters;

import inql.apbzd.shop.commands.OrderCommand;
import inql.apbzd.shop.domain.Order;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class OrderToOrderCommand implements Converter<Order,OrderCommand> {

    private final UserToUserCommand userToUserCommand;
    private final CartToCartCommand cartToCartCommand;

    public OrderToOrderCommand(UserToUserCommand userToUserCommand, CartToCartCommand cartToCartCommand) {
        this.userToUserCommand = userToUserCommand;
        this.cartToCartCommand = cartToCartCommand;
    }

    @Nullable
    @Transactional
    @Override
    public OrderCommand convert(Order order) {
        if(order == null)
            return null;

        final OrderCommand orderCommand = new OrderCommand();
        orderCommand.setId(order.getId());
        orderCommand.setOrderDate(order.getOrderDate());
        orderCommand.setShipDate(order.getShipDate());
        orderCommand.setIsPaid(order.getIsPaid());
        orderCommand.setUser(userToUserCommand.convert(order.getUser()).getId());
        if(order.getCartSet() != null && order.getCartSet().size() >0){
            order.getCartSet()
                    .forEach(cart -> orderCommand.getCartCommands().add(cartToCartCommand.convert(cart)));
        }

        return orderCommand;
    }
}
