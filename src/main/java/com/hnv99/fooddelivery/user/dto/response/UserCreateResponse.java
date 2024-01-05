package com.hnv99.fooddelivery.user.dto.response;

import com.hnv99.fooddelivery.user.domain.Level;
import com.hnv99.fooddelivery.user.domain.User;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record UserCreateResponse(
        Long id,
        String email,
        String name,
        String phone,
        LocalDate birthday,
        Level grade,
        List<AddressResponse> addresses
) {
    public static UserCreateResponse from(User user) {
        return UserCreateResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .phone(user.getPhone())
                .birthday(user.getBirthday())
                .grade(user.getLevel())
                .addresses(user.getAddresses().stream()
                        .map(AddressResponse::from)
                        .toList())
                .build();
    }
}
