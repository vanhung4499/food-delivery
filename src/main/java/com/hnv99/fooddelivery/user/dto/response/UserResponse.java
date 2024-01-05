package com.hnv99.fooddelivery.user.dto.response;

import com.hnv99.fooddelivery.user.domain.Level;
import com.hnv99.fooddelivery.user.domain.User;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record UserResponse (
        Long id,
        String email,
        String name,
        String phone,
        LocalDate birthday,
        Level level,
        List<AddressResponse> addresses
) {
    public static UserResponse from(User user, List<AddressResponse> addresses) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .phone(user.getPhone())
                .birthday(user.getBirthday())
                .level(user.getLevel())
                .addresses(addresses)
                .build();
    }
}
