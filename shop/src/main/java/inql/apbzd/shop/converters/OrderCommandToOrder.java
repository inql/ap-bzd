package inql.apbzd.shop.converters;

import inql.apbzd.shop.commands.OrderCommand;
import inql.apbzd.shop.domain.Order;
import inql.apbzd.shop.services.UserService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class OrderCommandToOrder implements Converter<OrderCommand,Order> {

    private final UserService userService;
    private final UserCommandToUser userCommandToUser;

    public OrderCommandToOrder(UserService userService, UserCommandToUser userCommandToUser) {
        this.userService = userService;
        this.userCommandToUser = userCommandToUser;
    }

    @Override
    @Transactional
    @Nullable
    public Order convert(OrderCommand orderCommand) {
        if(orderCommand == null)
            return null;
        Order order = new Order();
        order.setId(orderCommand.getId());
            order.setOrderDate(orderCommand.getOrderDate());
            order.setShipDate(orderCommand.getShipDate());
        order.setIsPaid(orderCommand.getIsPaid());
        order.setUser(userCommandToUser.convert(userService.findCommandById(orderCommand.getUser())));
//        if(orderCommand.getCartCommands() != null && orderCommand.getCartCommands().size() > 0){
//            orderCommand.getCartCommands()
//                    .forEach(cartCommand -> order.getCartSet().add(cartCommandToCart.convert(cartCommand)));
//        }
        return order;
    }
}
