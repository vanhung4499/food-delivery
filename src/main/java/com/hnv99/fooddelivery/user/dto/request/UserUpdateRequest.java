package com.hnv99.fooddelivery.user.dto.request;

import com.hnv99.fooddelivery.global.util.PhonePolicy;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

import static com.hnv99.fooddelivery.user.domain.User.*;
import static com.hnv99.fooddelivery.user.domain.User.PHONE_MAX_LENGTH;

public record UserUpdateRequest (
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
) { }
