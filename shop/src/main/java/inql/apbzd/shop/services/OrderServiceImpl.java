package inql.apbzd.shop.services;

import inql.apbzd.shop.commands.OrderCommand;
import inql.apbzd.shop.converters.OrderCommandToOrder;
import inql.apbzd.shop.converters.OrderToOrderCommand;
import inql.apbzd.shop.domain.Order;
import inql.apbzd.shop.repositories.OrderRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderToOrderCommand orderToOrderCommand;
    private final OrderCommandToOrder orderCommandToOrder;

    public OrderServiceImpl(OrderRepository orderRepository, OrderToOrderCommand orderToOrderCommand, OrderCommandToOrder orderCommandToOrder) {
        this.orderRepository = orderRepository;
        this.orderToOrderCommand = orderToOrderCommand;
        this.orderCommandToOrder = orderCommandToOrder;
    }

    @Override
    public Set<Order> getOrders() {
        Set<Order> orderSet = new HashSet<>();
        orderRepository.findAll().iterator().forEachRemaining(orderSet::add);
        return orderSet;
    }

    @Override
    public Order findById(Long l) {
        Optional<Order> orderOptional = orderRepository.findById(l);
        if(!orderOptional.isPresent()){
            try {
                throw new NotFoundException("Nie znaleziono zamówienia dla ID: "+l.toString());
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }

        return orderOptional.get();
    }

    @Override
    @Transactional
    public OrderCommand findCommandById(Long l) {
        return orderToOrderCommand.convert(findById(l));
    }

    @Override
    @Transactional
    public OrderCommand saveOrderCommand(OrderCommand orderCommand) {
        Order detachedOrder = orderCommandToOrder.convert(orderCommand);
        Order savedOrder = orderRepository.save(detachedOrder);
        return orderToOrderCommand.convert(savedOrder);
    }

    @Override
    public void deleteById(Long idToDelete) {
        orderRepository.deleteById(idToDelete);
    }
}
