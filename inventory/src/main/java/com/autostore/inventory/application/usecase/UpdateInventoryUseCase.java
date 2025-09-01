package com.autostore.inventory.application.usecase;


import com.autostore.inventory.application.exception.DuplicatedActivityException;
import com.autostore.inventory.application.port.driven.ActivityRepository;
import com.autostore.inventory.application.port.driven.EventProducer;
import com.autostore.inventory.application.port.driven.InventoryRepository;
import com.autostore.inventory.application.port.driver.UpdateInventoryDriverPort;
import com.autostore.inventory.application.port.driver.model.command.UpdateInventoryCommand;
import com.autostore.inventory.application.port.event.Order;
import com.autostore.inventory.application.port.event.OrderEvent;
import com.autostore.inventory.application.port.event.OrderProducts;
import com.autostore.inventory.application.port.event.Topic;
import com.autostore.inventory.domain.Activity;
import com.autostore.inventory.domain.Inventory;
import com.autostore.inventory.domain.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
public class UpdateInventoryUseCase implements UpdateInventoryDriverPort {

    private final ActivityRepository activityRepository;
    private final InventoryRepository inventoryRepository;
    private final EventProducer eventProducer;

    @Override
    public void execute(UpdateInventoryCommand command) {
        var event = command.event();
        try {
            requireActivityNotExists(event);
            registerActivity(event);
            updateInventory(event.getPayload());
            eventProducer.sendEvent(event, Topic.INVENTORY_SERVICE_INVENTORY_UPDATED_V1.getTopic());
        } catch (Exception ex) {
            log.error("Error trying to update inventory: ", ex);
            eventProducer.sendEvent(event, Topic.INVENTORY_SERVICE_INVENTORY_FAILED_V1.getTopic());
        }
    }

    private void requireActivityNotExists(OrderEvent event) {
        var orderId = event.getPayload().id();
        var transactionId = event.getPayload().transactionId();
        if (activityRepository.existsByOrderIdAndTransactionId(orderId, transactionId))
            throw new DuplicatedActivityException(
                    "This activity already exists for orderId: "
                            + orderId
                            + " and transactionId: "
                            + transactionId);
    }

    private void registerActivity(OrderEvent event) {
        event
                .getPayload()
                .products()
                .forEach(orderProduct -> {
                    var inventory = findInventoryByProductCode(orderProduct.product().getCode());
                    var activity = createInventoryActivity(event, orderProduct, inventory);
                    activityRepository.save(activity);
                });
    }

    private Inventory findInventoryByProductCode(String productCode) {
        return inventoryRepository
                .findByProductCode(productCode)
                .orElseThrow(() -> new ValidationException("Inventory not found by informed product."));
    }

    private Activity createInventoryActivity(
            OrderEvent event,
            OrderProducts product,
            Inventory inventory) {
        return Activity
                .builder()
                .inventory(inventory)
                .oldQuantity(inventory.getAvailableQuantity())
                .orderQuantity(product.quantity())
                .newQuantity(inventory.getAvailableQuantity() - product.quantity())
                .orderId(event.getPayload().id())
                .transactionId(event.getPayload().transactionId())
                .build();
    }

    private void updateInventory(Order order) {
        order.products().forEach(orderProduct -> {
            Inventory inventory = findInventoryByProductCode(orderProduct.product().getCode());
            inventory.decreaseQuantityBy(orderProduct.quantity());
            inventoryRepository.save(inventory);
        });
    }

    @Override
    public void rollback(OrderEvent event) {
        try {
            returnInventoryToPreviousValues(event);
            eventProducer.sendEvent(event, Topic.INVENTORY_SERVICE_INVENTORY_ROLLBACK_SUCCESS_V1.getTopic());
        } catch (Exception ex) {
            log.error("Error trying to rollback inventory: ".concat(ex.getMessage()));
            eventProducer.sendEvent(event, Topic.INVENTORY_SERVICE_INVENTORY_ROLLBACK_FAILED_V1.getTopic());
        }
    }

    private void returnInventoryToPreviousValues(OrderEvent event) {
        var orderId = event.getPayload().id();
        var transactionId = event.getPayload().transactionId();
        activityRepository
                .findByOrderIdAndTransactionId(orderId, transactionId)
                .forEach(activity -> {
                    Inventory inventory = activity.inventory();
                    inventory.increaseQuantityBy(activity.orderQuantity());
                    inventoryRepository.save(inventory);
                });
    }

}
