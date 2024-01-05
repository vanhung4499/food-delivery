package com.hnv99.fooddelivery.user.dto.request;

import com.hnv99.fooddelivery.global.util.PhonePolicy;
import com.hnv99.fooddelivery.user.domain.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.time.LocalDate;

import static com.hnv99.fooddelivery.user.domain.User.*;

public record UserCreateRequest(
    @Size(max = EMAIL_MAX_LENGTH, message = "The login id can be up to {max} characters.")
    @NotBlank(message = "The login id cannot be empty.")
    String email,

    @Size(max = PASSWORD_MAX_LENGTH, message = "The password can be up to {max} characters.")
    @NotBlank(message = "The password cannot be empty.")
    String password,

    @Size(max = NAME_MAX_LENGTH, message = "The name can be up to {max} characters.")
    @NotBlank(message = "The name cannot be empty.")
    String name,

    @Size(max = PHONE_MAX_LENGTH, message = "The phone number can be up to {max} characters.")
    @NotBlank(message = "The phone number cannot be empty.")
    @Pattern(regexp = PhonePolicy.PHONE_PATTERN, message = "The phone number must be in the format of 010-1234-5678.")
    String phone,

    @NotNull(message = "The birthday must not be null.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate birthday
) {
        public User toEntity(String encodedPassword) {
            return User.builder()
                    .email(email)
                    .name(name)
                    .password(User.password(password, encodedPassword))
                    .phone(phone)
                    .birthday(birthday)
                    .build();
        }
}
