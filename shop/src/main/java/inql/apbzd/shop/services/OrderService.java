package inql.apbzd.shop.services;

import inql.apbzd.shop.commands.OrderCommand;
import inql.apbzd.shop.domain.Order;

import java.util.Set;

public interface OrderService {

    Set<Order> getOrders();
    Order findById(Long l);
    OrderCommand findCommandById(Long l);
    OrderCommand saveOrderCommand(OrderCommand orderCommand);
    void deleteById(Long idToDelete);

}
