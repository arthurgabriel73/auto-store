package com.autostore.bff.infrastructure.adapter.driver.rest;


import com.autostore.bff.application.service.inventory.InventoryService;
import com.autostore.bff.application.service.inventory.dto.*;
import com.autostore.bff.application.service.order.OrderService;
import com.autostore.bff.application.service.order.dto.CreateOrderRequest;
import com.autostore.bff.application.service.order.dto.CreateOrderResponse;
import com.autostore.bff.application.service.user.UserService;
import com.autostore.bff.application.service.user.dto.AuthUserRequest;
import com.autostore.bff.application.service.user.dto.AuthUserResponse;
import com.autostore.bff.application.service.user.dto.RegisterUserRequest;
import com.autostore.bff.application.service.user.dto.RegisterUserResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.autostore.bff.infrastructure.adapter.driver.rest.examples.SwaggerExamples.*;


@RestController
@RequestMapping("/auto-store")
@RequiredArgsConstructor
public class AutoStoreController {

    private final UserService userService;
    private final InventoryService inventoryService;
    private final OrderService orderService;

    @PostMapping("/auth")
    @Tag(name = "Customer")
    public ResponseEntity<AuthUserResponse> auth(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = @ExampleObject(value = AUTH_USER)))
            @RequestBody AuthUserRequest request
    ) {
        return ResponseEntity.ok(userService.authUser(request));
    }

    @PostMapping("/register-customer")
    @Tag(name = "Customer")
    public ResponseEntity<RegisterUserResponse> registerUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = @ExampleObject(value
                    = REGISTER_USER)))
            @RequestBody RegisterUserRequest request
    ) {
        return new ResponseEntity<>(userService.registerUser(request), HttpStatus.CREATED);
    }

    @PostMapping("/register-product")
    @Tag(name = "Product")
    public ResponseEntity<ProductResponse> registerProduct(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = @ExampleObject(value
                    = REGISTER_PRODUCT)))
            @RequestBody RegisterProductRequest request
    ) {
        return new ResponseEntity<>(inventoryService.registerProduct(request), HttpStatus.CREATED);
    }

    @PostMapping("/add-product-stock")
    @Tag(name = "Product")
    public ResponseEntity<Void> addProductStock(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = @ExampleObject(value
                    = ADD_PRODUCT_STOCK)))
            @RequestBody AddProductStockRequest request) {
        inventoryService.addProductStock(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/available-products")
    @Tag(name = "Product")
    public ResponseEntity<ListAvailableProductsResponse> listAvailableProducts() {
        return ResponseEntity.ok(inventoryService.listAvailableProducts());
    }

    @GetMapping("/sold-products")
    @Tag(name = "Product")
    public ResponseEntity<ListSoldProductsResponse> listSoldProducts() {
        return ResponseEntity.ok(inventoryService.listSoldProducts());
    }

    @PutMapping("/update-product")
    @Tag(name = "Product")
    public ResponseEntity<Void> updateProduct(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = @ExampleObject(value
                    = UPDATE_PRODUCT)))
            @RequestBody UpdateProductRequest request
    ) {
        inventoryService.updateProduct(request);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/create-order")
    @Tag(name = "Order")
    public ResponseEntity<CreateOrderResponse> createOrder(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = @ExampleObject(value
                    = CREATE_ORDER)))
            @RequestBody CreateOrderRequest request) {
        // TODO: Get customer from auth token provided in the request header
        return new ResponseEntity<>(orderService.createOrder(request), HttpStatus.CREATED);
    }

}
