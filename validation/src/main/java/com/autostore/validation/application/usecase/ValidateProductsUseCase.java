package com.autostore.validation.application.usecase;


import com.autostore.validation.application.exception.ProductNotRegisteredException;
import com.autostore.validation.application.exception.ValidationConflictException;
import com.autostore.validation.application.port.driven.EventProducer;
import com.autostore.validation.application.port.driven.ValidationProductRepository;
import com.autostore.validation.application.port.driven.ValidationRepository;
import com.autostore.validation.application.port.driver.ValidateProductsDriverPort;
import com.autostore.validation.application.port.driver.model.command.ValidateProductsCommand;
import com.autostore.validation.application.port.event.OrderEvent;
import com.autostore.validation.application.port.event.Topic;
import com.autostore.validation.domain.Validation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
public class ValidateProductsUseCase implements ValidateProductsDriverPort {

    private final ValidationRepository validationRepository;
    private final ValidationProductRepository validationProductRepository;
    private final EventProducer eventProducer;

    @Override
    public void execute(ValidateProductsCommand command) {
        var event = command.event();
        try {
            requireValidationNotExists(event);
            requireProductsExists(event);
            createValidation(event, true);
            eventProducer.sendEvent(event, Topic.VALIDATION_SERVICE_VALIDATION_UPDATED_V1.getTopic());
        } catch (Exception ex) {
            log.error("Error executing products validation: ", ex);
            eventProducer.sendEvent(event, Topic.VALIDATION_SERVICE_VALIDATION_FAILED_V1.getTopic());
        }
    }

    private void requireValidationNotExists(OrderEvent event) {
        var orderId = event.getPayload().id();
        var transactionId = event.getPayload().transactionId();
        if (validationRepository.existsByOrderIdAndTransactionId(orderId, transactionId))
            throw new ValidationConflictException(
                    "There's another transactionId for this validation."
            );
    }

    private void requireProductsExists(OrderEvent event) {
        var products = event.getPayload().products();
        products.forEach(product -> {
                    if (!validationProductRepository.existsByCode(product.product().code()))
                        throw new ProductNotRegisteredException(
                                "Product with code " + product.product().code() + " is not registered for validation.");
                }
        );
    }

    private void createValidation(OrderEvent event, boolean success) {
        var validation = Validation
                .builder()
                .orderId(event.getPayload().id())
                .transactionId(event.getPayload().transactionId())
                .createdAt(event.getCreatedAt())
                .updatedAt(event.getCreatedAt())
                .success(success)
                .build();
        validationRepository.save(validation);
    }

    @Override
    public void rollback(OrderEvent event) {
        try {
            markValidationAsFailed(event);
            eventProducer.sendEvent(event, Topic.VALIDATION_SERVICE_VALIDATION_ROLLBACK_SUCCESS_V1.getTopic());
        } catch (Exception ex) {
            log.error("Error rolling back products validation: ", ex);
            eventProducer.sendEvent(event, Topic.VALIDATION_SERVICE_VALIDATION_ROLLBACK_FAILED_V1.getTopic());
        }
    }

    private void markValidationAsFailed(OrderEvent event) {
        var orderId = event.getPayload().id();
        var transactionId = event.getPayload().transactionId();
        validationRepository
                .findByOrderIdAndTransactionId(orderId, transactionId)
                .ifPresentOrElse(validation -> {
                            validation.markFailed();
                            validationRepository.save(validation);
                        }, () -> createValidation(event, false)
                );
    }

}
