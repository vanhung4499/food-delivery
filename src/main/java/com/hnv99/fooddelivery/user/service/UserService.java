package com.hnv99.fooddelivery.user.service;

import com.hnv99.fooddelivery.global.config.security.jwt.JwtAuthentication;
import com.hnv99.fooddelivery.global.config.security.jwt.JwtAuthenticationToken;
import com.hnv99.fooddelivery.global.error.exception.BusinessException;
import com.hnv99.fooddelivery.global.error.exception.EntityNotFoundException;
import com.hnv99.fooddelivery.global.error.exception.ErrorCode;
import com.hnv99.fooddelivery.global.error.exception.InvalidValueException;
import com.hnv99.fooddelivery.user.domain.Address;
import com.hnv99.fooddelivery.user.domain.AddressRepository;
import com.hnv99.fooddelivery.user.domain.User;
import com.hnv99.fooddelivery.user.domain.UserRepository;
import com.hnv99.fooddelivery.user.dto.request.*;
import com.hnv99.fooddelivery.user.dto.response.AddressResponse;
import com.hnv99.fooddelivery.user.dto.response.UserCreateResponse;
import com.hnv99.fooddelivery.user.dto.response.UserLoginResponse;
import com.hnv99.fooddelivery.user.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public UserCreateResponse createUser(UserCreateRequest request) {
        String encodedPassword = passwordEncoder.encode(request.password());
        User user = request.toEntity(encodedPassword);
        User savedUser = userRepository.save(user);

        return UserCreateResponse.from(savedUser);
    }

    public UserLoginResponse login(UserLoginRequest request) {
        JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(request.email(), request.password());
        Authentication authenticated = authenticationManager.authenticate(authenticationToken);
        JwtAuthentication authentication = (JwtAuthentication)authenticated.getPrincipal();
        User user = (User)authenticated.getDetails();
        return new UserLoginResponse(
                authentication.getToken(),
                user.getId(),
                user.getRoles()
        );
    }

    public UserResponse getUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(
                        () -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND)
                );
        List<AddressResponse> addresses = user.getAddresses()
                .stream()
                .map(AddressResponse::from)
                .toList();
        return UserResponse.from(user, addresses);
    }

    @Transactional
    public void updateUser(Long userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(
                        () -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND)
                );
        user.updateInfo(
                request.name(),
                request.phone(),
                request.birthday()
        );
    }

    @Transactional
    public void changePassword(Long userId, ChangePasswordRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(
                        () -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND)
                );

    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(
                        () -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND)
                );
        userRepository.delete(user);
    }

    @Transactional
    public AddressResponse createAddress(Long userId, AddressCreateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(
                        () -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND)
                );
        Address address = request.toEntity();
        address.attachToUser(user);
        Address savedAddress = addressRepository.save(address);

        return AddressResponse.from(savedAddress);
    }

    public List<AddressResponse> getAllAddresses(Long userId) {
        List<Address> addresses = addressRepository.findAllByUserId(userId);
        return addresses.stream()
                .map(AddressResponse::from)
                .toList();
    }

    @Transactional
    public void deleteAddress(Long userId, Long addressId) {
        User user = userRepository.findById(userId)
                .orElseThrow(
                        () -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND)
                );
        Address address = addressRepository.findById(addressId)
                .orElseThrow(
                        () -> new InvalidValueException(ErrorCode.USER_ADDRESS_BAD_REQUEST)
                );
        if (!user.removeAddress(address)) {
            throw new BusinessException(ErrorCode.USER_ADDRESS_NOT_MATCH);
        }
    }

    @Transactional
    public void updateAddress(Long userId, Long addressId, AddressUpdateRequest request) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(
                        () -> new InvalidValueException(ErrorCode.USER_ADDRESS_BAD_REQUEST)
                );
        if (!address.getUser().getId().equals(userId)) {
            throw new BusinessException(ErrorCode.USER_ADDRESS_NOT_MATCH);
        }
        address.updateAddress(request.addressAlias(), request.address());
    }
}
