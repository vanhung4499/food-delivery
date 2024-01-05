package com.hnv99.fooddelivery.user.controller;

import com.hnv99.fooddelivery.user.dto.request.*;
import com.hnv99.fooddelivery.user.dto.response.AddressResponse;
import com.hnv99.fooddelivery.user.dto.response.UserCreateResponse;
import com.hnv99.fooddelivery.user.dto.response.UserLoginResponse;
import com.hnv99.fooddelivery.user.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hnv99.fooddelivery.user.service.UserService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<UserCreateResponse> createUser(@Valid @RequestBody UserCreateRequest request) {
        UserCreateResponse response = userService.createUser(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<UserLoginResponse> login(@Valid @RequestBody UserLoginRequest request) {
        UserLoginResponse response = userService.login(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long userId) {
        UserResponse response = userService.getUser(userId);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<Void> updateUser(
            @PathVariable Long userId,
            @Valid @RequestBody UserUpdateRequest request
    ) {
        userService.updateUser(userId, request);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/users/{userId}/addresses")
    public ResponseEntity<AddressResponse> createAddress(
            @PathVariable Long userId,
            @Valid @RequestBody AddressCreateRequest request
    ) {
        AddressResponse response = userService.createAddress(userId, request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/users/{userId}/addresses")
    public ResponseEntity<List<AddressResponse>> getAllAddress(@PathVariable Long userId) {
        List<AddressResponse> response = userService.getAllAddresses(userId);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/users/{userId}/addresses/{addressId}")
    public ResponseEntity<Void> deleteAddress(
            @PathVariable Long userId,
            @PathVariable Long addressId
    ) {
        userService.deleteAddress(userId, addressId);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/users/{userId}/addresses/{addressId}")
    public ResponseEntity<Void> updateAddress(
            @PathVariable Long userId,
            @PathVariable Long addressId,
            @Valid @RequestBody AddressUpdateRequest request
    ) {
        userService.updateAddress(
                userId,
                addressId,
                request
        );

        return ResponseEntity.noContent().build();
    }
}

