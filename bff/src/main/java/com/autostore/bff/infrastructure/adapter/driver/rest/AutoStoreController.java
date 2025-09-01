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
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auto-store")
@RequiredArgsConstructor
@Tag(name = "auto-store-api")
public class AutoStoreController {

    private final UserService userService;
    private final InventoryService inventoryService;
    private final OrderService orderService;

    @PostMapping("/auth")
    public ResponseEntity<AuthUserResponse> auth(@RequestBody AuthUserRequest request) {
        return ResponseEntity.ok(userService.authUser(request));
    }

    @PostMapping("/register-customer")
    public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody RegisterUserRequest request) {
        return new ResponseEntity<>(userService.registerUser(request), HttpStatus.CREATED);
    }

    @PostMapping("/register-product")
    public ResponseEntity<ProductResponse> registerProduct(@RequestBody RegisterProductRequest request) {
        return new ResponseEntity<>(inventoryService.registerProduct(request), HttpStatus.CREATED);
    }

    @PostMapping("/add-product-stock")
    public ResponseEntity<Void> registerProduct(@RequestBody AddProductStockRequest request) {
        inventoryService.addProductStock(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/available-products")
    public ResponseEntity<ListAvailableProductsResponse> listAvailableProducts() {
        return ResponseEntity.ok(inventoryService.listAvailableProducts());
    }

    @GetMapping("/sold-products")
    public ResponseEntity<ListSoldProductsResponse> listSoldProducts() {
        return ResponseEntity.ok(inventoryService.listSoldProducts());
    }

    @PutMapping("/update-product")
    public ResponseEntity<Void> updateProduct(@RequestBody UpdateProductRequest request) {
        inventoryService.updateProduct(request);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/create-order")
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
        return new ResponseEntity<>(orderService.createOrder(request), HttpStatus.CREATED);
    }

}
