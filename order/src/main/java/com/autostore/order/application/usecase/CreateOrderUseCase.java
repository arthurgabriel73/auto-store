package com.autostore.order.application.usecase;


import com.autostore.order.application.port.driven.EventProducer;
import com.autostore.order.application.port.driven.OrderRepository;
import com.autostore.order.application.port.driver.CreateOrderDriverPort;
import com.autostore.order.application.port.driver.model.command.CreateOrderCommand;
import com.autostore.order.application.port.driver.model.command.CreateOrderCommandOutput;
import com.autostore.order.application.port.event.Topic;
import com.autostore.order.domain.DomainEvent;
import com.autostore.order.domain.Order;
import com.autostore.order.domain.OrderCustomer;
import com.autostore.order.domain.TransactionId;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class CreateOrderUseCase implements CreateOrderDriverPort {

    private final OrderRepository orderRepository;
    private final EventProducer eventProducer;

    @Override
    public CreateOrderCommandOutput execute(CreateOrderCommand command) {
        var newTransactionId = TransactionId.generate().value();
        var products = command.products();
        var customer = OrderCustomer.of(command.customer());
        Order orderToCreate = Order.create(
                products,
                newTransactionId,
                customer
        );
        Order createdOrder = orderRepository.save(orderToCreate);
        eventProducer.sendEvent(createOrderEvent(createdOrder), Topic.ORDER_SERVICE_ORDER_CREATED_V1.getTopic());
        return new CreateOrderCommandOutput(createdOrder);
    }

    private DomainEvent<Order> createOrderEvent(Order order) {
        return DomainEvent.<Order>
                        builder()
                .id(order.getId())
                .transactionId(order.getTransactionId())
                .payload(order)
                .createdAt(order.getCreatedAt())
                .build();
    }

}
